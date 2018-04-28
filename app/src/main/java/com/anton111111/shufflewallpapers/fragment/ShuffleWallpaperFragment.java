package com.anton111111.shufflewallpapers.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.anton111111.shufflewallpapers.R;
import com.anton111111.shufflewallpapers.view.ErrorOverlay;
import com.anton111111.shufflewallpapers.view.ProgressBarOverlay;


/**
 * Use the {@link ShuffleWallpaperFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShuffleWallpaperFragment extends Fragment  {


    private ProgressBarOverlay mainProgressBarOverlayView;
    private ErrorOverlay errorOverlayView;
    private RelativeLayout rootView;

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
        errorOverlayView.setOnTryAgainBtnClickListener(() -> loadingRandomPhoto());
        loadingRandomPhoto();
        return rootView;
    }

    private void loadingRandomPhoto() {
        showMainProgressBar();
        hideErrorOverlay();
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
    public void onDetach() {
        super.onDetach();
    }
}
