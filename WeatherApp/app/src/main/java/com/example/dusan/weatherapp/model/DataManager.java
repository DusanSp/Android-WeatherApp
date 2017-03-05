package com.example.dusan.weatherapp.model;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;

import java.util.concurrent.Callable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DataManager {

    private final String API_KEY = "c2767964e5bf6a348411a15c90f4f9c9";

//    public void getWeather() {
//        WeatherAPI weatherAPI = new WeatherAPI();
//
//        Observable<WeatherResponse> call = weatherAPI.getService().getWeather("Paris", API_KEY);
//        call.enqueue(new Callback<WeatherResponse>() {
//            @Override
//            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
//                if(response != null)
//                {
//                    ArrayList weatherList = response.body().getWeather();
//                    Main mainParametars = response.body().getMainParametars();
//                }
//
//
//                Log.d("TEST", "------->");
//            }
//
//            @Override
//            public void onFailure(Call<WeatherResponse> call, Throwable t) {
//                Log.d("TEST", t.toString());
//            }
//        });
//    }

    public Observable<WeatherResponse> getWeather(String cityName)
    {
      WeatherAPI weatherAPI = new WeatherAPI();
      return  weatherAPI.getService().getWeather(cityName, API_KEY);
    }

}
