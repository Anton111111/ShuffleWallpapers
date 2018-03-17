package com.anton111111.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.ContextCompat;

/**
 * Network util
 *
 * @author Anton Potekhin (anton.potekhin@gmail.com)
 */
public class NetworkUtil {

    /**
     * Check if active connection is connected
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {
        if (ContextCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            throw new SecurityException("Permission Manifest.permission.ACCESS_NETWORK_STATE is required");
        }

        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        @SuppressLint("MissingPermission") NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork == null) {
            return false;
        }
        return activeNetwork.isConnected();
    }

    /**
     * Check if connection via WIFI
     *
     * @return
     */
    public static boolean isConnectedViaWifi(Context context) {

        if (ContextCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            throw new SecurityException("Permission Manifest.permission.ACCESS_NETWORK_STATE is required");
        }
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        @SuppressLint("MissingPermission") NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnected() &&
                activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
    }


}
