package com.projects.cactus.el_kollia.authentication.model;



import com.projects.cactus.el_kollia.model.ServerResponse;
import com.projects.cactus.el_kollia.model.User;

import io.reactivex.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by el on 2/28/2017.
 */

public interface AuthenticationService {


    @POST("maskan/users/login-register.php")
    Single<ServerResponse> authenticate(@Body() ServerRequest serverRequest) ;

    @POST()
    Single<User> getUserData(@Field("email") RequestBody email, @Field("password") RequestBody password);

    @FormUrlEncoded
    @POST("maskan/users/get_user.php")
    Single<User> getUserData(@Field("id") String uerId);
}
