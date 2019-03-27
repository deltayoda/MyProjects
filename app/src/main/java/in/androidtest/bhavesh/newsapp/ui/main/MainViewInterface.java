package in.androidtest.bhavesh.newsapp.ui.main;

import in.androidtest.bhavesh.newsapp.models.NewsApi.NewsMain;


public interface MainViewInterface {

    void showToast(String s);
    void showProgressBar();
    void hideProgressBar();
    void displayNews(NewsMain newsMain);
    void displayError(String s);
}
