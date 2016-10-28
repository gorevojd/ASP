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
        db.execSQL("pragma foreign_keys = ON;");
        db.execSQL("create table MainTable(" +
            "ID integer primary key autoincrement," +
            "F real," +
            "T text);");

        db.execSQL(
            "create table DependTable(" +
            "ID integer," +
            "T text," +
            "foreign key(ID) references MainTable(ID) on delete cascade);");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists DependTable");
        db.execSQL("drop table if exists MainTable");

        db.execSQL("pragma foreign_keys = ON;");
        db.execSQL("create table MainTable(" +
                "ID integer primary key autoincrement," +
                "F real," +
                "T text);");

        db.execSQL(
                "create table DependTable(" +
                        "ID integer," +
                        "T text," +
                        "foreign key(ID) references MainTable(ID) on delete cascade);");
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
}
