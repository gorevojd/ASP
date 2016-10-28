package com.example.gorevojd.laba5smelov;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    public Button InsertButton;
    public Button SelectButton;
    public Button SelectRawButton;
    public Button PrevButton;

    public EditText IdEditText;
    public EditText TextEditText;

    MyDBHelper dbhelper = new MyDBHelper(this);
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        InsertButton = (Button)findViewById(R.id.InsertButton2);
        SelectButton = (Button)findViewById(R.id.SelectButton2);
        SelectRawButton = (Button)findViewById(R.id.SelectRawButton2);
        PrevButton = (Button)findViewById(R.id.PrevButton);

        IdEditText = (EditText)findViewById(R.id.idEditText_2);
        TextEditText = (EditText)findViewById(R.id.tEditText_2);

        db = dbhelper.getWritableDatabase();

        InsertButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int Id = GetID();
                String Txt = GetString();
                ContentValues values = new ContentValues();
                values.put("ID", Id);
                values.put("T", Txt);
                long RowId = db.insert("DependTable", null, values);
                Log.d("Laba5 insert: ", String.format("rowid - %d", RowId));
            }
        });

        SelectButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int IdValue = GetID();
                String IdString = String.format("%d", IdValue);
                Cursor c = db.query("DependTable",
                        new String[]{"ID", "T"}, "ID=?",
                        new String[]{IdString},
                        null, null, null);
                String TextValue = new String();
                if(c.moveToFirst()){
                    TextValue = c.getString(1);
                }
                SetValues(IdValue, TextValue);
            }
        });

        SelectRawButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int IdValue = GetID();
                Cursor c = db.rawQuery("SELECT ID, T FROM DependTable where ID=?", new String[]{String.format("%d", IdValue)});
                String TextValue = new String();
                if(c.moveToFirst()){
                    TextValue = c.getString(1);
                }
                SetValues(IdValue, TextValue);
            }
        });

        PrevButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(newIntent);
            }
        });
    }

    public int GetID(){
        int Result;
        Result = Integer.valueOf(IdEditText.getText().toString());
        return(Result);
    }

    public String GetString(){
        String Result;
        Result = TextEditText.getText().toString();
        return(Result);
    }

    public void SetValues(int IdValue, String TextValue){
        IdEditText.setText(String.valueOf(IdValue));
        TextEditText.setText(TextValue);
    }
}
