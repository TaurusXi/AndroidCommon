package com.taurusxi.androidcommon.network;

import com.android.volley.VolleyError;

/**
 * Created on 15/2/13.
 *
 * @author xicheng
 * @email 505591443@qq.com
 * @github https://github.com/TaurusXi
 */
public abstract class SimpleResponce implements NetworkResponce {
    @Override
    public void onError(VolleyError errorResponse, String errorModel) {

    }

    @Override
    public void networkFinish() {

    }
}
