package com.example.a97053.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String db_name = "info.db";
    public static final String table_name = "info";
    public static final String col_name = "NAME";
    public static final String col_email = "EMAIL";
    public static final String col_pass = "PASSWORD";
    public DatabaseHelper(Context context) {
        super(context,db_name,null,1);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + table_name + "(NAME String, EMAIL String, PASSWORD String)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists "+ table_name );
        onCreate(db);
    }

    public void insert(String name,String email,String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col_name,name);
        cv.put(col_email,email);
        cv.put(col_pass,password);
        long res = db.insert(table_name,null,cv);

    }

}
