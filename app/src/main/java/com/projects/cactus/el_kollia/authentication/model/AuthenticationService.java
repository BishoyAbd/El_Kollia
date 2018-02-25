package com.projects.cactus.el_kollia.authentication.model;


import com.projects.cactus.el_kollia.model.ServerRequest;
import com.projects.cactus.el_kollia.model.ServerResponse;
import com.projects.cactus.el_kollia.model.User;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by el on 2/28/2017.
 */

public interface AuthenticationService {


    @POST("elkollya/login_register/index.php")
    Call<ServerResponse> authenticate(@Body ServerRequest serverRequest) ;

    @POST()
    Call<User> getUserData(@Field("email") RequestBody email, @Field("password") RequestBody password);

    @FormUrlEncoded
    @POST("elkollya/login_register/getProfile.php")
    Call<User> getUserData(@Field ("user_id") RequestBody uerId);
}
