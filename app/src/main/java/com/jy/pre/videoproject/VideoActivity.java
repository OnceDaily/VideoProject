package com.jy.pre.videoproject;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_play;
    private VideoView vv;
    private TextView path;
    private MediaController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        bt_play = findViewById(R.id.bt_screen_shot);
        path = findViewById(R.id.tv_path);
        vv = findViewById(R.id.videoView);
        bt_play.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        play();
    }

    private void play(){
        if(TextUtils.isEmpty(path.getText().toString())){
            Toast.makeText(this,"播放地址为空!",Toast.LENGTH_SHORT).show();
            return;
        }
        controller = new MediaController(this);
        vv.setVideoURI(Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/" + path.getText().toString()));
        Log.d("videoUrl", Environment.getExternalStorageDirectory().getPath()+"/" + path.getText().toString());
        vv.setMediaController(controller);
        controller.setMediaPlayer(vv);

        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                vv.start();
            }
        });

    }
}
