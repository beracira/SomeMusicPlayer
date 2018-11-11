package io.github.ryanhoo.music.nodeAPI;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import io.github.ryanhoo.music.Config;
import io.github.ryanhoo.music.Injection;
import io.github.ryanhoo.music.R;
import io.github.ryanhoo.music.data.model.PlayList;
import io.github.ryanhoo.music.data.model.Song;
import io.github.ryanhoo.music.nodeAPI.PigFarmModel.CacheData;
import io.github.ryanhoo.music.nodeAPI.PigFarmModel.Recommend;
import io.github.ryanhoo.music.nodeAPI.PigFarmModel.RecommendWrapper;
import io.github.ryanhoo.music.player.Player;
import io.github.ryanhoo.music.ui.playlist.PlayListPresenter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSink;
import okio.Okio;

public class RequestAPI extends AsyncTask<String, Void, String> {

    static private RequestAPI instance = null;

//    static private void sendRequest {
//        NodeInterface nodeInterface = NodeInterface.getInstance(view.getContext().getApplicationContext());
//
//    }

    private OkHttpClient client = new OkHttpClient();

    public static RequestAPI getInstance() {
        if (instance == null) {
            instance = new RequestAPI();
            instance.updateCookie();
        }
        return instance;
    }

    private static String BASE_URL = "http://localhost:3000";
    private static String URL_DAILY = "http://localhost:3000/recommend/songs";

    private void updateCookie() {
        client = new OkHttpClient().newBuilder()
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        return buildCookie();
                    }
                })
                .build();
    }

    private List<Cookie> buildCookie() {
        SharedPreferences prefs = Injection.provideContext().getSharedPreferences(
                Config.MP_LOGIN_USER_COOKIE, Context.MODE_PRIVATE);
//        String MUSIC_U = "MUSIC_U=" + prefs.getString("MUSIC_U", "");
//        String __csrf = "__csrf=" + prefs.getString("__csrf", "");
//        urlConnection.addRequestProperty("Cookie", "__remember_me=true");

        ArrayList<Cookie> cookies = new ArrayList<>();

        Cookie MUSIC_U_cookie = new Cookie.Builder().domain("localhost").path("/").name("MUSIC_U")
                .value(prefs.getString("MUSIC_U", "")).build();

        Cookie __csrf_cookie = new Cookie.Builder().domain("localhost").path("/").name("__csrf")
                .value(prefs.getString("__csrf", "")).build();

        Cookie remember_cookie = new Cookie.Builder().domain("localhost").path("/").name("__remember_me")
                .value("true").build();


        cookies.add(MUSIC_U_cookie);
        cookies.add(__csrf_cookie);
        cookies.add(remember_cookie);

        return cookies;


    }

    private abstract class MyCallback implements Callback {
        @Override
        public void onFailure(Call call, IOException e) {
            e.printStackTrace();
        }
    }

    public void getDaily() {
        Request request = new Request.Builder().url(URL_DAILY).build();
        client.newCall(request).enqueue(new MyCallback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                Gson gson = new GsonBuilder().create();
                RecommendWrapper recommendWrapper = gson.fromJson(s, RecommendWrapper.class);
                if (recommendWrapper.code != 200) {
                    Log.e("get_daily", "oops, we are fucked");
                } else {
                    List<Recommend> recommendList = recommendWrapper.recommend;
                    PlayList playList = new PlayList();
                    playList.setName(Injection.provideContext().getString(R.string.mp_play_list_daily));
                    for (int i = 0; i < recommendList.size(); ++i) {
                        Song song = new Song();
                        Recommend recommend = recommendList.get(i);
                        song.setNid(recommend.id);
                        song.setTitle(recommend.name);
                        song.setDisplayName(recommend.name);
                        try {
                            song.setArtist(recommend.artists.get(0).name);
                        } catch (IndexOutOfBoundsException e) {
                            song.setArtist("");
                        }
                        song.setPath("/");
                        song.setAlbum(recommend.album.name);
                        song.setDuration(recommend.duration);
                        song.setSize(0);
                        playList.addSong(song);
                    }
                    // this code can only run on UI thread.
                    Handler handler = new Handler(Injection.provideContext().getMainLooper());
                    handler.post(() -> PlayListPresenter.getInstance().createPlayList(playList))                    ;
                }
            }
        });
    }

    public void getCache(Song song) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                String id = song.getNid();
                File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
                String filename = song.getArtist() + " - " + song.getDisplayName();
                String path = dir + File.separator + filename;
                Handler handler = new Handler(Injection.provideContext().getMainLooper());

                String base_url = "https://api.imjad.cn/cloudmusic/?type=song&id=%s&br=%s";
                String[] brs = new String[3];
                brs[0] = "320000"; brs[1] = "198000"; brs[2] = "128000";
                handler.post(() ->
                        Toast.makeText(Injection.provideContext(),
                                Injection.provideContext().getString(R.string.mp_toast_start_download), Toast.LENGTH_LONG).show());
                for (int i = 0; i < 3; ++i) {
                    Request request = new Request.Builder().url(String.format(base_url, id, brs[i])).build();
                    Log.d("startdownloading", brs[i] + path);
                    try {
                        String s = client.newCall(request).execute().body().string();
                        Gson gson = new GsonBuilder().create();
                        CacheData cacheData = gson.fromJson(s, CacheData.class);
                        if (cacheData.code != 200) {
                            Log.e("getCache","oops, we are fucked again");
                            continue;
                        }
                        Request fileRequest = new Request.Builder().url(cacheData.data.get(0).url).build();
                        Response response = client.newCall(fileRequest).execute();
                        File cacheFile = new File(path);
                        BufferedSink sink = Okio.buffer(Okio.sink(cacheFile));
                        sink.writeAll(response.body().source());
                        sink.close();

                        song.setSize(cacheData.data.get(0).size);
                        song.setPath(path);

                        handler.post(() ->
                                Toast.makeText(Injection.provideContext(),
                                        Injection.provideContext().getString(R.string.mp_toast_download_complete), Toast.LENGTH_LONG).show());
                        handler.post(() ->
                                Player.getInstance().play());
                        break;
                    } catch (IOException | NullPointerException e) {
                        handler.post(() ->
                                Toast.makeText(Injection.provideContext(),
                                        Injection.provideContext().getString(R.string.mp_toast_download_fail), Toast.LENGTH_LONG).show());
                        e.printStackTrace();
                    }
                }
                return null;
            }
        }.execute();
    }

    public void query(String s) {
        Request request = new Request.Builder().url(BASE_URL + s).build();
        client.newCall(request).enqueue(new MyCallback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                Log.d("get_query", s);
            }
        });
    }

    @Override
    protected void onPostExecute(String s) {
        Gson gson = new GsonBuilder().create();
        RecommendWrapper recommend = gson.fromJson(s, RecommendWrapper.class);
//        Type mapType = new TypeToken<Map<String, Map>>(){}.getType();
//        Map<String, String[]> son = new Gson().fromJson(s, mapType);
        Log.d("Request Result", s);
    }

    @Override
    protected String doInBackground(String... urls) {
        String nodeResponse="";
        try {
            URL localNodeServer = new URL("http://localhost:3000" + urls[0]);
            URLConnection urlConnection = localNodeServer.openConnection();
            SharedPreferences prefs = Injection.provideContext().getSharedPreferences(
                    Config.MP_LOGIN_USER_COOKIE, Context.MODE_PRIVATE);
            String MUSIC_U = "MUSIC_U=" + prefs.getString("MUSIC_U", "");
            String __csrf = "__csrf=" + prefs.getString("__csrf", "");
            urlConnection.addRequestProperty("Cookie", MUSIC_U);
            urlConnection.addRequestProperty("Cookie", "__remember_me=true");
            urlConnection.addRequestProperty("Cookie", __csrf);
            urlConnection.connect();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                nodeResponse=nodeResponse+inputLine;
            in.close();
        } catch (Exception ex) {
            nodeResponse=ex.toString();
        }
        return nodeResponse;
    }
}
