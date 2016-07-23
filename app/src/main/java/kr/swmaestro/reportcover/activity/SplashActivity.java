package kr.swmaestro.reportcover.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import kr.swmaestro.reportcover.R;

/**
 * Created by Yoon-Jisoo on 2016-07-16.
 *
 */
public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(1500);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.e(TAG, "ERROR: " + e);
                }
            }
        };
        timerThread.start();
    }
}
