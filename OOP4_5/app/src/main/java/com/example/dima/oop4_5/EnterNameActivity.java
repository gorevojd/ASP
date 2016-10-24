package com.example.dima.oop4_5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnterNameActivity extends AppCompatActivity {

    public Button PrevButton;
    public Button NextButton;
    public EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_name);

        NextButton = (Button)findViewById(R.id.EnterFamilyName);
        PrevButton = (Button)findViewById(R.id.Prev);
        et = (EditText)findViewById(R.id.editText);

        NextButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent nextIntent = new Intent(EnterNameActivity.this, EnterFamilyNameActivity.class);
                nextIntent.putExtra("StudentName", et.getText().toString());
                startActivity(nextIntent);
            }
        });

        PrevButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent prevIntent = new Intent(EnterNameActivity.this, MainActivity.class);
                startActivity(prevIntent);
            }
        });
    }
}
