package io.github.ryanhoo.music.ui.settings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.ryanhoo.music.Config;
import io.github.ryanhoo.music.R;

public class WeiboLoginActivity extends AppCompatActivity {


    @BindView(R.id.webview_login)
    WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weibo_login);
        ButterKnife.bind(this);

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                // do your handling codes here, which url is the requested url
                // probably you need to open that url rather than redirect:
                super.shouldOverrideUrlLoading(view, url);
                view.loadUrl(url);
                Log.i("WebView", "Open url " + url);
                return false; // then it is not handled by default action
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.i("WebView", "finished url " + url);
                String cookie = CookieManager.getInstance().getCookie(url);
                if (cookie == null) {
                    Log.d("WebView", "No cookie");
                    return;
                }
                HashMap<String, String> parsed = parseCookie(cookie);
                if (parsed.containsKey("MUSIC_U")) {
                    storeCookie(parsed);
                    setResult(AppCompatActivity.RESULT_OK, new Intent());
                    finish();
                }
                Log.d("WebView", "All the cookies in a string:" + cookie);
            }
        });

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSupportZoom(true);
        mWebView.loadUrl("https://api.weibo.com/oauth2/authorize?client_id=301575942&response_type=code&redirect_uri=http://music.163.com/back/weibo&forcelogin=true&scope=friendships_groups_read,statuses_to_me_read,follow_app_official_microblog&state=wRuqzeLivI&checkToken=9ca17ae2e6ffcda170e2e6ee87b65fafb2e1a4ae62a3868eb3c44b979f9e84f245a6adb6ace94df593ab82eb2af0feaec3b92a86acad86f360b2ecf8bae65a838e9fa7c85b8aeb85adee3994a68dd8cd3eb6b4ee9e");

    }

    private void storeCookie(HashMap<String, String> cookie) {
        SharedPreferences sharedPreferences =
                getApplicationContext().getSharedPreferences(Config.MP_LOGIN_USER_COOKIE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (HashMap.Entry<String, String> each : cookie.entrySet()) {
            editor.putString(each.getKey(), each.getValue());
        }
        editor.apply();
    }

    private HashMap<String, String> parseCookie(String s) {

        HashMap<String, String> retval = new HashMap<>();
        for (String each : s.split(";")) {
            String key = each.split("=")[0];
            String value = each.split("=")[1];
            retval.put(key, value);
        }
        return retval;
    }
}
