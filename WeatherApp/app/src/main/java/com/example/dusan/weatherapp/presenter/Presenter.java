package com.example.dusan.weatherapp.presenter;

import android.util.Log;
import com.example.dusan.weatherapp.model.DataManager;
import com.example.dusan.weatherapp.model.WeatherResponse;
import com.example.dusan.weatherapp.view.IView;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class Presenter implements IBasePresenter {

  private DataManager mDataManager;
  private IView view;
  private Observable<WeatherResponse> responseObservable;

  public Presenter(DataManager dataManager, IView view) {
    this.mDataManager = dataManager;
    this.view = view;
  }

  @Override
  public void getData(String city) {
    responseObservable = mDataManager.getWeather(city);
    responseObservable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            new DisposableObserver<WeatherResponse>() {

              @Override
              public void onNext(WeatherResponse weatherResponse) {

                view.showWeatherData(weatherResponse);
              }

              @Override
              public void onError(Throwable t) {
                Log.d("onError", t.toString());
                failedToGetData();
              }

              @Override
              public void onComplete() {
                Log.d("TAG", "onComplete");
              }
            }
        );
  }

  @Override
  public void unsubscribe() {
    responseObservable.unsubscribeOn(Schedulers.io());
  }

  @Override
  public void showLoadingData() {

  }

  @Override
  public void hideLoadingData() {

  }

  @Override
  public void failedToGetData() {

  }
}
