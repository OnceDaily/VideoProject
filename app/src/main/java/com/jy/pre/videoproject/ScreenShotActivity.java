package com.jy.pre.videoproject;

import android.graphics.Bitmap;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;



public class ScreenShotActivity extends AppCompatActivity implements View.OnClickListener {


    private Button play;
    private ImageView iv;


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
        Bitmap bitmap = takeScreenShotOfView(view);
        Util.saveBitmapToLocal(bitmap);
        iv.setImageBitmap(bitmap);
    }


    public Bitmap takeScreenShotOfView(View v) {
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache(true);

        Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false); // clear drawing cache
        return b;
    }
}
