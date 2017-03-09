package com.example.dusan.weatherapp.presenter;


public interface IBasePresenter {

  void getData(String city);
  void unsubscribe();
  void showLoadingData();
  void hideLoadingData();
  void failedToGetData();
}
