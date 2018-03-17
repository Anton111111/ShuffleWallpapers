package com.anton111111;

/**
 * Log class
 *
 * @author Anton Potekhin (anton.potekhin@gmail.com)
 */

public class Log {

    private static String LOG_TAG = "ANTON111111";

    public static void setLogTag(String logTag) {
        LOG_TAG = logTag;
    }

    public static void d(String msg, Throwable tr) {
        if (Preferences.isDebuggable()) android.util.Log.d(LOG_TAG, msg, tr);
    }

    public static void d(String msg) {
        if (Preferences.isDebuggable()) android.util.Log.d(LOG_TAG, msg);
    }

    public static void v(String msg) {
        if (Preferences.isDebuggable()) android.util.Log.v(LOG_TAG, msg);
    }

    public static void i(String msg) {
        if (Preferences.isDebuggable()) android.util.Log.i(LOG_TAG, msg);
    }

    public static void w(String msg) {
        if (Preferences.isDebuggable()) android.util.Log.w(LOG_TAG, msg);
    }

    public static void e(String msg) {
        android.util.Log.e(LOG_TAG, msg);
    }

    public static void e(String msg, Throwable tr) {
        android.util.Log.e(LOG_TAG, msg, tr);
    }

}
