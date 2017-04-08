package com.projects.cactus.el_kollia.ApiServices;





import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by el on 1/23/2017.
 */

public class ServiceGenerator {

    //private static final String BASE_URL = "https://api.github.com/";
    private static final String BASE_URL = "http://bishoy.esy.es/";


    private static Retrofit retrofit;

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());


    private static OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder();

    public static <S> S createService(
            Class<S> serviceClass) {

            retrofit = builder.build();
        return retrofit.create(serviceClass);
    }
}