package com.chetz.myapplication.data;

import com.chetz.myapplication.models.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("/ChaitanyaJoshi24/MyApplication/productData")
    public Call<ArrayList<Product>> getProductList();


}
