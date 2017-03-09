package com.example.dusan.weatherapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
  @BindView(R.id.pressure)
  TextView mPressuer;
  @BindView(R.id.humidity)
  TextView mHumidity;
  @BindView(R.id.search)
  EditText mSearchText;
  @BindView(R.id.city)
  TextView mCityName;
  @BindView(R.id.weather)
  LinearLayout mWeatherLayout;
  @BindView(R.id.detail)
  TextView mDetail;

  private Presenter presenter;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
    DataManager dataManager = new DataManager();
    presenter = new Presenter(dataManager, this);
  }

  @Override
  public void showWeatherData(WeatherResponse response) {

    mCurrentTemp.setText(String.valueOf(response.getMainParametars().getTemp()) + "℃");
    mMaxTemp.setText(String.valueOf(response.getMainParametars().getTemp_max()) + "℃");
    mMinTemp.setText(String.valueOf(response.getMainParametars().getTemp_min()) + "℃");
    mPressuer.setText(String.valueOf(response.getMainParametars().getPressure()) + " hPa");
    mHumidity.setText(String.valueOf(response.getMainParametars().getHumidity()) + " %");

    Object weatherDetail = response.getWeather().get(0);
    LinkedTreeMap linkedTreeMap = (LinkedTreeMap) weatherDetail;
    String iconURL = String.valueOf(linkedTreeMap.get("icon"));

    mDetail.setText(String.valueOf(linkedTreeMap.get("main")));
    Picasso.with(this)
        .load("http://openweathermap.org/img/w/" + iconURL + ".png").into(weatherIcon);
  }

  @OnClick(R.id.btn_search)
  public void onClickSearch() {
    String city = mSearchText.getText().toString();
    mSearchText.setText("");
    mSearchText.clearFocus();
    mWeatherLayout.setVisibility(View.VISIBLE); // TODO: Prebaciti u metodu kaja gasi loader indikator
    mCityName.setText(city);
    presenter.getData(city);
  }

  @Override
  protected void onDestroy() {
    presenter.unsubscribe();
    super.onDestroy();
  }
}
