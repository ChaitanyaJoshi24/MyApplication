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

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

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

    @OnClick(R.id.btn_submit)
    public void submit() {

        AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner();
        asyncTaskRunner.execute(edtUsername.getText().toString(), edtPassword.getText().toString(),"https://reqres.in/api/login");


    }

    private class AsyncTaskRunner extends AsyncTask<String, Void, String> {

        private Boolean resp;
        ProgressDialog progressDialog;

        protected String doInBackground(String... params) {
            String username = params[0];
            String password = params[1];
            String response = "";

            try {
                // This is getting the url from the string we passed in
                URL url = new URL(params[2]);

                // Create the urlConnection
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();


                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                urlConnection.setRequestProperty("Content-Type", "application/json");

                urlConnection.setRequestMethod("POST");


                // OPTIONAL - Sets an authorization header
                urlConnection.setRequestProperty("Authorization", "someAuthString");

                JSONObject postJsonObject = new JSONObject();
                postJsonObject.put("email", username);
                postJsonObject.put("password", password);

                Log.i("jasonobject",postJsonObject.toString());

                // Send the post body{
                    OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                    writer.write(postJsonObject.toString());
                    writer.flush();


                int statusCode = urlConnection.getResponseCode();

                if (statusCode ==  200) {

                    InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());

                    response = getStringFromInputStream(inputStream);

                    // From here you can convert the string to JSON with whatever JSON parser you like to use
                    // After converting the string to JSON, I call my custom callback. You can follow this process too, or you can implement the onPostExecute(Result) method
                } else {
                    // Status code is not 200
                    // Do something to handle the error
                }

            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }


            // Calls onProgressUpdate()
            return response;
        }


        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();

            if (result != null && !result.equals("")) {

                Log.i("result",result);
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
        protected void onProgressUpdate(Void... text) {
            Toast.makeText(MainActivity.this, "i = " + text[0], Toast.LENGTH_SHORT).show();
            Log.i("mainactivity", "i=" + text[0]);

        }

        public  String getStringFromInputStream(InputStream stream) throws IOException
        {
            int n = 0;
            char[] buffer = new char[1024 * 4];
            InputStreamReader reader = new InputStreamReader(stream, "UTF8");
            StringWriter writer = new StringWriter();
            while (-1 != (n = reader.read(buffer))) writer.write(buffer, 0, n);
            return writer.toString();
        }

    }
}
