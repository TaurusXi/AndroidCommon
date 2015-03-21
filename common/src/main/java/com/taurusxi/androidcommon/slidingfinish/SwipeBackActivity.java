package com.taurusxi.androidcommon.slidingfinish;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;

import org.taurusxi.common.R;

public class SwipeBackActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (needSwipeBack()) {
            SwipeBackLayout layout = (SwipeBackLayout) LayoutInflater.from(this).inflate(
                    R.layout.base, null);
            layout.attachToActivity(this);
        }
    }

    protected boolean needSwipeBack() {
        return true;
    }

}
