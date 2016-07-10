package com.google.a3dgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ForumWebViewActivity extends AppCompatActivity {

    private WebView webView;
    private String url="http://bbs.3dmgame.com/forum.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_webview);
        webView= (WebView) findViewById(R.id.forum_webview);
        webView.loadUrl(url);
        WebSettings webSettings=webView.getSettings();
        webSettings.setSupportZoom(true);
        WebViewClient webViewClient=new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        };
        webView.setWebViewClient(webViewClient);
    }
}
