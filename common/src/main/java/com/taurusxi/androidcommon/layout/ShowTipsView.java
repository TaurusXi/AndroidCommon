package com.taurusxi.androidcommon.layout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * @author Frederico Silva (fredericojssilva@gmail.com)
 * @date Oct 31, 2014
 */
public class ShowTipsView extends FrameLayout {
    private Point showhintPoints = new Point();
    private int radius = 60;

    public TipsClickCallBack getTipsClickCallBack() {
        return tipsClickCallBack;
    }

    public void setTipsClickCallBack(TipsClickCallBack tipsClickCallBack) {
        this.tipsClickCallBack = tipsClickCallBack;
    }

    private TipsClickCallBack tipsClickCallBack;

    private int screenX, screenY;

    private int title_color, description_color, background_color;
    private int circleColor = -1;

    public int getCircleColor() {
        return circleColor;
    }

    public void setCircleColor(int circleColor) {
        this.circleColor = circleColor;
    }

    public ShowTipsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ShowTipsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShowTipsView(Context context) {
        super(context);
        init();
    }

    private void init() {
        this.setBackgroundColor(Color.TRANSPARENT);
//        this.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // DO NOTHING
//                // HACK TO BLOCK CLICKS
//                MLog.e("TAG", "正在点击ShowTipsView");
//            }
//        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // Get screen dimensions
        screenX = w;
        screenY = h;
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*
         * Draw circle and transparency background
		 */

        Bitmap bitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas temp = new Canvas(bitmap);
        Paint paint = new Paint();
        if (background_color != 0)
            paint.setColor(background_color);
        else
            paint.setColor(Color.parseColor("#000000"));
        paint.setAlpha(220);
        temp.drawRect(0, 0, temp.getWidth(), temp.getHeight(), paint);

        Paint transparentPaint = new Paint();
        transparentPaint.setColor(getResources().getColor(android.R.color.transparent));
        transparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        int x = showhintPoints.x;
        int y = showhintPoints.y;
        temp.drawCircle(x, y, radius, transparentPaint);

        canvas.drawBitmap(bitmap, 0, 0, new Paint());

        Paint circleline = new Paint();
        circleline.setStyle(Paint.Style.STROKE);
        if (circleColor != -1)
            circleline.setColor(circleColor);
        else
            circleline.setColor(Color.RED);
        circleline.setAntiAlias(true);
        circleline.setStrokeWidth(2);
        canvas.drawCircle(x, y, radius, circleline);
    }


    public void setTarget(final int x, final int y, final int radius) {
        Point p = new Point(x, y);
        showhintPoints = p;
        this.radius = radius;
        invalidate();
        setOnTouchListener(new OnTouchListener() {
            private boolean isOntouch = false;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        float xPoint = event.getX();
                        float yPoint = event.getY();
                        boolean xFlag = xPoint <= x + radius/2 && xPoint >= x-radius/2;
                        boolean yFlag = yPoint <= y + radius/2 && xPoint >= y-radius/2;
                        if (xFlag && yFlag) {
                            isOntouch = false;
                            if (tipsClickCallBack != null) {
                                tipsClickCallBack.callback();
                            }
                        } else {
                            isOntouch = true;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        isOntouch = false;
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        isOntouch = true;
                        break;
                }
                return isOntouch;
            }
        });
    }


    public interface TipsClickCallBack {

        void callback();

    }
}
