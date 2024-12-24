package com.example.joille;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.DELETE;

public interface Api {
        String BASE_URL = "http://10.0.2.2/joille/api/";
        @GET("services/list")
        Call<List<Services>> getServices();
        @GET("services/list/{id}")
        Call<List<Services>> getServicesDescription(@Path("id") int id);
        @POST("users/login")
        Call<User> loginUser(@Body RequestBody requestBody);
        @POST("users/")
        Call<User> createUser(@Body RequestBody requestBody);
        @DELETE("services/delete/{id}")
        Call<Services> deleteService(@Path("id") int id);



}

