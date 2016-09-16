package com.example.dima.laba2;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {


    private File mFile;
    private final String mFileName = "names.txt";

    private Button mReadButton;
    private Button mWriteButton;
    private Button mDeleteButton;

    private EditText mNameET;
    private EditText mFamilyET;

    private TextView mNameTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mReadButton = (Button)findViewById(R.id.readButton);
        this.mWriteButton = (Button)findViewById(R.id.writeButton);
        this.mDeleteButton = (Button)findViewById(R.id.deleteButton);

        this.mNameET = (EditText)findViewById(R.id.nameET);
        this.mFamilyET = (EditText)findViewById(R.id.familyET);
        this.mNameTV = (TextView)findViewById(R.id.nameTV);

        mDeleteButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if(CheckIfFileIsExist(mFile) == true){
                    mFile.delete();
                    mNameTV.setText("");
                }
                else{
                    AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                    b.setTitle("File not existing.").setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });

                    AlertDialog ad = b.create();
                    ad.show();
                }

            }
        });

        mWriteButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                mFile = new File(getFilesDir(), mFileName);

                if(CheckIfFileIsExist(mFile) == false) {
                    AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                    b.setTitle("File is creating " + mFileName).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.d("Log_02", "File is creating " + mFileName);
                        }
                    });
                    AlertDialog ad = b.create();
                    ad.show();
                    try {
                        mFile.createNewFile();
                        Log.d("Log_02", "File has been created.");
                    }
                    catch (IOException e){

                    }
                }
                else{
                    Log.d("Log_02", "File has been already created");
                }

                try{
                    FileWriter fw = new FileWriter(mFile, true);
                    BufferedWriter bw = new BufferedWriter(fw);

                    String s = mNameET.getText() + ";" + mFamilyET.getText() + ";" +"\r\n";
                    try{
                        bw.write(s);
                        bw.close();
                    }
                    catch (IOException e){
                        Log.d("Log_02", e.getMessage());
                    }
                }
                catch(IOException e){
                    Log.d("Log_02", "File " + mFileName + " has not been created");
                }
            }
        });

        mReadButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                mFile = new File(getFilesDir(), mFileName);

                if(CheckIfFileIsExist(mFile) == false){
                    AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                    b.setTitle("File not existing.").setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    AlertDialog ad = b.create();
                    ad.show();
                }
                else{
                    try {

                        Context cont = getApplicationContext();
                        FileInputStream fis = cont.openFileInput(mFileName);
                        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
                        BufferedReader br = new BufferedReader(isr);

                        try{
                            StringBuilder sb = new StringBuilder();
                            String s = br.readLine();
                            while(s != null){
                                sb.append(s);
                                s = br.readLine();
                                sb.append("\n");
                            }
                            Log.d("Log_02", "Hello + " + sb.toString());
                            mNameTV.setText(sb.toString());
                            br.close();
                        }
                        catch(IOException e){
                            Log.d("Log_02", "Can not read line while reading the file.");
                        }
                    }
                    catch(IOException e){
                        Log.d("Log_02", "File not found while reading the files.");
                    }
                }
            }
        });
    }

    private boolean CheckIfFileIsExist(File file){
        boolean retVal;
        if((file.exists() == true) && (file != null)){
            retVal = true;
            Log.d("Log_02", "File is existing");
        }
        else{
            retVal = false;
            Log.d("Log_02", "File is NOT existing");
        }

        return retVal;
    }
}