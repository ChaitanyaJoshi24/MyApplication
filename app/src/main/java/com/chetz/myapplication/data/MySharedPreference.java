package com.chetz.myapplication.data;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class MySharedPreference {
    public static final String KEY_LOGIN = "islogin";
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private static MySharedPreference instance;

    private MySharedPreference() {

    }

    public static MySharedPreference getInstance(Context context) {
        if (instance == null) {
            instance = new MySharedPreference();
            sharedPreferences = context.getSharedPreferences("myapplication", MODE_PRIVATE);
            editor = sharedPreferences.edit();

        }
        return instance;
    }

    public void setBoolean(String key, Boolean value){
        editor.putBoolean(key,value);
        editor.commit();
    }

    public boolean getBoolean(String key){
        boolean result= sharedPreferences.getBoolean(key, false);
        return result;
    }
}


