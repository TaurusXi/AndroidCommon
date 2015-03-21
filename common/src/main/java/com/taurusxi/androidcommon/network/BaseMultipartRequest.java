package com.taurusxi.androidcommon.network;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//import org.apache.http.entity.mime.HttpMultipartMode;

public abstract class BaseMultipartRequest<T> extends Request<T> {
    MultipartEntityBuilder entity = MultipartEntityBuilder.create();
    HttpEntity httpentity;
    private static final String FILE_PART_NAME = "file";
    private static final ContentType UTF_8 = ContentType.create("text/plain", Consts.UTF_8);
    private final Response.Listener<T> mListener;
    private final Map<String, File> mFilePart;
    private final Map<String, String> mStringPart;

    public BaseMultipartRequest(String url,
                                Response.Listener<T> listener, Response.ErrorListener errorListener
    ) {
        super(Method.POST, url, errorListener);

        mListener = listener;
        mFilePart = new HashMap<String, File>();
        this.mStringPart = new HashMap<String, String>();
        entity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
    }

    public void addStringBody(String param, String value) {
        mStringPart.put(param, value);
    }

    public void addFileBody(String param, File value) {
        mFilePart.put(param, value);
    }


    public void buildMultipartEntity() {
        try {
            for (Map.Entry<String, File> entry : mFilePart.entrySet()) {
                entity.addBinaryBody(entry.getKey(), entry.getValue());
            }
            for (Map.Entry<String, String> entry : mStringPart.entrySet()) {
                entity.addTextBody(entry.getKey(), entry.getValue(), UTF_8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getBodyContentType() {
        return httpentity.getContentType().getValue();
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            httpentity = entity.build();
            httpentity.writeTo(bos);
        } catch (IOException e) {
            VolleyLog.e("IOException writing to ByteArrayOutputStream");
        }
        return bos.toByteArray();
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}