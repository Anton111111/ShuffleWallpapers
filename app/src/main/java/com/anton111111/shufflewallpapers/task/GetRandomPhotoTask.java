package com.anton111111.shufflewallpapers.task;

import android.content.Context;
import android.os.AsyncTask;

import com.anton111111.shufflewallpapers.api.APIUnsplash;
import com.anton111111.shufflewallpapers.model.Photo;

import java.util.HashMap;

/**
 * @author Anton Potekhin (anton.potekhin@gmail.com)
 */

public class GetRandomPhotoTask extends AsyncTask<Void, Void, Photo> {

    private final Context context;
    private String query;
    private int width = -1;
    private int height = -1;
    private String orientation;
    private boolean featured = false;
    private Listener listener = null;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public GetRandomPhotoTask(Context context) {
        this.context = context;
    }

    public GetRandomPhotoTask(Context context,
                              String query, int width, int height,
                              String orientation,
                              boolean featured) {
        this.context = context;
        this.query = query;
        this.width = width;
        this.height = height;
        this.orientation = orientation;
        this.featured = featured;
    }

    @Override
    protected Photo doInBackground(Void... voids) {
        APIUnsplash apiUnsplash = new APIUnsplash(context);
        Photo photo = apiUnsplash.randomPhoto(query, width, height, orientation, featured);
        return photo;
    }

    @Override
    protected void onPostExecute(Photo photo) {
        if (listener != null) {
            listener.onPostExecute(photo);
        }
    }

    public interface Listener {
        void onPostExecute(Photo photo);
    }
}
