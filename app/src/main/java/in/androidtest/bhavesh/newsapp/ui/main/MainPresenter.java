package in.androidtest.bhavesh.newsapp.ui.main;


import android.util.Log;

import in.androidtest.bhavesh.newsapp.models.NewsApi.NewsMain;
import in.androidtest.bhavesh.newsapp.network.NetworkClient;
import in.androidtest.bhavesh.newsapp.network.NetworkInterface;
import in.androidtest.bhavesh.newsapp.ui.Constants;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;


public class MainPresenter implements MainPresenterInterface{

    MainViewInterface mvi;
    private String TAG = "MainPresenter";

    public MainPresenter(MainViewInterface mvi) {
        this.mvi = mvi;
    }

    @Override
    public void getNews(String country) {
        getNewsObservable(country).subscribeWith(getNewsObserver());
    }

    public Observable<Response<NewsMain>> getNewsObservable(final String country){
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getNews(country,Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public DisposableObserver<Response<NewsMain>> getNewsObserver(){
        return new DisposableObserver<Response<NewsMain>>() {

            @Override
            public void onNext(@NonNull Response<NewsMain> newsMain) {
                if (newsMain.raw().cacheResponse() != null) {
                    Log.e("Network", "response came from cache");
                }

                if (newsMain.raw().networkResponse() != null) {
                    Log.e("Network", "response came from server");
                }
                mvi.displayNews(newsMain.body());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG,"Error"+e);
                e.printStackTrace();
                mvi.displayError("Error fetching Data");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
                mvi.hideProgressBar();
            }
        };
    }
}
