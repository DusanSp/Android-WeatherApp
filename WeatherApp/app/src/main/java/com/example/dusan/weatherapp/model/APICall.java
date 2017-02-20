package com.example.dusan.weatherapp.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface APICall {


    @GET("/data/2.5/weather")
    Call<WeatherResponse> getWeather(@Query("q") String city, @Query("APPID") String api);
}
