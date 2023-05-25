package com.wheatherapp;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.wheatherapp.databinding.ActivitySelectMapsBinding;
import com.wheatherapp.helper.AddressDb;
import com.wheatherapp.helper.GPSTracker;
import com.wheatherapp.helper.GeoAddress;

import java.io.IOException;
import java.util.List;

public class SelectMapsActivity extends FragmentActivity implements OnMapReadyCallback {
    static int REQUEST_LOCATION = 2;
    private static String[] ACCESS_GEO_LOCATION = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private GoogleMap mMap;
    private ActivitySelectMapsBinding binding;

    Double MapIdleLat, MapIdleLng;
    private static String stringFinalLatitude = "", stringFinalLongitude = "";
    public static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 100;
    // PlaceAutocompleteFragment autocompleteFragment;
    String UserAddress = "", CountryName = "", LandMark = "", PostCode = "", StreetRoad = "";
    GPSTracker gps;
    Context context;
    String stringLatit = "", stringLongi = "";
    Double latitude, longitude;
    AddressDb db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySelectMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db=new AddressDb(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        context = SelectMapsActivity.this;
        gps = new GPSTracker(context);
        boolean bLocation = doesUserHaveLocationPermission();
        if (!gps.canGetLocation()) {

        } else if (!bLocation) {

            verifyLocationPermissions(SelectMapsActivity.this);
        }else {
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

            stringLatit = String.valueOf(latitude);
            stringLongi = String.valueOf(longitude);


        }
    }
    private boolean doesUserHaveLocationPermission() {
        int result = checkCallingOrSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private static void verifyLocationPermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.BROADCAST_SMS);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, ACCESS_GEO_LOCATION, REQUEST_LOCATION);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;

        GPSTracker gps = new GPSTracker(SelectMapsActivity.this);

        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();

        final LatLng latLng = new LatLng(latitude, longitude);
        CameraUpdate center =
                CameraUpdateFactory.newLatLng(latLng);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(12.0f);  ///7.0f
        mMap.moveCamera(center);
        mMap.animateCamera(zoom);
        //  mMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        Geocoder geocoder1 = new Geocoder(SelectMapsActivity.this);


        stringFinalLatitude = String.valueOf(latitude);
        stringFinalLongitude = String.valueOf(longitude);

        try {
            List<Address> addressList = geocoder1.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (addressList != null && addressList.size() > 0) {
                String locality = addressList.get(0).getAddressLine(0);
                String country = addressList.get(0).getCountryName();
                UserAddress = addressList.get(0).getAddressLine(0);
                CountryName = addressList.get(0).getCountryName();
                LandMark = addressList.get(0).getLocality();
                PostCode = addressList.get(0).getPostalCode();
                StreetRoad = addressList.get(0).getSubLocality();
                try {
                    if (!locality.isEmpty() && !country.isEmpty()) {
                        binding.etAddress.setText(locality + "  " + country);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        // Enabling MyLocation Layer of Google Map
        //   this.mMap.setMyLocationEnabled(true);
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear();
                //   mMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
//                mMap.addMarker(new MarkerOptions().position(latLng).title("Custom location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
//                garageLocation = latLng;
//                LatLng latLng = mMap.getCameraPosition().target;
                Geocoder geocoder = new Geocoder(SelectMapsActivity.this);
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12), 2000, null);

                try {
                    List<Address> addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    if (addressList != null && addressList.size() > 0) {
                        String locality = addressList.get(0).getAddressLine(0);
                        String country = addressList.get(0).getCountryName();

                        UserAddress = addressList.get(0).getAddressLine(0);
                        CountryName = addressList.get(0).getCountryName();
                        LandMark = addressList.get(0).getLocality();
                        PostCode = addressList.get(0).getPostalCode();
                        StreetRoad = addressList.get(0).getSubLocality();

                        System.out.println("map_locality:" + locality);
                        System.out.println("map_country:" + country);
                        System.out.println("map_latLng_latitude:" + latLng.latitude);
                        System.out.println("map_latLng_longitude:" + latLng.longitude);
                        System.out.println("map_addressList.get(0).getLocality():" + addressList.get(0).getLocality() + "_" + addressList.get(0).getLocale());

                        try {
                            if (!locality.isEmpty() && !country.isEmpty()) {
                                binding.etAddress.setText(locality + "  " + country);


                                stringFinalLatitude = String.valueOf(latLng.latitude);
                                stringFinalLongitude = String.valueOf(latLng.longitude);

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });


        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                MapIdleLat = mMap.getCameraPosition().target.latitude;
                MapIdleLng = mMap.getCameraPosition().target.longitude;
                String currentAddress = GeoAddress.getCompleteAddressString(SelectMapsActivity.this, MapIdleLat, MapIdleLng);
                binding.etAddress.setText(currentAddress);

                stringFinalLatitude = String.valueOf(MapIdleLat);
                stringFinalLongitude = String.valueOf(MapIdleLng);


                Geocoder geocoder = new Geocoder(SelectMapsActivity.this);

                try {
                    List<Address> addressList = geocoder.getFromLocation(MapIdleLat, MapIdleLng, 1);
                    if (addressList != null && addressList.size() > 0) {
                        String locality = addressList.get(0).getAddressLine(0);
                        String country = addressList.get(0).getCountryName();

                        UserAddress = addressList.get(0).getAddressLine(0);
                        CountryName = addressList.get(0).getCountryName();
                        LandMark = addressList.get(0).getLocality();
                        PostCode = addressList.get(0).getPostalCode();
                        StreetRoad = addressList.get(0).getSubLocality();

                        System.out.println("map_locality:" + locality);
                        System.out.println("map_country:" + country);
                        System.out.println("map_latLng_latitude:" + latLng.latitude);
                        System.out.println("map_latLng_longitude:" + latLng.longitude);
                        System.out.println("map_addressList.get(0).getLocality():" + addressList.get(0).getLocality() + "_" + addressList.get(0).getLocale());

                        try {
                            if (!locality.isEmpty() && !country.isEmpty()) {
                                //  etAddress.setText(locality + "  " + country);


                                stringFinalLatitude = String.valueOf(MapIdleLat);
                                stringFinalLongitude = String.valueOf(MapIdleLng);

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        binding.rlMylocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12), 2000, null);

            }
        });

        binding.btnAddressConfrm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean insert=db.insert(UserAddress,stringFinalLatitude,stringFinalLongitude);
                if (insert==true){
                   finish();
                }

            }
        });
    }



}