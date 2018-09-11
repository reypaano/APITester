package com.example.reyanthonypaano.apitester.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;
import java.util.List;
import java.util.PriorityQueue;

public class UserAuth {

    @SerializedName("code")
    private String code;

    @SerializedName("data")
    private Object data;

    @SerializedName("user")
    private List<User> user;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
