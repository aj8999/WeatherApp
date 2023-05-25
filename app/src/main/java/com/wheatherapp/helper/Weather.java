package com.wheatherapp.helper;

public class Weather
{

    private static Weather weather=null;
    public  String id,main,description;

    public Weather() {
    }

    public static Weather getWeather() {
        return weather;
    }

    public static void setWeather(Weather weather) {
        Weather.weather = weather;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
