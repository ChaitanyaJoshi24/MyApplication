package com.chetz.myapplication;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("/ChaitanyaJoshi24/MyApplication/productData")
    public Call<ArrayList<Product>> getProductList();


}
