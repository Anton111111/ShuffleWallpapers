package com.anton111111.shufflewallpapers.api;

import com.anton111111.shufflewallpapers.api.model.Photo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface UnsplashAPI {

    String ORIENTATION_LANDSCAPE = "landscape";
    String ORIENTATION_PORTRAIT = "portrait";
    String ORIENTATION_SQUARISH = "squarish";


    String FEATURED_TRUE = "true";
    String FEATURED_FALSE = "false";

    @GET("photos/random")
    Observable<Photo> randomPhotos(
            @Query("client_id") String clientId,
            @Query("query") String query,
            @Query("orientation") String orientation,
            @Query("featured") String featured,
            @Query("w") int width,
            @Query("h") int height,
            @Query("count") int count);
}
