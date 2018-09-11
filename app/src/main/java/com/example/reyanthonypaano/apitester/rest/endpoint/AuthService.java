package com.example.reyanthonypaano.apitester.rest.endpoint;

import com.example.reyanthonypaano.apitester.model.User;
import com.example.reyanthonypaano.apitester.model.UserAuth;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AuthService {

    @Headers({
        "Grant-Type: password"
    })
    @FormUrlEncoded
    @POST("auth")
    Call<User> postAuthentication(
            @Field("username") String username,
            @Field("password") String password
        );
}
