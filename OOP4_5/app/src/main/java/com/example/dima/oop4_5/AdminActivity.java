package com.example.dima.oop4_5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        listView = (ListView)findViewById(R.id.lvMain);

        Intent ourIntent = this.getIntent();
        Bundle extras = ourIntent.getExtras();
        String CurseString = extras.getString("CurseString");
        Curse curse = CurseManager.DeserializeCurse(CurseString);

        ArrayList<String> WtfNames = new ArrayList<String>();
        for(int i = 0; i < curse.getCursePupils().size(); i++){
            Pupil pup = curse.getCursePupils().get(i);
            StringBuilder sb = new StringBuilder();
            sb.append(pup.getmName());
            sb.append(" ");
            sb.append(pup.getmFamilyName());
            WtfNames.add(sb.toString());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                AdminActivity.this,
                R.layout.list_item_xml,
                WtfNames);
        arrayAdapter.addAll(WtfNames);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return true;
            }
        });
    }
}
