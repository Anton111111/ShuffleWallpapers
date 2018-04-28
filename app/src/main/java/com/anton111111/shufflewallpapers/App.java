package com.anton111111.shufflewallpapers;

import android.app.Application;

import com.anton111111.shufflewallpapers.repository.PhotoRepository;

public class App extends Application {

    public static PhotoRepository photoRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        photoRepository = new PhotoRepository(this);
    }

    public static PhotoRepository getPhotoRepository() {
        return photoRepository;
    }
}
