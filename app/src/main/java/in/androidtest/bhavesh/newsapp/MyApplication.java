package in.androidtest.bhavesh.newsapp;

import android.app.Application;
import android.content.Context;

import in.androidtest.bhavesh.newsapp.di.components.ApplicationComponent;
import in.androidtest.bhavesh.newsapp.di.components.DaggerApplicationComponent;
import in.androidtest.bhavesh.newsapp.di.modules.ApplicationModule;


public class MyApplication extends Application {

    private static final String TAG = "MyApplication";
    private static MyApplication mInstance;
    private static Context context;
    private static ApplicationComponent applicationComponent;

    public static Context getAppContext() {
        return context;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
                                .builder()
                                .applicationModule(new ApplicationModule(this))
                                .build();

        MyApplication.context = getApplicationContext();

    }

    public ApplicationComponent getApplicationComponent(){
          return applicationComponent;
      }

}
