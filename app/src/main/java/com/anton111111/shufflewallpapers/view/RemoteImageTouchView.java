package com.anton111111.shufflewallpapers.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;

/**
 * @author Anton Potekhin (anton.potekhin@gmail.com)
 */
public class RemoteImageTouchView extends ImageViewTouch implements RequestListener<Drawable> {
    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public RemoteImageTouchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RemoteImageTouchView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void load(String url) {
        RequestOptions rOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(getContext())
                .load(url)
                .apply(rOptions)
                .listener(this)
                .into(this);
    }


    @Override
    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
        if (listener != null) {
            listener.onLoadFailed();
        }
        return false;
    }

    @Override
    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
        if (listener != null) {
            listener.onResourceReady();
        }
        return false;
    }

    public interface Listener {
        void onResourceReady();

        void onLoadFailed();
    }
}
