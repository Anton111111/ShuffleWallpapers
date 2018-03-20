package com.anton111111.shufflewallpapers.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anton111111.shufflewallpapers.R;

/**
 * @author Anton Potekhin (anton.potekhin@gmail.com)
 */
public class ErrorOverlay extends RelativeLayout {
    private boolean tryAgainBtnVisibility = true;
    private String errorMessage;
    private AppCompatButton tryAgainBtnView;
    private TextView errorMessageView;
    private OnTryAgainBtnClickListener onTryAgainBtnClickListener;

    public void setOnTryAgainBtnClickListener(OnTryAgainBtnClickListener onTryAgainBtnClickListener) {
        this.onTryAgainBtnClickListener = onTryAgainBtnClickListener;
    }

    public ErrorOverlay(Context context) {
        super(context);
        initView();
    }

    public ErrorOverlay(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ErrorOverlay,
                0, 0);

        try {
            this.errorMessage = a.getString(R.styleable.ErrorOverlay_eo_error_message);
            if (errorMessage == null) {
                errorMessage = context.getString(R.string.view_error_overlay_error_message);
            }

            this.tryAgainBtnVisibility = a.getBoolean(
                    R.styleable.ErrorOverlay_eo_try_again_btn_visibility, true);
        } finally {
            a.recycle();
        }
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.view_error_overlay, this);
        this.tryAgainBtnView = this.findViewById(R.id.view_error_overlay_try_again_btn);
        this.errorMessageView = this.findViewById(R.id.view_error_overlay_try_again_btn);

        errorMessageView.setText(errorMessage);

        if (!tryAgainBtnVisibility) {
            tryAgainBtnView.setVisibility(View.GONE);
        }
        tryAgainBtnView.setOnClickListener(v -> {
            if (onTryAgainBtnClickListener != null) {
                onTryAgainBtnClickListener.onClick();
            }
        });
    }

    public void setErrorMessage(String errorMessage) {
        if (errorMessage == null) {
            throw new IllegalArgumentException("Error message can't be null!");
        }
        this.errorMessage = errorMessage;
        if (errorMessageView != null) {
            errorMessageView.setText(errorMessage);
        }
    }

    public interface OnTryAgainBtnClickListener {
        void onClick();
    }
}
