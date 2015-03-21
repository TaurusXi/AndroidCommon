package com.taurusxi.androidcommon.utils;

import android.graphics.Color;

/**
 * Created on 15/3/9.
 *
 * @author xicheng
 * @email 505591443@qq.com
 * @github https://github.com/TaurusXi
 */
public class ColorUtil {


    public static int getAlphaColor(int color, float scrollRatio) {
        return Color.argb((int) (scrollRatio * 255f), Color.red(color), Color.green(color), Color.blue(color));
    }
}
