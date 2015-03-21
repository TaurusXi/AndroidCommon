package com.taurusxi.androidcommon.network;

import com.android.volley.VolleyError;

/**
 * Created on 15/2/13.
 *
 * @author xicheng
 * @email 505591443@qq.com
 * @github https://github.com/TaurusXi
 */
public interface NetworkResponce {

    public void onSuccessResponse(String responseData);

    public void onError(VolleyError errorResponse, String errorModel);

    public void networkFinish();
}
