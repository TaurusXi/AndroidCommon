package com.taurusxi.androidcommon.network;

import android.graphics.Bitmap;
import android.view.View;

/**
 * Created on 15/2/25.
 *
 * @author xicheng
 * @email 505591443@qq.com
 * @github https://github.com/TaurusXi
 */
public interface ImageLoaderListener {

    void onLoadingStart();

    void onLoading();

    void onLoadingFail(String url, View imageView, Bitmap errorBitmap);

    void onLoadingComplete(String url, View imageView, Bitmap bitmap);
}
