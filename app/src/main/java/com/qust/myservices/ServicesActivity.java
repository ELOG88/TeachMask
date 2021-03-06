package com.qust.myservices;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.qust.teachmask.R;

/**
 * Created by Joker on 2016-11-29.
 */

public class ServicesActivity extends Activity {
    private String LOG_TAG = "ServicesActivity";
    private EditText tvResult;
    private BinderService binderService;
    private ICounterService iCounterService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        Intent bindIntent = new Intent(ServicesActivity.this, BinderService.class);
        bindService(bindIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        tvResult = (EditText) findViewById(R.id.tvResult);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter counterActionFilter = new IntentFilter(BinderService.BROADCAST_COUNTER_ACTION);
        registerReceiver(counterActionReceiver, counterActionFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(counterActionReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }

    /**
     * startService
     *
     * @param v
     */
    public void btnStartService(View v) {
        startService(new Intent(ServicesActivity.this, MyServices.class));

    }

    /**
     * stopService
     *
     * @param v
     */
    public void btnStopService(View v) {
        stopService(new Intent(ServicesActivity.this, MyServices.class));
    }

    /**
     * bindService
     *
     * @param v
     */
    public void btnBindServices(View v) {
        binderService.startCounter(1);
    }

    /**
     * unBindService
     *
     * @param v
     */
    public void btnUnBindServices(View v) {
        binderService.stopCounter();
    }

    /**
     * 计数广播
     */
    private BroadcastReceiver counterActionReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            int counter = intent.getIntExtra(BinderService.COUNTER_VALUE, 0);
            String text = String.valueOf(counter);
            tvResult.setText(text);
            Log.i(LOG_TAG, "-----onReceice----");
        }
    };
    /***
     * 实现ServiceConnection
     */
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BinderService.MyBinder myBinder = (BinderService.MyBinder) service;
            binderService = myBinder.getBinderService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            binderService = null;
        }
    };

    /**
     * .Ctrl+Alt+T：选中一块代码，按此组合键，可快速添加if 、for、try/catch等语句
     */
    public void testCtrlAltT() {
        try {
            do {
                Log.i(LOG_TAG, "testCtrlAltT");
                Toast.makeText(ServicesActivity.this, "name", Toast.LENGTH_SHORT).show();
            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
