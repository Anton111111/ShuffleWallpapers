package com.anton111111.shufflewallpapers.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.anton111111.shufflewallpapers.R;

/**
 * @author Anton Potekhin (anton.potekhin@gmail.com)
 */
public class ProgressBarOverlay extends RelativeLayout {
    public ProgressBarOverlay(Context context) {
        this(context, null);
        initView();
    }

    public ProgressBarOverlay(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressBarOverlay(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.view_progress_bar_overlay, this);
    }
}
