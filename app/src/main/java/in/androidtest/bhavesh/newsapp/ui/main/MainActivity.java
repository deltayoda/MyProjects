package in.androidtest.bhavesh.newsapp.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.androidtest.bhavesh.newsapp.R;
import in.androidtest.bhavesh.newsapp.adapters.NewsApapter;
import in.androidtest.bhavesh.newsapp.models.NewsApi.NewsMain;

public class MainActivity extends AppCompatActivity implements MainViewInterface {

    @BindView(R.id.rvNews)
    RecyclerView rvNews;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    //Added in Part 2 of the series
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String TAG = "MainActivity";
    RecyclerView.Adapter adapter;
    MainPresenter mainPresenter;
    //RecyclerView rvMovies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //rvMovies = (RecyclerView) findViewById(R.id.rvMovies);
        setupMVP();
        setupViews();
        getNewsList();
    }

    private void setupMVP() {
        mainPresenter = new MainPresenter(this);
    }

    private void setupViews(){
        //Added in Part 2 of the series
        setSupportActionBar(toolbar);
        rvNews.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getNewsList() {
     mainPresenter.getNews("us");
    }



    @Override
    public void showToast(String str) {
        Toast.makeText(MainActivity.this,str,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void displayNews(NewsMain newsMain) {
        if(newsMain!=null) {
            Log.d(TAG,newsMain.getStatus());
            adapter = new NewsApapter(newsMain.getArticles(), MainActivity.this);
            rvNews.setAdapter(adapter);
        }else{
            Log.d(TAG,"Response null");
        }
    }

    @Override
    public void displayError(String e) {
        showToast(e);
    }
}
