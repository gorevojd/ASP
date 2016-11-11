package com.example.gorevojd.laba6smelov;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class List2Activity extends AppCompatActivity {

    public ListView listView;

    MyDBHelper dbhelper = new MyDBHelper(this);
    SQLiteDatabase db;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list2);

        db = dbhelper.getWritableDatabase();

        listView = (ListView)findViewById(R.id.ListView4);
        ArrayList<String> WtfNames = new ArrayList<String>();
        db.execSQL("create view TempV as SELECT STUDENTS.IDGROUP, STUDGROUPS.HEAD, COUNT(*)" +
                " FROM STUDENTS JOIN STUDGROUPS" +
                " ON STUDENTS.IDGROUP = STUDGROUPS.IDGROUP" +
                " GROUP BY STUDENTS.IDGROUP, STUDGROUPS.HEAD");


        Cursor c = db.rawQuery("SELECT * FROM TempV;", null);
        if (c.moveToFirst()) {
            int i = 0;
            StringBuilder sb = new StringBuilder();
            sb.append(String.valueOf(c.getInt(0)));
            sb.append(" ");
            sb.append(c.getString(1));
            sb.append(" ");
            sb.append(String.valueOf(c.getInt(2)));
            WtfNames.add(sb.toString());
            i++;
            while(c.moveToNext()){
                StringBuilder sb1 = new StringBuilder();
                sb1.append(String.valueOf(c.getInt(0)));
                sb1.append(" ");
                sb1.append(c.getString(1));
                sb1.append(" ");
                sb1.append(String.valueOf(c.getInt(2)));
                WtfNames.add(sb1.toString());
                i++;
            }
        }

        arrayAdapter = new ArrayAdapter<String>(List2Activity.this,
                android.R.layout.simple_list_item_1,
                WtfNames);
        listView.setAdapter(arrayAdapter);

    }
}
