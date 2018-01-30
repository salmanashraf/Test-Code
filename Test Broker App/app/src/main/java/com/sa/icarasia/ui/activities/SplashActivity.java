package com.sa.icarasia.ui.activities;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sa.icarasia.R;
import com.sa.icarasia.ui.activities.base.BaseActivity;
import com.sa.icarasia.utility.SharedObjects;

public class SplashActivity extends BaseActivity {

    private Activity mActivity;
    private static int SPLASH_DISPLAY_LENGTH = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        mActivity = this;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedObjects.getInstance().startNewActivity(mActivity, MainActivity.class, true);

            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
