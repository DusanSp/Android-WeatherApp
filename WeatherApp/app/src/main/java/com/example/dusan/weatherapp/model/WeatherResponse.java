package com.example.dusan.weatherapp.model;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherResponse {

    @SerializedName("coord")
    private Coordinate coordinate;
    @SerializedName("weather")
    private ArrayList weatherList;
    @SerializedName("main")
    private Main mainParametars;


    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public ArrayList getWeather() {
        return weatherList;
    }

    public void setWeather(ArrayList weather) {
        this.weatherList = weather;
    }

    public Main getMainParametars() {
        return mainParametars;
    }

    public void setMainParametars(Main mainParametars) {
        this.mainParametars = mainParametars;
    }
}
