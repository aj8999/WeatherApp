package com.wheatherapp.helper;

public class WeatherSubModel {
    private static WeatherSubModel weatherSubModel =null;
    public  String currentTime,time,temp,humidity,minTemp,maxTemp,pressure,windSpeed,tempDescription,image;

    public WeatherSubModel(String currentTime, String time, String temp, String humidity, String minTemp, String maxTemp, String pressure, String windSpeed, String tempDescription) {
        this.currentTime = currentTime;
        this.time = time;
        this.temp = temp;
        this.humidity = humidity;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.pressure = pressure;
        this.windSpeed = windSpeed;

        this.tempDescription = tempDescription;

    }

    public WeatherSubModel() {
    }

    public String getTempDescription() {
        return tempDescription;
    }

    public void setTempDescription(String tempDescription) {
        this.tempDescription = tempDescription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static WeatherSubModel getWeatherModified() {
        return weatherSubModel;
    }

    public static void setWeatherModified(WeatherSubModel weatherSubModel) {
        WeatherSubModel.weatherSubModel = weatherSubModel;
    }


    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }
}