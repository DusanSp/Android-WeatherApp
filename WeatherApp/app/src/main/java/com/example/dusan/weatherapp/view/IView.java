package com.example.dusan.weatherapp.view;

import com.example.dusan.weatherapp.model.WeatherResponse;


public interface IView {
  void showWeatherData(WeatherResponse response);
}
