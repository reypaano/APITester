package com.example.reyanthonypaano.apitester.model;

import com.google.gson.annotations.SerializedName;

public class UserAuth {

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("token_type")
    private String tokenType;
}
