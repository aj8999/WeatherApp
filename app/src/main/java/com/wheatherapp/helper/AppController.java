package com.wheatherapp.helper;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;




public class AppController extends Application
{

    public static final String TAG = AppController.class.getSimpleName();
    public static volatile Context mMainContext;
    public static volatile Handler mMainHandler;
    public static volatile LayoutInflater mMainLayoutInflater;
    private static AppController mInstance;
    private RequestQueue mRequestQueue;
    private static Context context;

    private static final String Locale_Preference = "Locale Preference";
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    public static synchronized AppController getInstance() {
        return mInstance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mMainContext = getApplicationContext();
        mMainHandler = new Handler(getMainLooper());

        sharedPreferences = getSharedPreferences(Locale_Preference, Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        mMainLayoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        context = getApplicationContext();

    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueueMax(Request<T> req) {
        req.setTag(TAG);
        req.setRetryPolicy(new DefaultRetryPolicy(12000000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue().add(req);
    }



}
