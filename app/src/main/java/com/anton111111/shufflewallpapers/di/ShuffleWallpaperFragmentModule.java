package com.anton111111.shufflewallpapers.di;

import android.arch.lifecycle.ViewModel;

import com.anton111111.shufflewallpapers.viewmodel.ShuffleWallpaperViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ShuffleWallpaperFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(ShuffleWallpaperViewModel.class)
    abstract ViewModel bindShuffleWallpaperViewModel
            (ShuffleWallpaperViewModel shuffleWallpaperViewModel);


}
