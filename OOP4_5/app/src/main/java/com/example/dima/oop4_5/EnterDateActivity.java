package com.example.dima.oop4_5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class EnterDateActivity extends AppCompatActivity {

    public int SelectedYear;

    public DatePicker dp;

    public Button PrevButton;
    public Button NextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_date);

        dp = (DatePicker)findViewById(R.id.datePicker);
        NextButton = (Button)findViewById(R.id.NextButton4);
        PrevButton = (Button)findViewById(R.id.PrevButton4);

        NextButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent nextIntent = new Intent(EnterDateActivity.this, VerifyInfoActivity.class);
                Bundle extras = getIntent().getExtras();
                if(extras != null){
                    String StName = extras.getString("StudentName");
                    String StFamName = extras.getString("StudentFamilyName");
                    int StMark = extras.getInt("StudentMark");

                    nextIntent.putExtra("StudentName", StName);
                    nextIntent.putExtra("StudentFamilyName", StFamName);
                    nextIntent.putExtra("StudentMark", StMark);
                }
                nextIntent.putExtra("StudentYear", dp.getYear());
                startActivity(nextIntent);
            }
        });

        PrevButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent prevIntent = new Intent(EnterDateActivity.this, EnterMarkAndDateActivity.class);
                startActivity(prevIntent);
            }
        });

        dp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SelectedYear = dp.getYear();
            }
        });
    }
}
