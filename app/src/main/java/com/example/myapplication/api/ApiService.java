package com.example.myapplication.api;

import com.example.myapplication.model.LogoutResp;
import com.example.myapplication.model.LoginBody;
import com.example.myapplication.model.LoginResp;
import com.example.myapplication.model.RegisterBody;
import com.example.myapplication.model.RegisterResp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    //https://testdemo-c6ux.onrender.com/
   Gson gson=new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create();
    ApiService apiService=new Retrofit.Builder()
            .baseUrl("https://testdemo-c6ux.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    @POST("auth/login")
    Call<LoginResp> login(@Body LoginBody loginBody);

    @POST("auth/register")
    Call<RegisterResp> register(@Body RegisterBody registerBody);

    @POST("auth/logout")
    Call<LogoutResp> logout();
}
