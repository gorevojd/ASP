package com.example.gorevojd.laba6smelov;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public Button InsertButton;
    public Button DeleteButton;
    public Button UpdateButton;
    public Button SelectButton;
    public Button NextButton;
    public Button ListButton;

    public EditText IdgroupET;
    public EditText FacultyET;
    public EditText CourseET;
    public EditText NameET;
    public EditText HeadET;


    MyDBHelper dbhelper = new MyDBHelper(this);
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = dbhelper.getWritableDatabase();

        InsertButton = (Button)findViewById(R.id.InsertButton1);
        DeleteButton = (Button)findViewById(R.id.DeleteButton1);
        UpdateButton = (Button)findViewById(R.id.UpdateButton1);
        SelectButton = (Button)findViewById(R.id.SelectButton1);
        NextButton = (Button)findViewById(R.id.NextButton1);
        ListButton = (Button)findViewById(R.id.ListButton1);

        IdgroupET = (EditText)findViewById(R.id.idet1);
        FacultyET = (EditText)findViewById(R.id.facet1);
        CourseET = (EditText)findViewById(R.id.courseet1);
        NameET = (EditText)findViewById(R.id.nameet1);
        HeadET = (EditText)findViewById(R.id.headet1);


        /*


IDGROUP 	Идентификатор группы (Первичный ключ)
FACULTY	Факультет
COURSE	Курс
NAME	Наименование группы
HEAD	Староста группы

         */

        InsertButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put("IDGROUP", GetId());
                cv.put("FACULTY", GetFaculty());
                cv.put("COURSE", GetCourse());
                cv.put("NAME", GetName());
                cv.put("HEAD", GetHead());
                long RowId = db.insert("STUDGROUPS", null, cv);
                Log.d("Laba6 insert: ", String.valueOf(RowId));
            }
        });

        DeleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int IdValue = GetId();
                int NumberDeleted = db.delete("STUDGROUPS", "IDGROUP=?", new String[]{String.valueOf(IdValue)});
                Log.d("Laba6 delete where: ", String.format("deleted %d rows", NumberDeleted));
            }
        });

        UpdateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put("IDGROUP", GetId());
                cv.put("FACULTY", GetFaculty());
                cv.put("COURSE", GetCourse());
                cv.put("NAME", GetName());
                cv.put("HEAD", GetHead());
                int c = db.update("STUDGROUPS", cv, "IDGROUP=?", new String[]{String.valueOf(GetId())});
                Log.d("Laba6 update where: ", "Number of changed rows: " + String.valueOf(c));
                SetValues(GetId(), GetFaculty(), GetCourse(), GetName(), GetHead());
            }
        });

        SelectButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int IdValue = GetId();
                Cursor c = db.rawQuery("SELECT IDGROUP, FACULTY, COURSE, NAME, HEAD FROM STUDGROUPS WHERE IDGROUP=?", new String[]{String.format("%d", IdValue)});
                //Cursor c = db.query("STUDGROUPS", new String[]{"IDGROUP", "FACULTY", "COURSE", "NAMEk","HEAD"},
                 //       "IDGROUR=?", new String[]{String.valueOf(IdValue)}, null, null, null);
                String FacultyValue = new String();
                int CurseValue  = -1;
                String NameValue = new String();
                String HeadValue = new String();
                if (c.moveToFirst()) {
                    FacultyValue = c.getString(1);
                    CurseValue = c.getInt(2);
                    NameValue = c.getString(3);
                    HeadValue = c.getString(4);
                }
                SetValues(IdValue, FacultyValue, CurseValue, NameValue, HeadValue);
            }
        });

        NextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent wtfIntent = new Intent(MainActivity.this, StudActivity.class);
                startActivity(wtfIntent);
            }
        });

        ListButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent wtfIntent = new Intent(MainActivity.this, StudentListActivity.class);
                wtfIntent.putExtra("GroupId", GetId());
                startActivity(wtfIntent);
            }
        });
    }

    public int GetId(){
        int RetVal;
        if(IdgroupET.getText().toString().length() > 0){
            RetVal = Integer.valueOf(IdgroupET.getText().toString());
        }
        else{
            RetVal = -1;
        }
        return RetVal;
    }

    public String GetFaculty(){
        return FacultyET.getText().toString();
    }

    public int GetCourse(){
        int RetVal;
        if(CourseET.getText().toString().length() > 0){
            RetVal = Integer.valueOf(CourseET.getText().toString());
        }
        else{
            RetVal = -1;
        }
        return RetVal;
    }

    public String GetName(){
        return NameET.getText().toString();
    }

    public String GetHead(){
        return HeadET.getText().toString();
    }

    void SetValues(int id, String Fac, int Course, String Name, String Head){
        IdgroupET.setText(String.valueOf(id));
        FacultyET.setText(Fac);
        CourseET.setText(String.valueOf(Course));
        NameET.setText(Name);
        HeadET.setText(Head);
    }
}
