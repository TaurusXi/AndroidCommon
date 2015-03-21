package com.taurusxi.androidcommon.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class FileUtil {


    private static final String TAG = "TaurusXi";
    private static final File parentPath = Environment.getExternalStorageDirectory();
    private static String storagePath = "";
    private static final String DST_FOLDER_NAME = "Valent";
    private static String cachePath = "";
    private static String sdPath = "";

    /**
     * 初始化保存路径
     *
     * @return
     */
    private static String initPath() {
        if (storagePath.equals("")) {
            storagePath = parentPath.getAbsolutePath() + "/" + DST_FOLDER_NAME;
            File f = new File(storagePath);
            if (!f.exists()) {
                f.mkdir();
            }
        }
        return storagePath;
    }

    /**
     * 保存Bitmap到Cache
     *
     * @param b
     */
    public static void saveCacheBitmap(Context context, Bitmap b, String picName) {

        try {
            File file = new File(getCachePath(context, picName));
            FileOutputStream fout = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fout);
            b.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            bos.flush();
            bos.close();
            // 最后通知图库更新
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 保存Bitmap到sdcard
     */
    public static boolean saveSDFile(InputStream is, String pic) {
        String jpegName = getSDPath(pic);
        return saveFile(is, jpegName);
    }


    public static String getCachePath(Context context, String picName) {
        if (picName == null) {
            return "";
        }
        String path = initCachePath(context);
        String messageDigest = Md5Utils.getMessageDigest(picName.getBytes());
        return path + "/" + messageDigest ;
    }

    public static String getSDPath(String fileName){
        if (fileName==null){
            return "";
        }
        String path = initPath();
        String messageDigest = Md5Utils.getMessageDigest(fileName);
        return path + "/" + messageDigest ;
    }


    public static String initCachePath(Context context) {
        if (cachePath==null||cachePath.equals("")){
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }


    public static boolean saveFile(InputStream is, String fileName) {
        BufferedOutputStream bos = null;
        try {
            FileOutputStream fout = new FileOutputStream(fileName);
            bos = new BufferedOutputStream(fout);
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = is.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            bos.flush();
            bos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return false;
    }


}
