package com.anton111111;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;


/**
 * @author Anton Potekhin (anton.potekhin@gmail.com)
 */

public class Preferences {
    private static final String PREFERENCES_NAME = "general_preferences";

    private static boolean IS_DEBUGGABLE = false;
    private static Preferences instance;
    private SharedPreferences preferences;
    private String curVersion = "";


    public static boolean isEmulator() {
        return (Build.HARDWARE.equals("ranchu") || Build.HARDWARE.equals("goldfish")) &&
                (Build.PRODUCT.startsWith("google_sdk_") || Build.PRODUCT.startsWith("sdk_"));
    }


    public static Preferences getInstance(Context context) {
        if (instance == null) {
            instance = new Preferences(context);
        }

        return instance;
    }

    public static void setIsDebuggable(boolean isDebuggable) {
        IS_DEBUGGABLE = isDebuggable;
    }

    public static boolean isDebuggable() {
        return IS_DEBUGGABLE;
    }

    public String getAppVersion() {
        return curVersion;
    }

    public Preferences(Context context) {
        preferences = context.getSharedPreferences(PREFERENCES_NAME,
                android.content.Context.MODE_PRIVATE);


        try {
            PackageInfo pInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            if (pInfo != null &&
                    pInfo.versionName != null &&
                    pInfo.versionName.length() > 0) {
                curVersion = pInfo.versionName;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

}
