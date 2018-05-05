package com.anton111111.shufflewallpapers.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.anton111111.shufflewallpapers.repository.PhotoRepository;

import javax.inject.Inject;


public class ShuffleWallpaperViewModel extends ViewModel {


    private final PhotoRepository photoRepository;

    @Inject
    ShuffleWallpaperViewModel(PhotoRepository photoRepository) {
        super();
        this.photoRepository = photoRepository;

    }
}
