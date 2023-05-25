package com.wheatherapp.helper;

public class Weather
{

    private static Weather weather=null;
    public  String id,main,description,icon;


    public static Weather getWeatherInstance()
    {
        if(weather==null)
        {
            weather=new Weather();
        }
        return weather;
    }

    public static Weather getWeatherInstance(String id, String main, String description, String icon)
    {
        if(weather==null)
        {
            weather=new Weather( id,  main,  description,  icon);
        }
        return weather;
    }

    public Weather(String id, String main, String description, String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
