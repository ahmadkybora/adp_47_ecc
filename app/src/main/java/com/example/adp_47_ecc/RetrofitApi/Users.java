package com.example.adp_47_ecc.RetrofitApi;

import com.google.gson.annotations.SerializedName;

public class Users {

    @SerializedName("response")
    private String response;

    @SerializedName("user_id")
    private String UserId;

    public String getResponse() {
        return response;
    }

    public String getUserId() {
        return UserId;
    }
}
