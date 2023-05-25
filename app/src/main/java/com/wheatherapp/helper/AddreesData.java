package com.wheatherapp.helper;

public class AddreesData {
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AddreesData(int id, String location, String lat, String aLong) {
        this.id = id;
        Location = location;
        Lat = lat;
        Long = aLong;
    }

    public String Location,Lat,Long;


    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String getLong() {
        return Long;
    }

    public void setLong(String aLong) {
        Long = aLong;
    }
}
