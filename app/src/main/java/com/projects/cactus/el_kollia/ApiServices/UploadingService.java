package com.projects.cactus.el_kollia.ApiServices;

import com.projects.cactus.el_kollia.model.Respond;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by el on 9/16/2017.
 */

public interface UploadingService {

    @Multipart
    @POST("elkollya/update_pic.php")
    Call<Respond> upload(@Part MultipartBody.Part image,
                         @Part("user_id") RequestBody userId,
                         @Part("code") RequestBody product_type); //code =profile for uploading profile pic and code = cover for uploading cover
}
