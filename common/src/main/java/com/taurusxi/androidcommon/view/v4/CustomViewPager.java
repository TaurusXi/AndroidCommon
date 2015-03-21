package com.taurusxi.androidcommon.view.v4;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created on 15/2/12.
 *
 * @author xicheng
 * @email 505591443@qq.com
 * @github https://github.com/TaurusXi
 */
public class CustomViewPager extends ViewPager {

    private boolean canScroll = true;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCanScroll(boolean flag) {
        this.canScroll = flag;
    }


    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        // TODO Auto-generated method stub
        if (canScroll) {
            return super.onTouchEvent(arg0);
        } else {
            return false;
        }

    }
}
