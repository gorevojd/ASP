package com.example.dima.oop4_5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class VerifyInfoActivity extends AppCompatActivity {

    public String Name;
    public String FamilyName;
    public int Mark;
    public int Year;

    public EditText NameET;
    public EditText FamilyNameET;
    public EditText YearET;
    public EditText MarkET;

    public Button ChangeBut;
    public Button FinishBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_info);

        ChangeBut = (Button)findViewById(R.id.ChangeVerifyInfoBut);
        FinishBut = (Button)findViewById(R.id.FinishAddButton);

        NameET = (EditText)findViewById(R.id.EditTextNameVerify);
        FamilyNameET = (EditText)findViewById(R.id.EditTextVerifyFamilyName);
        YearET = (EditText)findViewById(R.id.EditTextVerifyYear);
        MarkET = (EditText)findViewById(R.id.EditTextVerifyMark);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String StName = extras.getString("StudentName");
            NameET.setText(StName);
            String StFamName = extras.getString("StudentFamilyName");
            FamilyNameET.setText(StFamName);
            int StMark = extras.getInt("StudentMark");
            MarkET.setText(String.valueOf(StMark));
            int StYear = extras.getInt("StudentYear");
            YearET.setText(String.valueOf(StYear));
        }

        ChangeBut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent WtfIntent = new Intent(VerifyInfoActivity.this, EnterNameActivity.class);
                startActivity(WtfIntent);
            }
        });

        FinishBut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent nextIntent = new Intent(VerifyInfoActivity.this, MainActivity.class);

                nextIntent.putExtra("StudentName", NameET.getText());
                nextIntent.putExtra("StudentFamilyName", FamilyNameET.getText());
                nextIntent.putExtra("StudentMark", MarkET.getText());
                nextIntent.putExtra("StudentYear", YearET.getText());
                startActivity(nextIntent);
            }
        });
    }
}
