package com.anton111111.shufflewallpapers.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.anton111111.shufflewallpapers.R;
import com.anton111111.shufflewallpapers.view.ErrorOverlay;
import com.anton111111.shufflewallpapers.view.ProgressBarOverlay;
import com.anton111111.shufflewallpapers.viewmodel.ShuffleWallpaperViewModel;
import com.anton111111.shufflewallpapers.viewmodel.ViewModelFactory;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;


/**
 * Use the {@link ShuffleWallpaperFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShuffleWallpaperFragment extends Fragment {

    @Inject
    ViewModelFactory viewModelFactory;
    ShuffleWallpaperViewModel shuffleWallpaperViewModel;

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
        // ViewModelFactory viewModelFactory = new ViewModelFactory(App.getPhotoRepository());
        shuffleWallpaperViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ShuffleWallpaperViewModel.class);
        // Inflate the layout for this fragment
        this.rootView = (RelativeLayout) inflater.inflate(R.layout.fragment_shuffle_wallpaper, container, false);
        this.mainProgressBarOverlayView = rootView.findViewById(R.id.fragment_shuffle_wallpaper_main_progress_bar_overlay);
        this.errorOverlayView = new ErrorOverlay(getContext());
        errorOverlayView.setOnTryAgainBtnClickListener(() -> loadingRandomPhoto());
        loadingRandomPhoto();
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        AndroidSupportInjection.inject(this);
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
