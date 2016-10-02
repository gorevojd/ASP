package com.example.gorevojd.laba4smelov;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    private File mFile;
    final static String fileName = "zametki.txt";

    private Button mAddButton;
    private Button mChangeButton;
    private Button mDeleteButton;

    private EditText mEditText;

    private CalendarView mCalendar;

    private Zametki zametki = new Zametki();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mAddButton = (Button)findViewById(R.id.addButton);
        this.mChangeButton = (Button)findViewById(R.id.changeButton);
        this.mDeleteButton = (Button)findViewById(R.id.deleteButton);

        this.mEditText = (EditText)findViewById(R.id.editText);
        this.mCalendar = (CalendarView)findViewById(R.id.calendarView3);

        this.mAddButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                mFile = new File(getFilesDir(), fileName);
                if(CheckIfFileIsExist(mFile) == false){
                    AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                    b.setTitle("File is not exist.\n Press OK to create...").setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    AlertDialog ad = b.create();
                    ad.show();
                    try{
                        mFile.createNewFile();

                    }
                    catch(IOException e){

                    }
                }


            }
        });

        this.mChangeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

            }
        });

        this.mDeleteButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

            }
        });

    }

    private boolean CheckIfFileIsExist(File file){
        boolean retVal;
        if((file.exists() == true) && (file != null)){
            retVal = true;
            Log.d("LABA3", "File Is Existing");
        }
        else{
            retVal = false;
            Log.d("LABA3", "File Is NOT Existing");
        }
        return retVal;
    }
}
