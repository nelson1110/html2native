package com.example.administrator.js2native;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        button = (Button) findViewById(R.id.button);
        webview = (WebView) findViewById(R.id.webview);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.loadUrl("javascript:callJS()");
//                webview.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        webview.loadUrl("javascript:callJS()");
//                    }
//                });
            }
        });
        webview.setWebChromeClient(new WebChromeClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.addJavascriptInterface(new JsInterface(),"test");
        webview.loadUrl("file:///android_asset/test.html");
    }

    class JsInterface{
        @JavascriptInterface
        public void toaststr(String s){
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            Log.e("JsInterface", s);
        }
    }
}
