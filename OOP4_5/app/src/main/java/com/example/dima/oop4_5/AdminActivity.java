package com.example.dima.oop4_5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    public ListView listView;
    Curse curse;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        listView = (ListView)findViewById(R.id.lvMain);

        Intent ourIntent = this.getIntent();
        Bundle extras = ourIntent.getExtras();
        String CurseString = extras.getString("CurseString");
        curse = CurseManager.DeserializeCurse(CurseString);

        ArrayList<String> WtfNames = new ArrayList<String>();
        for(int i = 0; i < curse.getCursePupils().size(); i++){
            Pupil pup = curse.getCursePupils().get(i);
            StringBuilder sb = new StringBuilder();
            sb.append(pup.getmName());
            sb.append(" ");
            sb.append(pup.getmFamilyName());
            WtfNames.add(sb.toString());
        }

        arrayAdapter = new ArrayAdapter<String>(
                AdminActivity.this,
                android.R.layout.simple_list_item_multiple_choice,
                WtfNames);

        //arrayAdapter.addAll(WtfNames);
        listView.setAdapter(arrayAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                boolean ValueToSet = !((CheckedTextView)view).isChecked();
                listView.setItemChecked(position, ValueToSet);
                listView.isItemChecked(position);
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    public void deleteSelectedClick(MenuItem item){
        SparseBooleanArray ba = listView.getCheckedItemPositions();
        ArrayList<Pupil> tempAL = new ArrayList<Pupil>();
        /*
        for(int i = 0; i < curse.getCursePupils().size(); i++){
            if(ba.valueAt(i) == true){
                tempAL.add(curse.getCursePupils().get(i));
            }
        }
        */
        for(int i = 0; i < ba.size(); i++){
            if(ba.valueAt(i) == true){
                tempAL.add(curse.getCursePupils().get(ba.keyAt(i)));
            }
        }
        curse.getCursePupils().removeAll(tempAL);

        ArrayList<String> Wtf1Names = new ArrayList<String>();
        for(int i = 0; i < curse.getCursePupils().size(); i++){
            Pupil pup = curse.getCursePupils().get(i);
            StringBuilder sb = new StringBuilder();
            sb.append(pup.getmName());
            sb.append(" ");
            sb.append(pup.getmFamilyName());
            Wtf1Names.add(sb.toString());

            arrayAdapter = new ArrayAdapter<String>(
                    AdminActivity.this,
                    android.R.layout.simple_list_item_multiple_choice,
                    Wtf1Names);

            //arrayAdapter.addAll(WtfNames);
            listView.setAdapter(arrayAdapter);
            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        }
    }
}
