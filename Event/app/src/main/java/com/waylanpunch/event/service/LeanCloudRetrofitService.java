package com.waylanpunch.event.service;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by pc on 2017/3/11.
 */
public interface LeanCloudRetrofitService {

    @POST("users")
    Call<Object> signUp(@Header("X-LC-Id") String appId,
                        @Header("X-LC-Key") String appKey,
                        @Header("Content-Type") String contentType,
                        @Body RequestBody userinfo);

    @POST("login")
    Call<Object> signIn(@Header("X-LC-Id") String appId,
                        @Header("X-LC-Key") String appKey,
                        @Header("Content-Type") String contentType,
                        @Body RequestBody userinfo);
}
