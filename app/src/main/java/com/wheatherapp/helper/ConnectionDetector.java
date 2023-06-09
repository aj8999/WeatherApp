package com.wheatherapp.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionDetector {
    private static ConnectionDetector connectionDetector = null;
    private Context context;

    private ConnectionDetector(Context context) {
        this.context = context;
    }

    public static ConnectionDetector getInstance(Context context) {
        if (connectionDetector == null) {
            connectionDetector = new ConnectionDetector(context);
        }
        return connectionDetector;
    }

    public boolean isConnectionAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {

            NetworkInfo networkInfos = connectivityManager.getActiveNetworkInfo();


            if (networkInfos != null && networkInfos.isConnectedOrConnecting()) {
                return true;
            }
        }
        return false;
    }

}