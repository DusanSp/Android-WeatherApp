package com.example.dusan.weatherapp.view;

import com.example.dusan.weatherapp.model.WeatherResponse;

/**
 * Created by Dusan on 05.Mar.17.
 */

public interface IView {
  void showWeatherData(WeatherResponse response);
}
