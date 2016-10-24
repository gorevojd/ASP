package com.example.dima.oop4_5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnterFamilyNameActivity extends AppCompatActivity {

    public Button PrevButton;
    public Button NextButton;

    public EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_family_name);

        NextButton = (Button)findViewById(R.id.NextButton2);
        PrevButton = (Button)findViewById(R.id.PrevButton2);
        et = (EditText)findViewById(R.id.EditText2);



        NextButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent nextIntent = new Intent(EnterFamilyNameActivity.this, EnterMarkAndDateActivity.class);
                Bundle extras = getIntent().getExtras();
                if(extras != null){
                    String StName = extras.getString("StudentName");
                    nextIntent.putExtra("StudentName", StName);
                }
                nextIntent.putExtra("StudentFamilyName", et.getText().toString());
                startActivity(nextIntent);
            }
        });

        PrevButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent prevIntent = new Intent(EnterFamilyNameActivity.this, EnterNameActivity.class);
                startActivity(prevIntent);
            }
        });
    }
}
