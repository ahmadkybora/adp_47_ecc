package com.example.adp_47_ecc.RetrofitApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("email_registration")
    Call<Users> performEmailRegistration(
            @Query("user_name") String user_name,
            @Query("user_email") String user_email,
            @Query("user_password") String user_password
    );

    @GET("email_login")
    Call<Users> performEmailLogin(
            @Query("user_email") String user_email,
            @Query("user_password") String user_password
    );

    @GET("phone_registration")
    Call<Users> performPhoneRegistration(
            @Query("user_phone") String user_phone
    );

    @GET("phone_login")
    Call<Users> performPhoneLogin(
            @Query("user_phone") String user_phone
    );
}
