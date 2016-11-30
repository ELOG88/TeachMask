package com.qust.myservices;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Joker on 2016-11-29.
 */

public class MyServices extends Service {
  private static  final  String TAG="MyServices";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"MyServices------onCreate");
        System.out.println("MyServices------onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"MyServices-----onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"MyServices-----onDestroy");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"MyServices------onBind");
        return null;
    }
}
