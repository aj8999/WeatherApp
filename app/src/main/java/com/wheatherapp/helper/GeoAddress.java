package com.wheatherapp.helper;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.util.List;
import java.util.Locale;

public class GeoAddress {

    private static final String TAG = "GeoAddress";


    public static String countryName = "";
    public static String stateName = "";
    public static String mPostalCode = "";
    public static String mCountryCode = "";
    public static String address = "";
    public static String city = "";

    public static String getAddressData(Context context, double latitude, double longitude) {
        String city = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());

        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null) {
                Address returnAddresses = addresses.get(0);
                String address = addresses.get(0).getAddressLine(0);
                city = addresses.get(0).getLocality();
                stateName = addresses.get(0).getAdminArea();
                countryName = addresses.get(0).getCountryName();
                mPostalCode = addresses.get(0).getPostalCode();
                mCountryCode = addresses.get(0).getCountryCode();
                address = addresses.get(0).getAddressLine(0);
                city = addresses.get(0).getLocality();

            } else {
                city = "";
                Log.e(TAG, "getCityByLatLong: ");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("location address", "Cannot get Address!");
        }
        return city;
    }


    public static String getCompleteAddressString(Context conetxt, double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(conetxt, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);

                String CompleteAddress = addresses.get(0).getAddressLine(0)
                        + " " + addresses.get(0).getAddressLine(1) + " " + addresses.get(0).getAddressLine(2)
                        + " " + addresses.get(0).getAddressLine(3);

                StringBuilder strReturnedAddress = new StringBuilder("");

                strReturnedAddress.append(returnedAddress.getAddressLine(0)).append("\n");

                strAdd = CompleteAddress.replace("null", "");

//                Log.e("loction address", "" + strReturnedAddress.toString());
            } else {
                Log.e("loction address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("location address", "Cannot get Address!");
        }
        return strAdd;
    }
}
