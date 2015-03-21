package com.taurusxi.androidcommon.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by DELL on 2014/12/19.
 */
public class MyRelativeLayout extends RelativeLayout {


    public void setHeightChangeListener(HeightChangeListener heightChangeListener) {
        this.heightChangeListener = heightChangeListener;
    }

    private HeightChangeListener heightChangeListener;


    public MyRelativeLayout(Context context) {
        super(context);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (heightChangeListener != null) {
            heightChangeListener.changeHeight(h, oldh);
        }
    }
}
