package com.example.dusan.weatherapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
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
  TextView mPressure;
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
  private ProgressDialog mProgressDialog;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    mWeatherLayout.setVisibility(View.INVISIBLE);

    DataManager dataManager = new DataManager();
    presenter = new Presenter(dataManager, this);
  }

  @Override
  public void showWeatherData(WeatherResponse response) {

    mCurrentTemp.setText(String.valueOf(response.getWeatherData().getTemp()) + "℃");
    mMaxTemp.setText(String.valueOf(response.getWeatherData().getTemp_max()) + "℃");
    mMinTemp.setText(String.valueOf(response.getWeatherData().getTemp_min()) + "℃");
    mPressure.setText(String.valueOf(response.getWeatherData().getPressure()) + " hPa");
    mHumidity.setText(String.valueOf(response.getWeatherData().getHumidity()) + " %");

    Object weatherDetail = response.getWeather().get(0);
    LinkedTreeMap linkedTreeMap = (LinkedTreeMap) weatherDetail;
    String iconURL = String.valueOf(linkedTreeMap.get("icon"));

    mDetail.setText(String.valueOf(linkedTreeMap.get("main")));
    Picasso.with(this)
        .load("http://openweathermap.org/img/w/" + iconURL + ".png").into(weatherIcon);
  }

  @Override
  public void showLoadingDialog() {
    if (mProgressDialog == null) {
      mProgressDialog = new ProgressDialog(this);
      mProgressDialog.setTitle("Weather App");
      mProgressDialog.setMessage("Loading...");
    }
    mProgressDialog.show();
  }

  @Override
  public void hideLoadingDialog() {
    mProgressDialog.hide();
    mWeatherLayout.setVisibility(View.VISIBLE);
  }

  @Override
  public void errorLoadingData() {
    mWeatherLayout.setVisibility(View.INVISIBLE);
    Toast.makeText(this,
        "Error loading data, please try again.",
        Toast.LENGTH_SHORT)
        .show();
  }

  @OnClick(R.id.btn_search)
  public void onClickSearch() {
    String city = mSearchText.getText().toString();
    mSearchText.setText("");
    clearFocusAndHideInput();
    mCityName.setText(city.toUpperCase());
    presenter.getWeatherForCity(city);
  }

  private void clearFocusAndHideInput() {
    mSearchText.clearFocus();
    mSearchText.setFocusable(false);
    mSearchText.setFocusableInTouchMode(false);
    InputMethodManager imm =
        (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
  }

  @OnClick(R.id.search)
  public void onEditTextClick(View v) {
    mSearchText.setFocusableInTouchMode(true);
    mSearchText.setFocusable(true);

    InputMethodManager imm =
        (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.showSoftInput(v, InputMethod.SHOW_EXPLICIT);
  }

  @Override
  protected void onDestroy() {
    mProgressDialog.dismiss();
    presenter.unsubscribe();
    super.onDestroy();
  }
}
