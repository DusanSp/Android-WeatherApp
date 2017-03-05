package com.example.dusan.weatherapp.presenter;

import android.util.Log;
import com.example.dusan.weatherapp.model.DataManager;
import com.example.dusan.weatherapp.model.WeatherResponse;
import com.example.dusan.weatherapp.view.IView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Dusan on 05.Mar.17.
 */

public class Presenter {

  private DataManager mDataManager;
  private IView view;

  public Presenter(DataManager dataManager, IView view)
  {
    this.mDataManager = dataManager;
    this.view = view;
  }

  public void featchWeatherFor(String cityName)
  {
    Observable<WeatherResponse> responseObservable = mDataManager.getWeather(cityName);
    responseObservable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .unsubscribeOn(Schedulers.io())
        .subscribe(
            new DisposableObserver<WeatherResponse>() {

              @Override
              public void onNext(WeatherResponse weatherResponse) {

                view.showWeatherData(weatherResponse);
              }

              @Override
              public void onError(Throwable t) {
                Log.d("onError", t.toString());
              }

              @Override
              public void onComplete() {
                Log.d("TAG", "onComplete");
              }
            }
        );
  }
}
