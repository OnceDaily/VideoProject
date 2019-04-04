package com.jy.pre.videoproject;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

public class Util {
    public static void saveBitmapToLocal(Bitmap bitmap){
        File drFile = new File(Environment.getExternalStorageDirectory(),"VideoProject");
        if (!drFile.exists())
            drFile.mkdir();
        File file = new File(drFile,System.currentTimeMillis()+".jpg");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (fos == null) {
                try {
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
