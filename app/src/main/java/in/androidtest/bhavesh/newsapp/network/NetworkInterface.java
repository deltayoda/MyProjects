package in.androidtest.bhavesh.newsapp.network;


import in.androidtest.bhavesh.newsapp.models.NewsApi.NewsMain;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface NetworkInterface {

    @GET("top-headlines")
    Observable<Response<NewsMain>> getNews(@Query("country") String country, @Query("apiKey") String api_key);
}
