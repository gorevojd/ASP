package com.example.gorevojd.lab9ext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public Button ListBut;
    public EditText GroupIDET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListBut = (Button)findViewById(R.id.listButtonId);
        GroupIDET = (EditText)findViewById(R.id.groupIdET);

        ListBut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent wtfIntent = new Intent(MainActivity.this, ListActivity.class);
                wtfIntent.putExtra("GroupId", GetID());
                startActivity(wtfIntent);
            }
        });
    }


    public int GetID(){
        int Result;
        Result = Integer.valueOf(GroupIDET.getText().toString());
        return(Result);
    }
}
