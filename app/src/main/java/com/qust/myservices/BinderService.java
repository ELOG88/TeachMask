package com.qust.myservices;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Joker on 2016-11-29.
 */

public class BinderService extends Service implements ICounterService{
    private  String TAG="BinderService";
    private boolean stop = false;
    public final static String BROADCAST_COUNTER_ACTION = "broadcast_counter_action";
    public final static String COUNTER_VALUE = "counter_value";
    private  final  IBinder iBinder = new MyBinder();
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"-----onBind");
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG,"-----onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG,"-----onRebind");
        super.onRebind(intent);
    }

    public  class MyBinder extends Binder{

        public  BinderService getBinderService(){
            return  BinderService.this;
        }
    }

    @Override
    public void startCounter(int initVal) {
        AsyncTask<Integer, Integer, Integer> task = new AsyncTask<Integer, Integer, Integer>() {
            @Override
            protected Integer doInBackground(Integer... vals) {
                Integer initCounter = vals[0];

                stop = false;
                while(!stop) {
                    publishProgress(initCounter);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    initCounter++;
                }

                return initCounter;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);

                int counter = values[0];

                Intent intent = new Intent(BROADCAST_COUNTER_ACTION);
                intent.putExtra(COUNTER_VALUE, counter);

                sendBroadcast(intent);
            }

            @Override
            protected void onPostExecute(Integer val) {
//            int counter = val;
//            Intent intent = new Intent(BROADCAST_COUNTER_ACTION);
//            intent.putExtra(COUNTER_VALUE, counter);
//            sendBroadcast(intent);
            }

        };
        task.execute(initVal);
    }




    public void stopCounter() {
        stop = true;
    }


}
