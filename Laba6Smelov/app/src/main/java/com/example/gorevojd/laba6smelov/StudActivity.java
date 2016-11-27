package com.example.gorevojd.laba6smelov;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StudActivity extends AppCompatActivity {

    public Button InsertButton;
    public Button DeleteButton;
    public Button UpdateButton;
    public Button SelectButton;

    public Button PrevButton;

    public EditText GroupET;
    public EditText IdET;
    public EditText NameET;

    MyDBHelper dbhelper = new MyDBHelper(this);
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud);

        InsertButton = (Button)findViewById(R.id.InsertBut2);
        DeleteButton = (Button)findViewById(R.id.deleteBut2);
        UpdateButton = (Button)findViewById(R.id.updateBut2);
        SelectButton = (Button)findViewById(R.id.SelectBut2);
        PrevButton = (Button)findViewById(R.id.PrevBut2);

        GroupET = (EditText)findViewById(R.id.idGroup2);
        IdET = (EditText)findViewById(R.id.idStudent2);
        NameET = (EditText)findViewById(R.id.idName2);

        db = dbhelper.getWritableDatabase();

        InsertButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    ContentValues cv = new ContentValues();
                    cv.put("IDGROUP", GetGroupId());
                    cv.put("IDSTUDENT", GetStudentId());
                    cv.put("NAME", GetName());
                    long RowId = db.insertOrThrow("STUDENTS", null, cv);
                    Log.d("Laba6 insert: ", String.valueOf(RowId));
                }
                catch(SQLiteException e){
                    Log.d("LAB6", e.getMessage());
                }
            }
        });

        DeleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try{
                    int IdValue = GetStudentId();
                    int NumberDeleted = db.delete("STUDENTS", "IDSTUDENT=?", new String[]{String.valueOf(IdValue)});
                    Log.d("Laba6 delete where: ", String.format("deleted %d rows", NumberDeleted));
                }
                catch(SQLiteException e){
                    Log.d("LAB6", e.getMessage());
                }
            }
        });

        UpdateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try{
                    ContentValues cv = new ContentValues();
                    cv.put("IDGROUP", GetGroupId());
                    cv.put("IDSTUDENT", GetStudentId());
                    cv.put("NAME", GetName());
                    int c = db.update("STUDENTS", cv, "IDSTUDENT=?", new String[]{String.valueOf(GetStudentId())});
                    Log.d("Laba6 update where: ", "Number of changed rows: " + String.valueOf(c));
                    SetValues(GetGroupId(), GetStudentId(), GetName());
                }
                catch(SQLiteException e){
                    Log.d("LAB6", e.getMessage());
                }
            }
        });

        SelectButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                 try{
                    int IdValue = GetStudentId();
                    Cursor c = db.rawQuery("SELECT IDGROUP, IDSTUDENT, NAME FROM STUDENTS WHERE IDSTUDENT=?", new String[]{String.format("%d", IdValue)});
                    //Cursor c = db.query("STUDGROUPS", new String[]{"IDGROUP", "FACULTY", "COURSE", "NAMEk","HEAD"},
                    //       "IDGROUR=?", new String[]{String.valueOf(IdValue)}, null, null, null);
                    int IdGroupValue = -1;
                    String NameValue = new String();
                    if (c.moveToFirst()) {
                        IdGroupValue = c.getInt(0);
                        NameValue = c.getString(2);
                    }
                    SetValues(IdGroupValue, IdValue, NameValue);
                }
                catch(SQLiteException e){
                    Log.d("LAB6", e.getMessage());
                }
            }
        });

        PrevButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent wtfIntent = new Intent(StudActivity.this, MainActivity.class);
                startActivity(wtfIntent);
            }
        });
    }

    public void SetValues(int IdGroup, int IdStud, String StudName){
        GroupET.setText(String.valueOf(IdGroup));
        IdET.setText(String.valueOf(IdStud));
        NameET.setText(StudName);
    }

    public int GetGroupId(){
        int Result;
        if(GroupET.getText().toString().length() > 0){
            Result = Integer.valueOf(GroupET.getText().toString());
        }
        else{
            Result = -1;
        }
        return(Result);
    }

    public int GetStudentId(){
        int Result;
        if(IdET.getText().toString().length() > 0){
            Result = Integer.valueOf(IdET.getText().toString());
        }
        else{
            Result = -1;
        }
        return(Result);
    }

    public String GetName(){
        return NameET.getText().toString();
    }
}
