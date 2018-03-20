package com.anton111111.shufflewallpapers.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anton111111.shufflewallpapers.R;
import com.anton111111.shufflewallpapers.task.GetRandomPhotoTask;
import com.anton111111.shufflewallpapers.view.ErrorOverlay;
import com.anton111111.shufflewallpapers.view.ProgressBarOverlay;

/**
 * Use the {@link ShuffleWallpaperFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShuffleWallpaperFragment extends Fragment {


    private ProgressBarOverlay mainProgressBarOverlayView;
    private GetRandomPhotoTask getRandomPhotoTask;
    private ErrorOverlay errorOverlayView;

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
        View view = inflater.inflate(R.layout.fragment_shuffle_wallpaper, container, false);
        this.mainProgressBarOverlayView = view.findViewById(R.id.fragment_shuffle_wallpaper_main_progress_bar_overlay);
        this.errorOverlayView = view.findViewById(R.id.fragment_shuffle_wallpaper_error_overlay);
        errorOverlayView.setOnTryAgainBtnClickListener(() -> loadingRandomPhoto());
        loadingRandomPhoto();
        return view;
    }

    private void loadingRandomPhoto() {
        mainProgressBarOverlayView.setVisibility(View.VISIBLE);
        errorOverlayView.setVisibility(View.GONE);
        getRandomPhotoTask = new GetRandomPhotoTask(getContext());
        getRandomPhotoTask.setListener(photo -> {
            if (photo == null) {
                errorOverlayView.setVisibility(View.VISIBLE);
            } else {
                errorOverlayView.setVisibility(View.GONE);
            }
            mainProgressBarOverlayView.setVisibility(View.GONE);
        });
        getRandomPhotoTask.execute();
    }

    @Override
    public void onDetach() {
        if (getRandomPhotoTask != null) {
            getRandomPhotoTask.cancel(true);
        }
        super.onDetach();
    }


}
