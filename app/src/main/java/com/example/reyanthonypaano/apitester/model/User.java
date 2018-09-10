package com.example.reyanthonypaano.apitester.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;

public class User {
    @SerializedName("user_id")
    private BigInteger userId;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("badge_no")
    private String badgeNo;

    @SerializedName("email")
    private String email;

    @SerializedName("user_type")
    private String userType;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("middle_name")
    private String middleName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("suffix")
    private String suffix;

    @SerializedName("full_name")
    private String fullName;
}
