package com.chetz.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_call);

        getproduct();

        //ApiCallActivity.AsyncTaskRunner asyncTaskRunner = new ApiCallActivity.AsyncTaskRunner();
        //   asyncTaskRunner.execute("https://my-json-server.typicode.com/ChaitanyaJoshi24/MyApplication/productData");
    }

//    private class AsyncTaskRunner extends AsyncTask<String, Void, String> {
//
//        private Boolean resp;
//        ProgressDialog progressDialog;
//
//        protected String doInBackground(String... params) {
////            String username = params[0];
////            String password = params[1];
//            String response = "";
//
//            try {
//                // This is getting the url from the string we passed in
//                URL url = new URL(params[0]);
//
//                // Create the urlConnection
//                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//
//
//               // urlConnection.setDoInput(true);
//                //urlConnection.setDoOutput(true);
//
//                urlConnection.setRequestProperty("Content-Type", "application/json");
//
//                urlConnection.setRequestMethod("GET");
//
//
//                // OPTIONAL - Sets an authorization header
//                urlConnection.setRequestProperty("Authorization", "someAuthString");
//
//                //JSONObject postJsonObject = new JSONObject();
////                postJsonObject.put("email", username);
////                postJsonObject.put("password", password);
//
////                Log.i("jasonobject",postJsonObject.toString());
////
////                // Send the post body{
////                OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
////                writer.write(postJsonObject.toString());
////                writer.flush();
//
//
//                int statusCode = urlConnection.getResponseCode();
//
//                if (statusCode ==  200) {
//
//                    InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
//
//                    response = getStringFromInputStream(inputStream);
//
//                    // From here you can convert the string to JSON with whatever JSON parser you like to use
//                    // After converting the string to JSON, I call my custom callback. You can follow this process too, or you can implement the onPostExecute(Result) method
//                } else {
//                    // Status code is not 200
//                    // Do something to handle the error
//                }
//
//            } catch (Exception e) {
//               // Log.d(TAG, e.getLocalizedMessage());
//            }
//
//            // Calls onProgressUpdate()
//            return response;
//        }
//
//
//        @Override
//        protected void onPostExecute(String result) {
//            // execution of result of Long time consuming operation
//            progressDialog.dismiss();
//
//
//            try {
//                JSONArray productData = new JSONArray(result);
//
//                ArrayList<Product> productArrayList=new ArrayList<Product>();
//
//                for(int i=0;i<productData.length();i++){
//
//                    Product product = new Product();
//
//                    JSONObject allData = productData.getJSONObject(i);
//
//                    String firstName = allData.getString("name");
//                    product.setName(firstName);
//
//                    Log.i("result",firstName);
//
//                    String url = allData.getString("web");
//                    product.setWeb(url);
//                    Log.i("result",url);
//
//                    JSONArray image = allData.getJSONArray("images");
//                    ArrayList<String> imageArrayList = new ArrayList<>();
//                    for(int j=0;j<image.length();j++){
//                        String imagNumber = image.getString(j);
//                        imageArrayList.add(imagNumber);
//
//                        Log.i("result",imagNumber);
//                    }
//                    product.setImages(imageArrayList);
//
//                    JSONArray tag = allData.getJSONArray("tags");
//                    for(int j=0;j<tag.length();j++){
//                        String tagNumber = tag.getString(j);
//                        Log.i("result",tagNumber);
//                    }
//
//                    WarehouseLocation location = new WarehouseLocation();
//                    JSONObject warehouse = allData.getJSONObject("warehouseLocation");
//                    double latitude = warehouse.getDouble("latitude");
//                    location.setLatitude(latitude);
//                    double longitude = warehouse.getDouble("longitude");
//                    location.setLongitude(longitude);
//
//                    product.setLocation(location);
//                    Log.i("result", String.valueOf(latitude));
//                    Log.i("result", String.valueOf(longitude));
//
//                    productArrayList.add(product);
//
//                }
//
//                for (int i = 0; i < productArrayList.size(); i++) {
//
//                    Log.i("name",productArrayList.get(i).getName());
//                }
//
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//
////            if (result != null && !result.equals("")) {
////
//
////                Toast.makeText(MainActivity.this, "valid", Toast.LENGTH_LONG).show();
////
////                MySharedPreference.getInstance(MainActivity.this).setBoolean(MySharedPreference.KEY_LOGIN, true);
////
////                Intent calculatorIntent = new Intent(MainActivity.this, CalculatorActivity.class);
////                startActivity(calculatorIntent);
////                finish();
////
////            } else {
////                Toast.makeText(MainActivity.this, "invalid", Toast.LENGTH_LONG).show();
////            }
//
//        }
//
//
//        @Override
//        protected void onPreExecute() {
//            progressDialog = ProgressDialog.show(ApiCallActivity.this,
//                    "ProgressDialog",
//                    "Wait for 10 seconds");
//        }
//
//
//        @Override
//        protected void onProgressUpdate(Void... text) {
//            Toast.makeText(ApiCallActivity.this, "i = " + text[0], Toast.LENGTH_SHORT).show();
//            Log.i("mainactivity", "i=" + text[0]);
//
//        }
//
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

    private void getproduct() {

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ArrayList<Product>> call = apiInterface.getProductList();

        call.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                ArrayList<Product> productArrayList = response.body();

                Log.i("apicall", "test = " + productArrayList.size());
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {

                t.printStackTrace();
            }
        });
    }
}
