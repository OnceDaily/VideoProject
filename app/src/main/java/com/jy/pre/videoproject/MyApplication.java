package com.jy.pre.videoproject;

import android.Manifest;
import android.app.Application;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import java.security.Permission;

public class MyApplication extends Application {

    private static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
    }


    public Application getInstance(){

        if (application == null) {
            application = new MyApplication();
        }

        return application;
    }

    public void checkUsePermission(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
           int permissionCheck =  ContextCompat.checkSelfPermission(this, Manifest.permission_group.STORAGE);

        }


    }

}
