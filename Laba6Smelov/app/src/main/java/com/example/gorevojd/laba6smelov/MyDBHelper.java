package com.example.gorevojd.laba6smelov;

/**
 * Created by GOREVOJD on 31.10.2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MyDBHelper extends SQLiteOpenHelper {
    public MyDBHelper (Context context){
        super(context, "MyDatabase", null, 1);
    }

    public void onCreate(SQLiteDatabase db){
        InitMyDB(db);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop trigger if exists STUDENTS_TRIGGER_INS");
        db.execSQL("drop trigger if exists STUDENTS_TRIGGER_DEL");
        db.execSQL("drop table if exists STUDENTS");
        db.execSQL("drop table if exists STD");

        InitMyDB(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    static void InitMyDB(SQLiteDatabase db){
        try{

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

            /*
            db.execSQL(
            "create trigger if not exists STUDENTS_TRIGGER_INS " +
                    "before insert " +
                    "on STUDENTS " +
                    "for each row " +
                    "when (SELECT count(*) FROM STUDENTS STD WHERE NEW.IDGROUP = STD.IDGROUP) > 5 " +
                    "begin " +
                    "SELECT RAISE(ABORT, 'INSERT TRIGGER ERROR'); " +
                    "end;"
            );

            db.execSQL(
                    "create trigger if not exists STUDENTS_TRIGGER_DEL " +
                            "before delete " +
                            "on STUDENTS " +
                            "for each row " +
                            "when (SELECT count(*) FROM STUDENTS STD WHERE STD.IDGROUP = OLD.IDGROUP) < 3 and " +
                            "(SELECT count(*) " +
                            "FROM STUDGROUPS WHERE STUDGROUPS.IDGROUP = OLD.IDGROUP) != 0 " +
                            "begin " +
                            "SELECT RAISE(ABORT, 'DELETE TRIGGER ERROR'); " +
                            "end;"
            );
            */

/*
            db.execSQL(
            "create trigger if not exists STUDENTS_TRIGGER_DEL " +
                    "before delete " +
                    "on STUDENTS " +
                    "for each row " +
                    "when (SELECT count(*) FROM STUDENTS STD WHERE STD.IDGROUP = OLD.IDGROUP) < 3 and " +
                    "(SELECT count(*) " +
                    "FROM (SELECT * " +
                            "FROM STUDENTS STD JOIN STUDGROUPS STG " +
                            "ON STD.IDGROUP = STG.IDGROUP) TEMP1 " +
                    "WHERE TEMP1.IDGROUP = OLD.IDGROUP) != 0 " +
                    "begin " +
                    "SELECT RAISE(ABORT, 'DELETE TRIGGER ERROR'); " +
                    "end;"
            );
*/
        }
        catch(SQLiteException e){
            Log.d("LAB6 DB Creation", e.getMessage());
        }
    }
}
