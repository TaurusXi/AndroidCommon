package com.taurusxi.androidcommon.network;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;

/**
 * Created by taurusxi on 14-8-6.
 */
public class StringMultipartRequest extends BaseMultipartRequest<String> {

    private static final String TAG = StringMultipartRequest.class.getSimpleName();

    public StringMultipartRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    @Override
    protected Response<String> parseNetworkResponse(final NetworkResponse response) {
        try {
            String responceStr = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(responceStr,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }

    }
}
