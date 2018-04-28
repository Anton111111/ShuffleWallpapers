package com.anton111111.shufflewallpapers.repository;

import android.content.Context;

import com.anton111111.shufflewallpapers.R;
import com.anton111111.shufflewallpapers.api.UnsplashAPI;
import com.anton111111.shufflewallpapers.api.model.Photo;
import com.anton111111.utils.StringUtil;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotoRepository {


    private final UnsplashAPI unsplashAPI;
    private final String unsplashClientId;

    public PhotoRepository(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.unsplash.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        unsplashAPI = retrofit.create(UnsplashAPI.class);
        unsplashClientId = context.getString(R.string.unsplash_client_id);
    }

    public Observable<Photo> randomPhoto(
            String query,
            String orientation,
            boolean featured,
            int width,
            int height) {

        if (StringUtil.isEmpty(orientation) || !validateOrientation(orientation)) {
            orientation = UnsplashAPI.ORIENTATION_LANDSCAPE;
        }
        if (width > 0 && height > 0) {
            if (width == height) {
                orientation = UnsplashAPI.ORIENTATION_SQUARISH;
            } else if (width > height) {
                orientation = UnsplashAPI.ORIENTATION_LANDSCAPE;
            } else {
                orientation = UnsplashAPI.ORIENTATION_PORTRAIT;
            }
        }

        return unsplashAPI.randomPhotos(
                unsplashClientId,
                query,
                orientation,
                featured ? UnsplashAPI.FEATURED_TRUE : UnsplashAPI.FEATURED_FALSE,
                width,
                height,
                1);
    }

    private boolean validateOrientation(String orientation) {
        return orientation.equals(UnsplashAPI.ORIENTATION_LANDSCAPE) ||
                orientation.equals(UnsplashAPI.ORIENTATION_PORTRAIT) ||
                orientation.equals(UnsplashAPI.ORIENTATION_SQUARISH);
    }
}
