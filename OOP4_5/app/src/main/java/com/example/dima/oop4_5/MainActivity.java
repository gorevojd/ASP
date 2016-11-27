package com.example.dima.oop4_5;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    public Button mLoginButton;
    public Button mWriteButton;
    public Button mReadButton;
    public Button mGenerateButton;
    public Button mDeleteButton;
    public Button mAddNewStudentButton;
    public Button mAdminButton;
    public Button mSortButton;

    private CurseManager curseManager;
    private Curse curse;

    public static final String mFileName = "names.txt";
    public static File mFile;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mFile = new File(getFilesDir(), mFileName);

        try{
            mFile.delete();
            mFile.createNewFile();

            if(CheckIfFileIsExist(mFile) == false) {
                mFile.createNewFile();
            }
            else{
                Log.d("Log_02", "File has been already created");
            }            FileWriter fw = new FileWriter(mFile, true);
            BufferedWriter bw = new BufferedWriter(fw);

            String s = CurseManager.SerializeCurse(curse);
            try{
                bw.write(s);
                Log.d("LAB4_5", s);
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

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mFile = new File(getFilesDir(), mFileName);

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
                    //sb.append("\n");
                }

                Log.d("Laba: ", sb.toString());
                curse = CurseManager.DeserializeCurse(sb.toString());

                Log.d("Log_02", "Hello + " + sb.toString());
                //mNameTV.setText(sb.toString());
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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWriteButton = (Button)findViewById(R.id.writeButton);
        mReadButton = (Button)findViewById(R.id.readButton);
        mGenerateButton = (Button)findViewById(R.id.generateButton);
        mDeleteButton = (Button)findViewById(R.id.deleteButton);
        mAddNewStudentButton = (Button)findViewById(R.id.AddNewStudent);
        mAdminButton = (Button)findViewById(R.id.adminButton);
        mLoginButton = (Button)findViewById(R.id.loginButton);
        mSortButton = (Button)findViewById(R.id.SortButton);

        curseManager = new CurseManager();
        curse = new Curse();

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String StName = extras.getString("StudentName");
            Student st = new Student();
            st.setmName(extras.getString("StudentName"));
            st.setmFamilyName(extras.getString("StudentFamilyName"));
            st.setMark(extras.getInt("StudentMark"));
            st.setYearBirth(extras.getInt("StudentYear"));
            try {
                curse.add(st);
            } catch (EduException e) {
                e.printStackTrace();
            }
        }


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
                    mFile.delete();
                    mFile.createNewFile();
                    FileWriter fw = new FileWriter(mFile, true);
                    BufferedWriter bw = new BufferedWriter(fw);

                    String s = CurseManager.SerializeCurse(curse);
                    try{
                        bw.write(s);
                        Log.d("LAB4_5", s);
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


        mAddNewStudentButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent AddNewStudentIntent = new Intent(MainActivity.this, EnterNameActivity.class);
                startActivity(AddNewStudentIntent);
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
                            //sb.append("\n");
                        }

                        curse = CurseManager.DeserializeCurse(sb.toString());

                        Log.d("Log_02", "Hello + " + sb.toString());
                        //mNameTV.setText(sb.toString());
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

        mGenerateButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                curse = CurseManager.generateCurse();
                CurseManager.PrintCurse(curse);
            }
        });

        mDeleteButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                mFile = new File(getFilesDir(), mFileName);
            if(CheckIfFileIsExist(mFile)){
                mFile.delete();
            }
            else{

            }
            }
        });

        mAdminButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(MainActivity.this, AdminActivity.class);
                String CurseString = CurseManager.SerializeCurse(curse);
                newIntent.putExtra("CurseString", CurseString);
                startActivity(newIntent);
            }
        });

        mLoginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(MainActivity.this, LoginActivity.class);
                String CurseString = CurseManager.SerializeCurse(curse);
                newIntent.putExtra("CurseString", CurseString);
                startActivity(newIntent);
            }
        });

        mSortButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
             //   curse.getCursePupils() = CurseManager.SortByYear(curse);
            }
        });

    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }
*/
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
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
