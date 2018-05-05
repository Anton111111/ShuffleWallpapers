package com.anton111111.shufflewallpapers;


import com.anton111111.Log;
import com.anton111111.shufflewallpapers.di.DaggerAppComponent;
import com.anton111111.shufflewallpapers.repository.PhotoRepository;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;


public class App extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends App> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
