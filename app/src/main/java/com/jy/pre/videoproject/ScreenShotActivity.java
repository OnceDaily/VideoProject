package com.jy.pre.videoproject;

import android.app.Activity;
import android.graphics.Bitmap;

import android.os.Bundle;

import android.os.Environment;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;



public class ScreenShotActivity extends AppCompatActivity implements View.OnClickListener {


    private Button play;
    private ImageView iv;

    private String url = "http://img1.xiazaizhijia.com/walls/20160927/1920x1200_dec5fdacc3059ca.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screenshot);

        play = findViewById(R.id.bt_screen_shot);
        iv = findViewById(R.id.iv_show);
        play.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
//        Bitmap bitmap = takeScreenShotOfView(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = Util.loadImage(url);
                Util.saveBitmapToLocal(bitmap);
                bitmap = Util.compressBitmap(Environment.getExternalStorageDirectory() + "/screenShot/1554301894556.jpg",400,400);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        iv.setImageBitmap(bitmap);
//                    }
//                });
            }
        }).start();

    }


    public Bitmap takeScreenShotOfView(Activity activity) {
        activity.getWindow().getDecorView().setDrawingCacheEnabled(true);
        Bitmap bitmap = activity.getWindow().getDecorView().getDrawingCache();
        return bitmap;
    }

}
