package com.example.dusan.weatherapp.model;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface APICall {


    @GET("weather?&units=metric")
    Observable<WeatherResponse> getWeather(@Query("q") String city, @Query("APPID") String api);
}
