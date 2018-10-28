package com.chetz.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.edt_username)
    EditText edtUsername;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "pass";

    public static final String TAG = "mainactivity::";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        edtUsername = findViewById(R.id.edt_username);
//        edtPassword = findViewById(R.id.edt_password);
//        btnSubmit = findViewById(R.id.btn_submit);
        ButterKnife.bind(this);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);

    }

    //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onstart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onresume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onrestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onpause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onstop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "ondestroy");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_submit)
    public void submit() {
        if (edtUsername.getText().toString().equals(USERNAME) && edtPassword.getText().toString().equals(PASSWORD)) {
            Toast.makeText(MainActivity.this, "valid", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "invalid", Toast.LENGTH_LONG).show();
        }

    }
}
