package com.logic.tsg.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.logic.tsg.R;
import com.logic.tsg.ui.auth.LoginActivity;
import com.logic.tsg.ui.main.ParameterGraphActivity;

import dagger.android.support.DaggerAppCompatActivity;

import static com.logic.tsg.util.Constant.token;

public class SplashActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (token!=null) {

                }
                else {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
            }
        },2000);
    }
}
