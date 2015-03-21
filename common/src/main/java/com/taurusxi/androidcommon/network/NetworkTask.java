package com.taurusxi.androidcommon.network;


import com.taurusxi.androidcommon.model.BaseParams;

/**
 * Created on 15/2/13.
 *
 * @author xicheng
 * @email 505591443@qq.com
 * @github https://github.com/TaurusXi
 */
public class NetworkTask {

    public static interface Method {
        int DEPRECATED_GET_OR_POST = -1;
        int GET = 0;
        int POST = 1;
        int PUT = 2;
        int DELETE = 3;
        int HEAD = 4;
        int OPTIONS = 5;
        int TRACE = 6;
        int PATCH = 7;
    }

    public NetworkTask(String url) {
        this.httpUrl = url;
        this.tag = httpUrl;
        this.method = Method.POST;
    }


    public NetworkTask(String url, int method) {
        this.httpUrl = url;
        this.tag = httpUrl;
        this.method = method;
    }

    private String httpUrl;

    private String tag;
    private BaseParams uuParams;
    private int method;

    public NetworkResponce getNetworkResponce() {
        return networkResponce;
    }

    public void setNetworkResponce(NetworkResponce networkResponce) {
        this.networkResponce = networkResponce;
    }

    private NetworkResponce networkResponce;


    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public BaseParams getParams() {
        return uuParams;
    }

    public void setParams(BaseParams uuParams) {
        this.uuParams = uuParams;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }


}
