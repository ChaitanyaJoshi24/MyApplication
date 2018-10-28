package com.chetz.myapplication;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
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
        ButterKnife.bind(this);
    }

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

        AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner();
        asyncTaskRunner.execute(edtUsername.getText().toString(), edtPassword.getText().toString());


    }

    private class AsyncTaskRunner extends AsyncTask<String, String, Boolean >{

        private Boolean resp;
        ProgressDialog progressDialog;

        protected Boolean doInBackground(String... params) {
            String username = params[0];
            String password = params[1];

            for (int i = 0; i < 10; i++) {

                try {
                    Thread.sleep(1000);
                    if(i%3==0){
                        publishProgress(""+i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (username.equals(USERNAME) && password.equals(PASSWORD)){

                resp = true;
            }
            else{
                resp = false;
            }
            // Calls onProgressUpdate()
            return resp;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();

            if (result == true) {
                Toast.makeText(MainActivity.this, "valid", Toast.LENGTH_LONG).show();
                SharedPreferences sharedPreferences = getSharedPreferences("myapplication", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("is_login", true);
                editor.commit();

                Intent calculatorIntent = new Intent(MainActivity.this, CalculatorActivity.class);
                startActivity(calculatorIntent);
                finish();

            } else {
                Toast.makeText(MainActivity.this, "invalid", Toast.LENGTH_LONG).show();
            }

        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this,
                    "ProgressDialog",
                    "Wait for 10 seconds");
        }


        @Override
        protected void onProgressUpdate(String... text) {
            Toast.makeText(MainActivity.this, "i = "+text[0], Toast.LENGTH_SHORT).show();
            Log.i("mainactivity","i="+text[0]);

        }

    }
}
