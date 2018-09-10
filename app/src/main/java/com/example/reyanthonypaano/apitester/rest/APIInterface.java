package com.example.reyanthonypaano.apitester.rest;

import com.example.reyanthonypaano.apitester.model.UserAuth;
import com.example.reyanthonypaano.apitester.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIInterface {

    @Headers({"Grant-Type: Password"})
    @FormUrlEncoded
    @POST("auth")
    Call<UserAuth> postAPIAuthentication(
            @Field("username") String username,
            @Field("password") String password
        );
}
