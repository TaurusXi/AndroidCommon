package com.taurusxi.androidcommon.utils;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * Created on 15/3/2.
 *
 * @author xicheng
 * @email 505591443@qq.com
 * @github https://github.com/TaurusXi
 */
public class AnimationUtils {


    public static void translateColor(final View view, int defaultColor, int newColor) {
        translateColor(view, defaultColor, newColor, null);
    }


    public static void translateColor(final View view, int defaultColor, int newColor, ValueAnimator.AnimatorUpdateListener listener) {


        final ValueAnimator colorAnim = ObjectAnimator.ofObject(new ArgbEvaluator(),
                defaultColor, newColor);
        colorAnim.setDuration(1500);
        if (listener == null) {
            listener = new ValueAnimator.AnimatorUpdateListener() {
                @Override public void onAnimationUpdate(ValueAnimator animation) {
                    view.setBackgroundColor((Integer) colorAnim.getAnimatedValue());
                }
            };
        }
        colorAnim.addUpdateListener(listener);
        colorAnim.start();
    }

}
