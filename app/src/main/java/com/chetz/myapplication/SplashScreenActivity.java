package com.chetz.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    private final int SPLASH_DURATION = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences sharedPreferences = getSharedPreferences("myapplication", MODE_PRIVATE);
                boolean isLogin = sharedPreferences.getBoolean("is_login", false);

                if(isLogin == true){
                    Intent calculatorIntent = new Intent(SplashScreenActivity.this, CalculatorActivity.class);
                    startActivity(calculatorIntent);
                }
                else{
                    Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                }
                finish();
            }
        }, SPLASH_DURATION);

    }
}
