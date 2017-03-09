package com.example.dusan.weatherapp.presenter;


public interface IBasePresenter {

  void getWeatherForCity(String city);
  void unsubscribe();
  void showLoading();
  void hideLoading();
  void failedToGetDataForCity();
}
