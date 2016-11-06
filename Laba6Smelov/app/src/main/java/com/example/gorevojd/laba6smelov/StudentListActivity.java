package com.example.gorevojd.laba6smelov;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class StudentListActivity extends AppCompatActivity {

    public ListView listView;

    MyDBHelper dbhelper = new MyDBHelper(this);
    SQLiteDatabase db;
    ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        Bundle extras = getIntent().getExtras();
        int GroupId = extras.getInt("GroupId");

        db = dbhelper.getWritableDatabase();

        listView = (ListView)findViewById(R.id.ListView3);
        ArrayList<String> WtfNames = new ArrayList<String>();
        Cursor c = db.rawQuery("SELECT NAME FROM STUDENTS WHERE IDGROUP=?",
                new String[]{String.valueOf(GroupId)});
        if (c.moveToFirst()) {
            int i = 0;
            WtfNames.add(c.getString(0));
            i++;
            while(c.moveToNext()){
                WtfNames.add(c.getString(0));
                i++;
            }
        }
        arrayAdapter = new ArrayAdapter<String>(StudentListActivity.this,
                android.R.layout.simple_list_item_1,
                WtfNames);
        listView.setAdapter(arrayAdapter);
    }
}
