package com.taurusxi.androidcommon.layout;

/**
 * Created by DELL on 2014/12/8.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class HalfLayout extends RelativeLayout {
    public HalfLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public HalfLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HalfLayout(Context context) {
        super(context);
    }

    @SuppressWarnings("unused")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

//        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec)); // Children are just made to fill our space.
//        int childWidthSize = getMeasuredWidth();
//        int childHeightSize = getMeasuredHeight();
//        //高度是宽度的一半
//        widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);
//        heightMeasureSpec = widthMeasureSpec / 2;
//        MLog.e("HalfLayout","width = "+widthMeasureSpec+" hight ="+heightMeasureSpec);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if (mode != MeasureSpec.EXACTLY) {
            throw new IllegalStateException("layout_width must be match_parent");
        }

        int width = MeasureSpec.getSize(widthMeasureSpec);
        // Honor aspect ratio for height but no larger than 2x width.
        int height = width / 2;
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
