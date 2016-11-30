package com.qust.calltip;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.qust.teachmask.R;

import static android.content.ContentValues.TAG;

/**
 * Created by Joker on 2016-11-30.
 */

public class CallTip extends Activity implements View.OnClickListener {
    private WindowManager wm = null;
    private MoveRelativeLayout relativeLayout = null;
    private View view = null;
    private Button btnCloseCallTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calltip);

        wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.call_tip_layout, null);
        btnCloseCallTip = (Button)view.findViewById(R.id.btnCloseCallTip);
        btnCloseCallTip.setOnClickListener(this);
        relativeLayout = new MoveRelativeLayout(this);
        relativeLayout.addView(view);
    }

    public void CallTip(View v) {

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (wm != null) {

                    WindowManager.LayoutParams params = new WindowManager.LayoutParams();
                    params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
                    params.format = PixelFormat.RGBA_8888;// 设置图片格式，效果为背景透明
                    params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                    params.x = 0;
                    params.y = 50;
                    params.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    params.width = (int) getResources().getDimension(R.dimen.tipWidth);
                    // params.width = WindowManager.LayoutParams.WRAP_CONTENT;
                    // params.height = (int)getResources().getDimension(R.dimen.tipHeight);
                    params.gravity = Gravity.CENTER | Gravity.TOP;
                    try {
                        relativeLayout.setParames(params);
                        wm.addView(relativeLayout, params);
                        Log.e(TAG, "来电弹屏成功...");
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        Log.e(TAG, "来电弹屏崩溃...", e);
                    }
                } else {
                    Log.e(TAG, "====== 来电弹屏错误 =========");
                }
            }
        }, 1000);


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCloseCallTip:
                wm.removeViewImmediate(relativeLayout);
                break;

        }

    }
}
