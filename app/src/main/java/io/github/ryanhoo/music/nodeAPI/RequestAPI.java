package io.github.ryanhoo.music.nodeAPI;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import io.github.ryanhoo.music.Config;

public class RequestAPI extends AsyncTask<String, Void, String> {

    static private Context appContext;

    static void setAppContext(Context appContext) {
        RequestAPI.appContext = appContext;
    }

//    static private void sendRequest {
//        NodeInterface nodeInterface = NodeInterface.getInstance(view.getContext().getApplicationContext());
//
//    }

    @Override
    protected void onPostExecute(String s) {
        Log.d("Request Result", s);
    }

    @Override
    protected String doInBackground(String... urls) {
        String nodeResponse="";
        try {
            URL localNodeServer = new URL("http://localhost:3000" + urls[0]);
            URLConnection urlConnection = localNodeServer.openConnection();
            SharedPreferences prefs = appContext.getSharedPreferences(
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
