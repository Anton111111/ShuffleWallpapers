package com.anton111111.shufflewallpapers.api;

import android.content.Context;

import com.anton111111.Log;
import com.anton111111.api.APIAbstract;
import com.anton111111.shufflewallpapers.R;
import com.anton111111.shufflewallpapers.model.Photo;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.InterruptedIOException;
import java.util.HashMap;

/**
 * @author Anton Potekhin (anton.potekhin@gmail.com)
 */

public class APIUnsplash extends APIAbstract {

    public static final String PARAM_CLIENT_ID = "client_id";
    public static final String PARAM_ORIENTATION = "orientation";
    public static final String PARAM_FEATURED = "featured";
    public static final String PARAM_WIDTH = "w";
    public static final String PARAM_HEIGHT = "h";
    public static final String PARAM_QUERY = "query";
    public static final String PARAM_COUNT = "count";

    public static final String ORIENTATION_LANDSCAPE = "landscape";
    public static final String ORIENTATION_PORTRAIT = "portrait";
    public static final String ORIENTATION_SQUARISH = "squarish";

    public static final String FEATURED_TRUE = "true";
    public static final String FEATURED_FALSE = "false";

    private String clientId = "8f445da2f55f814ff28cd2a0d18e8f8c778d8a53b18f0bc6706b3fe508b83ece";

    public APIUnsplash(Context context) {
        super(context);
        this.userAgent = context.getString(R.string.user_agent);
    }

    public Photo randomPhoto(String query, int width, int height,
                             String orientation,
                             boolean featured) {
        HashMap<String, String> params = new HashMap<>();
        if (orientation == null || orientation.isEmpty()) {
            orientation = ORIENTATION_LANDSCAPE;
        }
        if (width > 0 && height > 0) {
            params.put(PARAM_WIDTH, width + "");
            params.put(PARAM_HEIGHT, height + "");
            if (width == height) {
                orientation = ORIENTATION_SQUARISH;
            } else if (width > height) {
                orientation = ORIENTATION_LANDSCAPE;
            } else {
                orientation = ORIENTATION_PORTRAIT;
            }
        }
        params.put(PARAM_ORIENTATION, orientation);

        if (query != null && query.trim().isEmpty()) {
            params.put(PARAM_QUERY, query.trim());
        }

        if (featured) {
            params.put(PARAM_FEATURED, FEATURED_TRUE);
        }

        String url = getURL() + "photos/random";

        try {
            String resultString = executeRequest(params, url);
            Photo photo = new GsonBuilder().create().fromJson(resultString, Photo.class);
            return photo;
        } catch (APIException | InterruptedIOException | JsonSyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected String getURL() {
        return "https://api.unsplash.com/";
    }

    @Override
    protected String getRequestMethod() {
        return REQUEST_METHOD_GET;
    }

    @Override
    protected String executeRequest(HashMap<String, String> params, String url, String requestMethod) throws APIException, InterruptedIOException {
        if (!params.containsKey(PARAM_CLIENT_ID)) {
            params.put(PARAM_CLIENT_ID, this.clientId);
        }
        return super.executeRequest(params, url, requestMethod);
    }
}
