package com.example.dusan.weatherapp.model;

import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DataManager {

    private final String API_KEY = "c2767964e5bf6a348411a15c90f4f9c9";

    public void getWeather() {
        WeatherAPI weatherAPI = new WeatherAPI();

        Call<WeatherResponse> call = weatherAPI.getService().getWeather("Paris", API_KEY);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if(response != null)
                {
                    ArrayList weatherList = response.body().getWeather();
                    Main mainParametars = response.body().getMainParametars();
                }


                Log.d("TEST", "------->");
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.d("TEST", t.toString());
            }
        });
    }
}
