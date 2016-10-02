package com.example.gorevojd.laba3smelov_2;

import android.content.Context;
import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.support.v4.util.Pair;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    private Button mWriteButton;
    private Button mGetButton;
    private Button mDeleteButton;
    private Button mReadButton;

    private EditText mWriteKey;
    private EditText mWriteValue;
    private EditText mGetKey;
    private EditText mGetValue;

    private TextView mReadTV;

    final static String fileName = "Lab3.txt";
    private File mFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mWriteButton = (Button)findViewById(R.id.writeButton);
        mGetButton = (Button)findViewById(R.id.getButton);
        mDeleteButton = (Button)findViewById(R.id.deleteButton);
        mReadButton = (Button)findViewById(R.id.readButton);

        mReadTV = (TextView)findViewById(R.id.readTextView);

        mWriteKey = (EditText)findViewById(R.id.editKey1);
        mWriteValue = (EditText)findViewById(R.id.editValue1);
        mGetKey = (EditText)findViewById(R.id.editKey2);
        mGetValue = (EditText)findViewById(R.id.editValue2);

        mWriteButton.setOnClickListener(new View.OnClickListener(){
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
                        HashTable.MarkFile(mFile);
                    }
                    catch(IOException e){

                    }
                }

                try{
                    String keyStr = mWriteKey.getText().toString();
                    String valStr = mWriteValue.getText().toString();

                    int ksbSize = 5 - keyStr.length();
                    StringBuffer ksb = new StringBuffer(ksbSize);
                    for(int i = 0; i < ksbSize; i++){
                        ksb.append(" ");
                    }

                    int vsbSize = 10 - valStr.length();
                    StringBuffer vsb = new StringBuffer(vsbSize);
                    for(int i = 0; i < vsbSize; i++){
                        vsb.append(" ");
                    }

                    String utf16Key = new String(keyStr.getBytes("UTF-16"), "UTF-16").concat(ksb.toString());
                    String utf16Val = new String(valStr.getBytes("UTF-16"), "UTF-16").concat(vsb.toString());

                    //byte[] keyVal = utf16Key.getBytes();
                    //byte[] valVal = utf16Val.getBytes();

                    PairKeyValue pair = new PairKeyValue(utf16Key, utf16Val);
                    HashTable.WritePair(pair, mFile);
                }
                catch(UnsupportedEncodingException e){

                }

            }
        });

        mGetButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                try{
                    mFile = new File(getFilesDir(), fileName);

                    String keyStr = mGetKey.getText().toString();

                    int ksbSize = 5 - keyStr.length();
                    StringBuffer ksb = new StringBuffer(ksbSize);
                    for(int i = 0; i < ksbSize; i++){
                        ksb.append(" ");
                    }

                    String utf16Key = new String(keyStr.getBytes("UTF-16"), "UTF-16").concat(ksb.toString());

                    String value = HashTable.RestorePair(mFile, utf16Key);
                    String newValue = new String(value);
                    mGetValue.setText(newValue);
                }
                catch(UnsupportedEncodingException e){

                }
            }
        });

        mDeleteButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                mFile = new File(getFilesDir(), fileName);
                if(CheckIfFileIsExist(mFile) == true){
                    mFile.delete();
                }
                else{

                }
            }
        });

        mReadButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                mFile = new File(getFilesDir(), fileName);

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
                        FileInputStream fis = cont.openFileInput(fileName);
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
                            mReadTV.setText(sb.toString());
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
            Log.d("LABA3", "File Is Existing");
        }
        else{
            retVal = false;
            Log.d("LABA3", "File Is NOT Existing");
        }
        return retVal;
    }
}
