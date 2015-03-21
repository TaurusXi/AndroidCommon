package com.taurusxi.androidcommon.layout;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created on 15/3/20.
 *
 * @author xicheng
 * @email 505591443@qq.com
 * @github https://github.com/TaurusXi
 */
public class TagLinearLayout extends LinearLayout {

    private Context context;

    public TagLinearLayout(Context context) {
        this(context, null, 0);
    }

    public TagLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public TagLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
    }

    public void setTagList(List<Tag> list) {

        removeAllViews();
        int width = getWidth();
        if (list != null && !list.isEmpty()) {
            int nowWidth = getWidth();
            for (Tag tag : list) {
                LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.leftMargin = 10;
                layoutParams.rightMargin = 10;
                TextView textView = new TextView(context);
                textView.setTextSize(18f);
                textView.setText(tag.text);
                textView.setSingleLine(true);
                textView.setBackgroundColor(tag.backgroundColor);
                textView.setPadding(5, 2, 5, 2);
                textView.setOnClickListener(tag.onClickListener);
                textView.setTextColor(Color.WHITE);
                textView.setLayoutParams(layoutParams);
                float v = textView.getPaint().measureText(tag.text);
                v += 30;
                if (v >= width) {
                    addView(textView);
                } else {
                    if (nowWidth == getWidth()) {
                        LinearLayout linearLayout = new LinearLayout(context);
                        LayoutParams linearLayoutLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        linearLayoutLayoutParams.bottomMargin = 10;
                        linearLayout.setLayoutParams(linearLayoutLayoutParams);
                        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                        addView(linearLayout);
                        linearLayout.addView(textView);
                        nowWidth = (int) (nowWidth - v);
                    } else if (nowWidth >= v) {
                        int childCount = getChildCount();
                        LinearLayout childAt = (LinearLayout) getChildAt(childCount - 1);
                        childAt.addView(textView);
                        nowWidth = (int) (nowWidth - v);
                    } else {
                        nowWidth = getWidth();
                        LinearLayout linearLayout = new LinearLayout(context);
                        LayoutParams linearLayoutLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        linearLayoutLayoutParams.bottomMargin = 10;
                        linearLayout.setLayoutParams(linearLayoutLayoutParams);
                        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                        addView(linearLayout);
                        linearLayout.addView(textView);
                        nowWidth = (int) (nowWidth - v);
                    }
                }

                Log.e("tag", "width:" + nowWidth + "___v:" + v);
            }
        }
    }


    public static class Tag {

        public String text;

        public int backgroundColor;

        public OnClickListener onClickListener;


        public Tag(String text, int backgroundColor, OnClickListener onClickListener) {
            this.text = text;
            this.backgroundColor = backgroundColor;
            this.onClickListener = onClickListener;
        }

    }


}
