package com.example.dusan.weatherapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.dusan.weatherapp.model.DataManager;
import com.example.dusan.weatherapp.model.WeatherResponse;
import com.example.dusan.weatherapp.presenter.Presenter;
import com.example.dusan.weatherapp.view.IView;
import com.google.gson.internal.LinkedTreeMap;
import com.squareup.picasso.Picasso;

public class MainActivity extends Activity implements IView {

  @BindView(R.id.current_temp)
  TextView mCurrentTemp;
  @BindView(R.id.max_temp)
  TextView mMaxTemp;
  @BindView(R.id.min_temp)
  TextView mMinTemp;
  @BindView(R.id.icon)
  ImageView weatherIcon;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
    DataManager dataManager = new DataManager();
    Presenter presenter = new Presenter(dataManager, this);
    presenter.featchWeatherFor("London");
  }

  @Override
  public void showWeatherData(WeatherResponse response) {

    mCurrentTemp.setText(String.valueOf(response.getMainParametars().getTemp()));
    mMaxTemp.setText(String.valueOf(response.getMainParametars().getTemp_max()));
    mMinTemp.setText(String.valueOf(response.getMainParametars().getTemp_min()));

    Object weatherDetail = response.getWeather().get(0);
    LinkedTreeMap linkedTreeMap = (LinkedTreeMap)weatherDetail;
    String iconURL = String.valueOf(linkedTreeMap.get("icon"));

    Picasso.with(this)
        .load("http://openweathermap.org/img/w/" + iconURL + ".png").into(weatherIcon);

  }
}
