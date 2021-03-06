package com.example.dusan.weatherapp.model;

import io.reactivex.Observable;


public class DataManager {

    private final String API_KEY = "c2767964e5bf6a348411a15c90f4f9c9";

//    public void getWeatherData() {
//        WeatherAPI weatherAPI = new WeatherAPI();
//
//        Observable<WeatherResponse> call = weatherAPI.getService().getWeatherData("Paris", API_KEY);
//        call.enqueue(new Callback<WeatherResponse>() {
//            @Override
//            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
//                if(response != null)
//                {
//                    ArrayList weatherList = response.body().getWeatherData();
//                    MainWeatherData mainParametars = response.body().getWeatherData();
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

    public Observable<WeatherResponse> getWeatherData(String cityName)
    {
      WeatherAPI weatherAPI = new WeatherAPI();
      return  weatherAPI.getService().getWeatherCall(cityName, API_KEY);
    }

}
