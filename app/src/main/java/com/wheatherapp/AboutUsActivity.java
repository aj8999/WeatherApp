package com.wheatherapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.wheatherapp.databinding.ActivityAboutUsBinding;

public class AboutUsActivity extends AppCompatActivity {
    WebView webview;
    ActionBar mActionBar;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        webview = findViewById(R.id.webview);
        progressDialog = new ProgressDialog(AboutUsActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        mActionBar = getSupportActionBar();
        mActionBar.setTitle("About Us");
        mActionBar.setDisplayHomeAsUpEnabled(true);
       webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(AboutUsActivity.this, "Error:" + description, Toast.LENGTH_SHORT).show();
            }
        });
       webview.loadUrl("https://openweathermap.org/guide");


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}