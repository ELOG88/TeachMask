package com.qust.teachmask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Joker on 2016-11-28.
 */

public class CallBackActivity extends Activity {
    private Callback mCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callback);
    }
    public void btnBack(View v){
        Intent intentData = new Intent();
        Bundle bundleData = new Bundle();
        bundleData.putString("name","joker");
        intentData.putExtras(bundleData);
        this.setResult(0,intentData);
        finish();
    }
}
