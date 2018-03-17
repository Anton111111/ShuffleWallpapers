package com.anton111111.utils;

import android.app.Activity;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.view.Display;

import java.io.IOException;


/**
 * Wallpaper util
 *
 * @author Anton Potekhin (anton.potekhin@gmail.com)
 */
public class WallpaperUtil {

    public static boolean setWallpaper(Activity activity, Bitmap wallBitmap) {

        //get screen height
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenHeight = size.y;

        //calculate width
        int width = wallBitmap.getWidth();
        width = (width * screenHeight) / wallBitmap.getHeight();

        WallpaperManager myWallpaperManager = WallpaperManager.getInstance(activity.getApplicationContext());
        try {
            myWallpaperManager.setBitmap(Bitmap.createScaledBitmap(wallBitmap, width, screenHeight, true));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
