package com.jy.pre.videoproject;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.io.File;

import io.vov.vitamio.Vitamio;
import io.vov.vitamio.utils.Log;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;


public class VitmioActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView tv;
    private VideoView vv;
    private MediaController controller;
    private Button play;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitmio);

        tv = findViewById(R.id.tv_path);
        vv = findViewById(R.id.videoView);
        play = findViewById(R.id.bt_screen_shot);

//        play.setOnClickListener(this);
        if (Vitamio.initialize(this)) {
            vv.setVideoURI(Uri.parse("rtsp://192.168.0.113/475848.sdp"));
//            vv.setVideoURI(Uri.parse(Environment.getExternalStorageDirectory().getPath() + File.separator + tv.getText().toString()));
            Log.d("videoUrl", Environment.getExternalStorageDirectory().getPath() + File.separator + tv.getText().toString());
//            vv.setVideoPath(Environment.getExternalStorageDirectory().getPath()+"/" + tv.getText().toString());
//            android.util.Log.d("videoUrl", Environment.getExternalStorageDirectory().getPath()+"/" + tv.getText().toString());
//            controller = new MediaController(this);
//            vv.setMediaController(controller);
//            controller.setMediaPlayer(vv);
            vv.requestFocus();
        }
    }

    @Override
    public void onClick(View view) {
        if (Vitamio.initialize(this)) {
            vv.setVideoURI(Uri.parse("rtsp://192.168.0.113:554/475848.sdp"));
//            vv.setVideoURI(Uri.parse(Environment.getExternalStorageDirectory().getPath() + File.separator + tv.getText().toString()));
            Log.d("videoUrl", Environment.getExternalStorageDirectory().getPath() + File.separator + tv.getText().toString());
//            vv.setVideoPath(Environment.getExternalStorageDirector-y().getPath()+"/" + tv.getText().toString());
//            android.util.Log.d("videoUrl", Environment.getExternalStorageDirectory().getPath()+"/" + tv.getText().toString());
            controller = new MediaController(this);
//            vv.setMediaController(controller);
//            controller.setMediaPlayer(vv);
            vv.requestFocus();
        }
    }

    private void text(){
        boolean isScreenOn = true;
        VideoActivity us = null;
        boolean isShowingAndNotHidden = false;
        boolean isShowing = true;
        us = new VideoActivity();

        boolean keygroudActivity = (us == null ? false : (isScreenOn ? isShowingAndNotHidden : isShowing));
    }
}
