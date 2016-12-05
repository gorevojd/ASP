package com.example.gorevojd.lab9ext;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    static final String AUTHORITY = "my.authority";
    static final String STUDENTS_PATH = "STUDENTS";

    public static final String STUD_CONTENT_URI_STRING = "content://"+AUTHORITY+"/"+STUDENTS_PATH;

    public ListView listView;

    ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Bundle extras = getIntent().getExtras();
        int GroupId = extras.getInt("GroupId");

        listView = (ListView)findViewById(R.id.list_view_id);
        ArrayList<String> WtfNames = new ArrayList<String>();
        //Cursor c = db.rawQuery("SELECT NAME FROM STUDENTS WHERE IDGROUP=?", new String[]{String.valueOf(GroupId)});
        Uri uri = Uri.parse(STUD_CONTENT_URI_STRING + "/" + String.valueOf(GroupId));
        Cursor c = getContentResolver().query(uri, null, null, null, null);
        if (c.moveToFirst()) {
            int i = 0;
            WtfNames.add(c.getString(2));
            i++;
            while(c.moveToNext()){
                WtfNames.add(c.getString(2));
                i++;
            }
        }
        arrayAdapter = new ArrayAdapter<String>(ListActivity.this,
                android.R.layout.simple_list_item_1,
                WtfNames);
        listView.setAdapter(arrayAdapter);
    }
}
