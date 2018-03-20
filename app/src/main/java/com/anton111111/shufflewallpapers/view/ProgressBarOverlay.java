package com.anton111111.shufflewallpapers.view;

import android.content.Context;
import android.widget.RelativeLayout;

import com.anton111111.shufflewallpapers.R;

/**
 * @author Anton Potekhin (anton.potekhin@gmail.com)
 */
public class ProgressBarOverlay extends RelativeLayout {
    public ProgressBarOverlay(Context context) {
        super(context);
        initView();
    }


    private void initView() {
        inflate(getContext(), R.layout.view_progress_bar_overlay, this);
    }
}
