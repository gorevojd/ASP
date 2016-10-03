package com.example.gorevojd.laba4smelov;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private File mFile;
    final static String fileName = "zametki.txt";

    private Button mAddButton;
    private Button mChangeButton;
    private Button mDeleteButton;
    private Button mDeleteFileButton;

    private EditText mEditText;

    private CalendarView mCalendar;
    private DatePicker mDatePicker;

    private Zametki zametki;

    private long currentDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mAddButton = (Button)findViewById(R.id.addButton);
        this.mChangeButton = (Button)findViewById(R.id.changeButton);
        this.mDeleteButton = (Button)findViewById(R.id.deleteButton);
        this.mDeleteFileButton = (Button)findViewById(R.id.deleteFileButton);

        this.mEditText = (EditText)findViewById(R.id.editText);
        this.mCalendar = (CalendarView)findViewById(R.id.calendarView3);
//        this.mDatePicker = (DatePicker)findViewById(R.id.calendarView3);

        currentDate = mCalendar.getDate();

        zametki = new Zametki(new ArrayList<Para>(10));

        mFile = new File(getFilesDir(), fileName);
        DeserializeFromJson(mFile);

        mCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth){
                Date tempDate = new Date(year, month, dayOfMonth);
                currentDate = tempDate.getTime();
                long tempTime = tempDate.getTime();
                int tYear = tempDate.getYear();
                int tMonth = tempDate.getMonth();
                if(zametki.FindZametkaByData(tempTime) >= 0){
                    mAddButton.setVisibility(View.INVISIBLE);
                    mDeleteButton.setVisibility(View.VISIBLE);
                    mChangeButton.setVisibility(View.VISIBLE);
                }
                else{
                    mAddButton.setVisibility(View.VISIBLE);
                    mDeleteButton.setVisibility(View.INVISIBLE);
                    mChangeButton.setVisibility(View.INVISIBLE);
                }
            }
        });

        this.mAddButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                mFile = new File(getFilesDir(), fileName);
                ProvideTheFile();
                /*
                int tYear = mDatePicker.getYear();
                int tMonth = mDatePicker.getMonth();
                int tDay = mDatePicker.getDayOfMonth();
                Date tDate = new Date(tYear, tMonth, tDay);

                DatePicker dp = new DatePicker(getApplicationContext());
                */
                Para para = new Para(currentDate, mEditText.getText().toString());
                boolean addZametkaRes = zametki.AddZametka(para);
                Log.d("Hello", "Hello");

                mAddButton.setVisibility(View.INVISIBLE);
                mDeleteButton.setVisibility(View.VISIBLE);
                mChangeButton.setVisibility(View.VISIBLE);

                SerializeToJson(mFile, zametki);
            }
        });

        this.mChangeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                ProvideTheFile();

                long tempDate = currentDate;
                Para newPara = new Para(tempDate, mEditText.getText().toString());
                zametki.ChangeZametka(zametki.FindZametkaByData(currentDate), newPara);

                SerializeToJson(mFile, zametki);
            }
        });

        this.mDeleteButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                ProvideTheFile();

                zametki.DeleteZametka(zametki.FindZametkaByData(currentDate));

                mAddButton.setVisibility(View.VISIBLE);
                mDeleteButton.setVisibility(View.INVISIBLE);
                mChangeButton.setVisibility(View.INVISIBLE);

                SerializeToJson(mFile, zametki);
            }
        });

        this.mDeleteFileButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if(CheckIfFileIsExist(mFile)){
                    mFile.delete();

                }
                else{

                }
            }
        });

    }

    boolean DeserializeFromJson(File file){
        if(CheckIfFileIsExist(file)){
            try{

                Context cont = getApplicationContext();
                FileInputStream fis = cont.openFileInput(fileName);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);

                StringBuilder sb = new StringBuilder();
                String s=  br.readLine();
                while(s != null){
                    sb.append(s).append("\n");
                    s = br.readLine();
                }

                String serializedStr = sb.toString();
                Gson gs = new Gson();

                ArrayList<Para> serializedPares = gs.fromJson(serializedStr, new TypeToken<ArrayList<Para>>(){}.getType());
                zametki = new Zametki(serializedPares);
                //zametki = gs.fromJson(serializedStr, new Zametki().getClass());

                br.close();

                return true;
            }
            catch(IOException e){
                return true;
            }
        }
        else{
            return true;
        }
    }

    boolean SerializeToJson(File file, Zametki notes){
        if(CheckIfFileIsExist(file) == true){
            file.delete();
            try{
                file.createNewFile();

                FileWriter fw = new FileWriter(mFile, true);
                BufferedWriter bw = new BufferedWriter(fw);

                Gson gs = new Gson();
                String serializedStr = gs.toJson(notes.getPares(), new ArrayList<Para>().getClass());
                //String serializedStr = gs.toJson(notes, notes.getClass());
                bw.write(serializedStr);

                bw.close();
                fw.close();
                return true;
            }
            catch(IOException e){
                return false;
            }
        }
        else{
            return false;
        }
    }

    void ProvideTheFile(){
        if(CheckIfFileIsExist(mFile) == false){
            AlertDialogForFileCreating();
            try{
                mFile.createNewFile();

            }
            catch(IOException e){

            }
        }
    }

    void AlertDialogForFileCreating(){
        AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
        b.setTitle("File is not exist.\n Press OK to create...").setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){

            }
        });
        AlertDialog ad = b.create();
        ad.show();
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
