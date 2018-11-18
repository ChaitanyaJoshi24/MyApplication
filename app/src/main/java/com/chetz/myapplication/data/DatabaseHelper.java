package com.chetz.myapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.chetz.myapplication.models.Product;

import java.util.ArrayList;

public class DatabaseHelper {

    private static DatabaseHelper instance;
    private static SQLiteDatabase sqLiteDatabase;

    private DatabaseHelper(){

    }

    public static DatabaseHelper getInstance(Context context){

        if (instance == null){
            instance = new DatabaseHelper();
            sqLiteDatabase = new DatabaseOpenHelper(context).getWritableDatabase();
        }
        return instance;

    }

    public void insertProduct(ArrayList<Product> productArrayList ){

        for (int i = 0; i < productArrayList.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("product_id",productArrayList.get(i).getProductId());
            contentValues.put("product_name", productArrayList.get(i).getName());
            sqLiteDatabase.insert("product",null,contentValues );
        }

    }

    public void getProduct(){

        Cursor productData = sqLiteDatabase.query("product",null,null,null,null,null,null);
        if(productData.moveToFirst()){
            do {

                String productId =productData.getString(productData.getColumnIndex("product_id"));
                String  productName =productData.getString(productData.getColumnIndex("product_name"));
                Log.i("database","detail="  +productId + " , " +productName);

            }
            while (productData.moveToNext());
        }

    }
}
