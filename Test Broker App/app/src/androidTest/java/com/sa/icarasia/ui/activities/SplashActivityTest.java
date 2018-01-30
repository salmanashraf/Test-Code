package com.sa.icarasia.ui.activities;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.widget.Button;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

import static org.junit.Assert.*;

/**
 * Created by salman on 30/01/2018.
 */
public class SplashActivityTest {


    @Rule
    public ActivityTestRule<SplashActivity> splashActivityTestRule = new ActivityTestRule<SplashActivity>(SplashActivity.class);

    private int splashScreenWaitingTime = 5000;

    @Test
    public void testOpenNextActivity() {
        // register next activity that need to be monitored.
        final Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);

        // open current activity.
        SplashActivity myActivity = new SplashActivity();

        myActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                HomeActivity nextActivity = (HomeActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
                assertNotNull(nextActivity);
                nextActivity .finish();
            }
        });



    }
}