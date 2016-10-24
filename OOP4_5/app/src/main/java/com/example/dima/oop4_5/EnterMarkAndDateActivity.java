package com.example.dima.oop4_5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EnterMarkAndDateActivity extends AppCompatActivity {

    public int CheckedMark;

    public Button PrevButton;
    public Button NextButton;

    public RadioButton rb1;
    public RadioButton rb2;
    public RadioButton rb3;
    public RadioButton rb4;
    public RadioButton rb5;

    public RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_mark_and_date);

        NextButton = (Button)findViewById(R.id.NextButton3);
        PrevButton = (Button)findViewById(R.id.PrevButton3);

        rb1 = (RadioButton)findViewById(R.id.radioButton5);
        rb2 = (RadioButton)findViewById(R.id.radioButton4);
        rb3 = (RadioButton)findViewById(R.id.radioButton3);
        rb4 = (RadioButton)findViewById(R.id.radioButton2);
        rb5 = (RadioButton)findViewById(R.id.radioButton);
        rg = (RadioGroup)findViewById(R.id.RadioGroup1);


        NextButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent nextIntent = new Intent(EnterMarkAndDateActivity.this, EnterDateActivity.class);
                Bundle extras = getIntent().getExtras();
                if(extras != null){
                    String StName = extras.getString("StudentName");
                    String StFamName = extras.getString("StudentFamilyName");

                    nextIntent.putExtra("StudentName", StName);
                    nextIntent.putExtra("StudentFamilyName", StFamName);
                }
                nextIntent.putExtra("StudentMark", CheckedMark);
                startActivity(nextIntent);
            }
        });

        PrevButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent prevIntent = new Intent(EnterMarkAndDateActivity.this, EnterFamilyNameActivity.class);
                startActivity(prevIntent);
            }
        });

        rb1.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckedMark = 1;
            }
        });

        rb2.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckedMark = 2;
            }
        });

        rb3.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckedMark = 3;
            }
        });

        rb4.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckedMark = 4;
            }
        });

        rb5.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckedMark = 5;
            }
        });
    }
}
