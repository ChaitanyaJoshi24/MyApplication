package com.chetz.myapplication.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chetz.myapplication.data.MySharedPreference;
import com.chetz.myapplication.R;

public class SplashScreenActivity extends AppCompatActivity {

    private final int SPLASH_DURATION = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                boolean isLogin = MySharedPreference.getInstance(SplashScreenActivity.this).getBoolean(MySharedPreference.KEY_LOGIN);

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
