package com.taurusxi.androidcommon.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import org.taurusxi.common.R;

/**
 * Created on 15/3/4.
 *
 * @author xicheng
 * @email 505591443@qq.com
 * @github https://github.com/TaurusXi
 */
public class TrigonFrameLayout extends FrameLayout {

    private static final float DEFAULT_ANGLE_SIZE = 1.0f;
    private static final int DEFAULT_ANGLE_COLOR = Color.WHITE;
    private Path path;
    private Paint paint;
    private Context context;

    private float angleSize;
    private int angleColor;

    public TrigonFrameLayout(Context context) {
        this(context, null, 0);
    }

    public TrigonFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TrigonFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        super.setBackgroundResource(android.R.color.transparent);
        this.context = context;
        init(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public TrigonFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        super.setBackgroundResource(android.R.color.transparent);
        this.context = context;
        init(context, attrs, defStyleAttr);
    }

    public void setAngleSize(float angleSize) {
        this.angleSize = angleSize;
        invalidate();
    }

    public float getAngleSize() {
        return angleSize;
    }

    public void setAngleColor(int angleColor) {
        this.angleColor = angleColor;
        paint.setColor(angleColor);
        invalidate();
    }

    public int getAngleColor() {
        return angleColor;
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TrigonFrameLayout, defStyle, 0);
        angleSize = a.getFloat(R.styleable.TrigonFrameLayout_angle_size, DEFAULT_ANGLE_SIZE);
        angleColor = a.getColor(R.styleable.TrigonFrameLayout_angle_color, DEFAULT_ANGLE_COLOR);
        a.recycle();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(angleColor);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(3);
        path = new Path();
        path.moveTo(0, 0);

    }


//    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int mode = MeasureSpec.getMode(widthMeasureSpec);
//        if (mode != MeasureSpec.EXACTLY) {
//            throw new IllegalStateException("layout_width 必须明确大小");
//        }
//        int width = MeasureSpec.getSize(widthMeasureSpec);
//        int height = (int) (width * angleSize);
//        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas temp = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(angleColor);
        temp.drawRect(0, 0, temp.getWidth(), temp.getHeight(), paint);
        Paint transparentPaint = new Paint();
        transparentPaint.setColor(getResources().getColor(android.R.color.transparent));
        transparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        path.lineTo(temp.getWidth(), 0);
        path.lineTo(temp.getWidth(), temp.getWidth() * angleSize);
        path.close();
        temp.drawPath(path, transparentPaint);
        canvas.drawBitmap(bitmap, 0, 0, new Paint());
//        int height = getHeight();
//        int width = getWidth();
//        path.lineTo(0, height);
//        path.lineTo(width, height);
//        path.close();
//        canvas.drawPath(path, paint);
    }
}
