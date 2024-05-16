package com.example.classdemo3;

import com.google.gson.annotations.SerializedName;

public class WeatherInfo {

    @SerializedName("obsTime")
    private String obsTime;

    @SerializedName("temp")
    private String temp;

    @SerializedName("feelsLike")
    private String feelsLike;

    @SerializedName("icon")
    private String icon;

    @SerializedName("text")
    private String text;

    @SerializedName("wind360")
    private String wind360;

    @SerializedName("windDir")
    private String windDir;

    // Getters and Setters
    public String getObsTime() {
        return obsTime;
    }

    public String getTemp() {
        return temp;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public String getIcon() {
        return icon;
    }

    public String getText() {
        return text;
    }

    public String getWind360() {
        return wind360;
    }

    public String getWindDir() {
        return windDir;
    }
}
