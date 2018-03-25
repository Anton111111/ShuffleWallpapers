package com.anton111111.shufflewallpapers.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.anton111111.Log;
import com.anton111111.shufflewallpapers.R;
import com.anton111111.shufflewallpapers.model.Photo;
import com.anton111111.shufflewallpapers.task.GetRandomPhotoTask;
import com.anton111111.shufflewallpapers.view.ErrorOverlay;
import com.anton111111.shufflewallpapers.view.ProgressBarOverlay;
import com.anton111111.shufflewallpapers.view.RemoteImageTouchView;
import com.anton111111.utils.StringUtil;

import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;


/**
 * Use the {@link ShuffleWallpaperFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShuffleWallpaperFragment extends Fragment implements RemoteImageTouchView.Listener {


    private ProgressBarOverlay mainProgressBarOverlayView;
    private GetRandomPhotoTask getRandomPhotoTask;
    private ErrorOverlay errorOverlayView;
    private RelativeLayout rootView;
    private RemoteImageTouchView imageView;

    public ShuffleWallpaperFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShuffleWallpaperFragment.
     */
    public static ShuffleWallpaperFragment newInstance() {
        ShuffleWallpaperFragment fragment = new ShuffleWallpaperFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.rootView = (RelativeLayout) inflater.inflate(R.layout.fragment_shuffle_wallpaper, container, false);
        this.mainProgressBarOverlayView = rootView.findViewById(R.id.fragment_shuffle_wallpaper_main_progress_bar_overlay);
        this.errorOverlayView = new ErrorOverlay(getContext());
        this.imageView = rootView.findViewById(R.id.fragment_shuffle_image);
        this.imageView.setListener(this);
        this.imageView.setDisplayType(ImageViewTouchBase.DisplayType.FIT_HEIGHT);
        errorOverlayView.setOnTryAgainBtnClickListener(() -> loadingRandomPhoto());
        loadingRandomPhoto();
        return rootView;
    }

    private void loadingRandomPhoto() {
        showMainProgressBar();
        hideErrorOverlay();
        getRandomPhotoTask = new GetRandomPhotoTask(getContext());
        getRandomPhotoTask.setListener(photo -> {
            if (photo == null && photo.getUrls() != null &&
                    !(photo.getUrls().containsKey(Photo.URL_CUSTOM) ||
                            photo.getUrls().containsKey(Photo.URL_FULL))) {
                showErrorOverlay();
            } else {
                hideErrorOverlay();
            }

            String urlType = !StringUtil.isEmpty(photo.getUrls().get(Photo.URL_CUSTOM)) ?
                    Photo.URL_CUSTOM : Photo.URL_FULL;
            imageView.load(photo.getUrls().get(urlType));
        });
        getRandomPhotoTask.execute();
    }

    private void showErrorOverlay() {
        rootView.addView(errorOverlayView);
    }

    private void hideErrorOverlay() {
        rootView.removeView(errorOverlayView);
    }

    private void showMainProgressBar() {
        mainProgressBarOverlayView.setVisibility(View.VISIBLE);
    }

    private void hideMainProgressBar() {
        mainProgressBarOverlayView.setVisibility(View.GONE);
    }


    @Override
    public void onResourceReady() {
        hideErrorOverlay();
        hideMainProgressBar();
    }

    @Override
    public void onLoadFailed() {
        showErrorOverlay();
        hideMainProgressBar();
    }

    @Override
    public void onDetach() {
        if (getRandomPhotoTask != null) {
            getRandomPhotoTask.cancel(true);
        }
        super.onDetach();
    }
}
