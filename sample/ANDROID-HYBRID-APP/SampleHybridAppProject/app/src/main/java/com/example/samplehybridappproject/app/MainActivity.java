package com.example.samplehybridappproject.app;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URISyntaxException;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. WebView 생성

        final WebView myWebView = (WebView) findViewById(R.id.webview);

        // 2. JavaScript 활성화

        WebSettings settings = myWebView.getSettings();
        settings.setJavaScriptEnabled(true);

        // 3. HTML5 LocalStorage 활성화

        if (Build.VERSION.SDK_INT >= 7) {
            // SDK 2.1 부터 HTML5 LocalStorage를 사용할 수 있다.
            settings.setDomStorageEnabled(true);
        }

        // 4. User-Agent

        Context context = myWebView.getContext();
        PackageManager packageManager = context.getPackageManager();
        String appName = "";
        String appVersion = "";
        String userAgent = settings.getUserAgentString();

        try {
            // App 이름 추출
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            appName = packageManager.getApplicationLabel(applicationInfo).toString();

            // App 버전 추출
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            appVersion = String.format("%s", "" + packageInfo.versionName);


            // User-Agent에 App 이름과 버전을 붙여서 보내줌
            userAgent = String.format("%s %s %s", userAgent, appName, appVersion);


            // 변경된 User-Agent 반영
            settings.setUserAgentString(userAgent);

        } catch (PackageManager.NameNotFoundException e) {
            // e.printStackTrace();
        }

        // 5. WebViewClient 사용

        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("WebViewClient", "Loading URL : " + url);
                if (view == null || url == null) {
                    return false;
                }

                if (url.contains("play.google.com")) {
                    // play.google.com 도메인이면서 App 링크인 경우에는 market:// 로 변경
                    String[] params = url.split("details");
                    if (params.length > 1) {
                        url = "market://details" + params[1];
                        view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                        return true;
                    }
                }

                if (url.startsWith("http:") || url.startsWith("https:")) {
                    // HTTP/HTTPS 요청은 내부에서 처리한다.
                    view.loadUrl(url);
                } else {
                    Intent intent;

                    try {
                        intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                    } catch (URISyntaxException e) {
                        return false;
                    }

                    try {
                        view.getContext().startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        // Intent Scheme인 경우, 앱이 설치되어 있지 않으면 Market으로 연결
                        if (url.startsWith("intent:") && intent.getPackage() != null) {
                            url = "market://details?id=" + intent.getPackage();
                            view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
                return true;
            }
        });

        myWebView.loadUrl("http://m.daum.net/");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
