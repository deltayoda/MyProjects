package in.androidtest.bhavesh.newsapp.ui.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import in.androidtest.bhavesh.newsapp.R;

public class WebViewDetailActivity extends AppCompatActivity {

    String newsUrl = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getNewsExtra();
        setUpViews();

    }

    private void getNewsExtra() {
        if (getIntent() != null)
        {
            newsUrl = getIntent().getExtras().getString("news_url");
        }
    }

    private void setUpViews() {
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(newsUrl);
    }
}
