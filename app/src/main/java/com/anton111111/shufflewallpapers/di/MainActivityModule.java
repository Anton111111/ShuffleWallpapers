package com.anton111111.shufflewallpapers.di;

import com.anton111111.shufflewallpapers.ui.fragment.ShuffleWallpaperFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = ShuffleWallpaperFragmentModule.class)
    abstract ShuffleWallpaperFragment bindShuffleWallpaperFragment();

}
