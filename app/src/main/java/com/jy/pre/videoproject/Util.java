package com.jy.pre.videoproject;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

    public static Bitmap compressBitmap(String pathName , int height, int width){
        Bitmap bitmap = null;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//设置为true，可以获取到图片的宽高，但不占用内存，防止图片太大产生oom;
        BitmapFactory.decodeFile(pathName,options);
        int bitmapHeight = options.outHeight;//原始图片的宽高
        int bitmapWidth = options.outWidth;
        options.inJustDecodeBounds = false;

        int heightSize = bitmapHeight/height;
        int widthSize = bitmapWidth/width;

        options.inSampleSize = (heightSize <= widthSize)? heightSize:widthSize;
        bitmap = BitmapFactory.decodeFile(pathName,options);

        return bitmap;
    }
}
