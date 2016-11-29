package com.qust.teachmask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

    private ImageView img;
    private WindowManager windowManager;
    private Button btnOk;
    private TextView tvContext;
    private Context mContext;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        btnOk = (Button) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(this);
        tvContext = (TextView) findViewById(R.id.tvContext);
        windowManager = getWindowManager();
        tvContext.setText(getAppInfo());
        // 动态初始化图层
        img = new ImageView(this);
        img.setLayoutParams(new LayoutParams(
                android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                android.view.ViewGroup.LayoutParams.MATCH_PARENT));
        img.setScaleType(ScaleType.FIT_XY);
        img.setImageResource(R.drawable.ic_ordermaok);

        // 设置LayoutParams参数
        LayoutParams params = new WindowManager.LayoutParams();
        // 设置显示的类型，TYPE_PHONE指的是来电话的时候会被覆盖，其他时候会在最前端，显示位置在stateBar下面，其他更多的值请查阅文档
        params.type = WindowManager.LayoutParams.TYPE_PHONE;
        // 设置显示格式
        params.format = PixelFormat.RGBA_8888;
        // 设置对齐方式
        params.gravity = Gravity.LEFT | Gravity.TOP;
        // 设置宽高
        params.width = ScreenUtils.getScreenWidth(this);
        params.height = ScreenUtils.getScreenHeight(this);

        // 添加到当前的窗口上
        //windowManager.addView(img, params);

        // 点击图层之后，将图层移除
        img.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                windowManager.removeView(img);
            }
        });

    }

    private String getAppInfo() {
        try {
            String pkName = this.getPackageName();
            String versionName = this.getPackageManager().getPackageInfo(
                    pkName, 0).versionName;
            int versionCode = this.getPackageManager()
                    .getPackageInfo(pkName, 0).versionCode;
            return pkName + "   " + versionName + "  " + versionCode;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOk:
                TestCallback tc = new TestCallback(callBack);
                Toast.makeText(MainActivity.this, "跳转到第二个页面了！", Toast.LENGTH_SHORT).show();
                this.startActivityForResult(new Intent(MainActivity.this, CallBackActivity.class), 0);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == 0) {
            System.out.println(data.getExtras().getString("name"));
        }
    }

    private Callback callBack = new Callback() {
        @Override
        public void calcCallBack(int i, int j) {

        }

        @Override
        public void getNameCallBack(String name) {
            Toast.makeText(mContext, "callBackName:" + name, Toast.LENGTH_SHORT).show();
        }
    };

}

