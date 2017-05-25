package com.projects.cactus.el_kollia.ApiServices;





import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by el on 1/23/2017.
 */

public class ServiceGenerator {

    //private static final String BASE_URL = "https://api.github.com/";
    private static final String BASE_URL = "http://elkollya.esy.es/";


    private static Retrofit retrofit;

//   static Gson gson = new GsonBuilder()
//            .setLenient()
//            .create();

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


   public static RequestBody createFromString(String string){
        RequestBody requestBody=RequestBody.create(MediaType.parse("text"),string);
        return requestBody;
    }



}