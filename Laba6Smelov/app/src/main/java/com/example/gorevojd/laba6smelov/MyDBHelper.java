package com.example.gorevojd.laba6smelov;

/**
 * Created by GOREVOJD on 31.10.2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDBHelper extends SQLiteOpenHelper {
    public MyDBHelper (Context context){
        super(context, "MyDatabase", null, 1);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("pragma foreign_keys = ON;");
        db.execSQL("create table STUDGROUPS(" +
                "IDGROUP integer primary key autoincrement," +
                "FACULTY text," +
                "COURSE integer," +
                "NAME text," +
                "HEAD text);");

        db.execSQL(
                "create table STUDENTS(" +
                        "IDGROUP integer," +
                        "IDSTUDENT integer primary key," +
                        "NAME text," +
                        "foreign key(IDGROUP) references STUDGROUPS(IDGROUP) on delete cascade on update cascade);");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists STUDENTS");
        db.execSQL("drop table if exists STD");

        db.execSQL("pragma foreign_keys = ON;");
        db.execSQL("create table STUDGROUPS(" +
                "IDGROUP integer primary key autoincrement," +
                "FACULTY text," +
                "COURSE integer," +
                "NAME text," +
                "HEAD text);");

        db.execSQL(
                "create table STUDENTS(" +
                        "IDGROUP integer," +
                        "IDSTUDENT integer," +
                        "NAME text," +
                        "foreign key(IDGROUP) references STUDGROUPS(IDGROUP) on delete cascade on update cascade);");
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
}
