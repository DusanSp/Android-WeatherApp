package com.example.dusan.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dusan.weatherapp.model.DataManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataManager dataManager = new DataManager();
        dataManager.getWeather();
    }
}
