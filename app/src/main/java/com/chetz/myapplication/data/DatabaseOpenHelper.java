package com.chetz.myapplication.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;


    private static final String DATABASE_NAME = "myapplication_db";
    private static final String CREATE_TABLE = "create table product (product_id varchar(50), product_name varchar(50), product_description varchar(100), product_weight varchar(10), product_length varchar(10))";



    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
