package com.example.tundepeter.test.utils;

import android.content.Context;
import android.graphics.Point;
import android.os.AsyncTask;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public class ImageLoaderProvider {
    private static Context context;
    private volatile static ImageLoaderProvider instance;
    private static final Point DIMENSION = new Point(480, 800);

    /** Returns singleton class instance */
    public static ImageLoaderProvider getInstance(Context myContext) {
        if (instance == null) {
            synchronized (ImageLoaderProvider.class) {
                context = myContext;
                if (instance == null) {
                    instance = new ImageLoaderProvider();
                }
            }
        }
        return instance;
    }

    public ImageLoader getLoader() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .resetViewBeforeLoading(false)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(DIMENSION.x, DIMENSION.y) // default = device screen dimensions
                .diskCacheExtraOptions(DIMENSION.x, DIMENSION.y, null)
                .taskExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
                .defaultDisplayImageOptions(options)
                .build();

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);
        return imageLoader;
    }
}

