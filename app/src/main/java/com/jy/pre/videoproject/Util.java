package com.jy.pre.videoproject;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static void saveBitmapToLocal(Bitmap bitmap){
        File drFile = new File(Environment.getExternalStorageDirectory(),"VideoProject");
        if (!drFile.exists()){
            drFile.mkdir();
            System.out.print("mkdir success");
        }
        File file = new File(drFile,System.currentTimeMillis()+".jpg");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (fos != null) {
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


    public static void  checkUsePermission(Activity activity, String[] perms, int requestCode){
        List<String> requestPerms = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            for (int i = 0; i < perms.length; i++) {
              if (ContextCompat.checkSelfPermission(activity,perms[i]) != PackageManager.PERMISSION_GRANTED){
                  requestPerms.add(perms[i]);
              }
            }
            if (requestPerms.isEmpty())
                Toast.makeText(activity,"已获取权限",Toast.LENGTH_SHORT).show();
            else {
                String[] permission = requestPerms.toArray(new String[requestPerms.size()]);
                ActivityCompat.requestPermissions(activity,permission,requestCode);
            }
        }
    }

    public static Bitmap loadImage (String imageUrl){
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            InputStream is =  conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }
}
