package com.zybooks.highapp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
        @POST("/register")
        Call<ServerResponse> registerUser(@Body User user);

        @POST("/login")
        Call<ServerResponse> loginUser(@Body User user);
    }


