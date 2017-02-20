package com.example.dusan.weatherapp.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class WeatherAPI {

    private APICall apiCall;
    private final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    public WeatherAPI()
    {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiCall = retrofit.create(APICall.class);
    }

    public APICall getService()
    {
        return this.apiCall;
    }
}
