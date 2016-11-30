package com.qust.teachmask;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Joker on 2016-11-29.
 */

public class GitHubTest extends Activity implements View.OnClickListener{
    private TextView tvGitHubText;
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_githubtest);
        tvGitHubText= (TextView) findViewById(R.id.tvGitHubText);
        tvGitHubText.setText("githubText");
        tvGitHubText.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        tvGitHubText.setText("gb");
    }
}
