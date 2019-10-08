package com.example.webviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = findViewById(R.id.web_view);
        //获取webView实例
        webView.getSettings().setJavaScriptEnabled(true);
        //让WebView支持JavaScript脚本
        webView.setWebViewClient(new WebViewClient());
        //将从网络传入的页面，仍然放在WebView中显示
        webView.loadUrl("https://www.2345.com");
        //传入网络地址
        //切记:加入网络权限
    }
}
