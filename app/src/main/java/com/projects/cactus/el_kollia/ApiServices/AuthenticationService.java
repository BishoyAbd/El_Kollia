package com.projects.cactus.el_kollia.ApiServices;


import com.projects.cactus.el_kollia.model.ServerRequest;
import com.projects.cactus.el_kollia.model.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by el on 2/28/2017.
 */

public interface AuthenticationService {


    @POST("retrofit/login_register/index.php")
    Call<ServerResponse> authenticate(@Body ServerRequest serverRequest) ;
}
