package com.taurusxi.androidcommon.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.CycleInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.sefford.circularprogressdrawable.CircularProgressDrawable;

import org.taurusxi.common.R;

/**
 * Created on 15/3/3.
 *
 * @author xicheng
 * @email 505591443@qq.com
 * @github https://github.com/TaurusXi
 */
public class CirCleProgress extends FrameLayout {


    CircularProgressDrawable drawable;
    private ImageView circleImageView;
    private Animator currentAnimator;
    private Animator stopAnimator;
    private boolean isFinishFlated = false;

    public CirCleProgress(Context context) {
        this(context, null);
    }

    public CirCleProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CirCleProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        drawable = new CircularProgressDrawable.Builder()
                .setRingWidth(getResources().getDimensionPixelSize(R.dimen.drawable_ring_size))
                .setOutlineColor(getResources().getColor(android.R.color.transparent))
                .setRingColor(getResources().getColor(android.R.color.holo_green_light))
                .setCenterColor(getResources().getColor(android.R.color.holo_blue_dark))
                .create();
        circleImageView = (ImageView) LayoutInflater.from(context).inflate(R.layout.circle_view_info, this, false);
        circleImageView.setImageDrawable(drawable);
        currentAnimator = preparePulseAnimation();
        stopAnimator = prepareStopAnimation();
    }

    private Animator prepareStopAnimation() {

        Animator animation = ObjectAnimator.ofFloat(circleImageView, ALPHA,
                1.0f, 0f);
        animation.setDuration(450);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.addListener(new Animator.AnimatorListener() {
            @Override public void onAnimationStart(Animator animation) {

            }

            @Override public void onAnimationEnd(Animator animation) {
                circleImageView.setVisibility(GONE);
            }

            @Override public void onAnimationCancel(Animator animation) {

            }

            @Override public void onAnimationRepeat(Animator animation) {

            }
        });
        return animation;

    }

    @TargetApi(21)
    public CirCleProgress(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        isFinishFlated = true;
        addView(circleImageView);
        currentAnimator.start();
    }

    public void startProgress() {
        if (isFinishFlated) {
            currentAnimator.start();
            circleImageView.setVisibility(VISIBLE);
        }
    }


    public void stopProgress() {
        currentAnimator.cancel();
    }

    @Override protected void onDetachedFromWindow() {
        currentAnimator.cancel();
        super.onDetachedFromWindow();
    }

    /**
     * This animation will make a pulse effect to the inner circle
     *
     * @return Animation
     */
    private Animator preparePulseAnimation() {

        AnimatorSet animatorSet = new AnimatorSet();
        Animator alphaAnimator = ObjectAnimator.ofFloat(circleImageView, ALPHA,
                0.0f, 1.0f);
        alphaAnimator.setDuration(450);
        alphaAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        Animator animation = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.CIRCLE_SCALE_PROPERTY,
                drawable.getCircleScale(), 0.60f);
        animation.setDuration(1000 * 10000);
        animation.setInterpolator(new CycleInterpolator(10000));
        animation.addListener(new Animator.AnimatorListener() {
            @Override public void onAnimationStart(Animator animation) {

            }

            @Override public void onAnimationEnd(Animator animation) {
            }

            @Override public void onAnimationCancel(Animator animation) {
                if (stopAnimator != null) {
                    stopAnimator.start();
                }
            }

            @Override public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.playTogether(alphaAnimator, animation);
        return animatorSet;
    }

}
