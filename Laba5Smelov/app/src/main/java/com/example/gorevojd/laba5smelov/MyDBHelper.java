package com.example.gorevojd.laba5smelov;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by GOREVOJD on 17.10.2016.
 */

public class MyDBHelper extends SQLiteOpenHelper {
    public MyDBHelper (Context context){
        super(context, "MyDatabase", null, 1);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table MainTable(" +
        "ID integer primary key autoincrement," +
        "F real," +
        "T text);");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists MainTable");
        db.execSQL("create table MainTable (" +
        "ID integer primary key autoincrement," +
        "F real," +
        "T text);");
    }
}
