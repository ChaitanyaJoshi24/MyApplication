package com.chetz.myapplication.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.chetz.myapplication.data.MySharedPreference;
import com.chetz.myapplication.R;

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
    private static final String USERNAME = "cbjoshi_24@outlook.com";
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

    @OnClick(R.id.btn_submit)
    public void submit() {

        String username = edtUsername.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (username.matches(emailPattern))
        {
            AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner();
            asyncTaskRunner.execute(username, edtPassword.getText().toString());
        }
        else
        {
            Toast.makeText(MainActivity.this,"Invalid email address", Toast.LENGTH_SHORT).show();
        }




    }

    private class AsyncTaskRunner extends AsyncTask<String, String, Boolean> {

        private Boolean resp;
        ProgressDialog progressDialog;

        protected Boolean doInBackground(String... params) {
            String username = params[0];
            String password = params[1];


            for (int i = 0; i < 10; i++) {

                try {
                    Thread.sleep(1000);
                    if (i % 3 == 0) {
                        publishProgress("" + i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


//            if (username.matches(emailPattern))
//            {
//                Toast.makeText(MainActivity.this,"valid email address",Toast.LENGTH_SHORT).show();
//            }
//            else
//            {
//                Toast.makeText(MainActivity.this,"Invalid email address", Toast.LENGTH_SHORT).show();
//            }

            if (username.equals(USERNAME) && password.equals(PASSWORD)) {

                resp = true;
            } else {
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

                MySharedPreference.getInstance(MainActivity.this).setBoolean(MySharedPreference.KEY_LOGIN, true);

                Intent calculatorIntent = new Intent(MainActivity.this, ApiCallActivity.class);
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
            Toast.makeText(MainActivity.this, "i = " + text[0], Toast.LENGTH_SHORT).show();
            Log.i("mainactivity", "i=" + text[0]);

        }

    }

//        public  String getStringFromInputStream(InputStream stream) throws IOException
//        {
//            int n = 0;
//            char[] buffer = new char[1024 * 4];
//            InputStreamReader reader = new InputStreamReader(stream, "UTF8");
//            StringWriter writer = new StringWriter();
//            while (-1 != (n = reader.read(buffer))) writer.write(buffer, 0, n);
//            return writer.toString();
//        }
//
//    }
}
