package com.example.gorevojd.laba5smelov;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public Button InsertButton;
    public Button SelectButton;
    public Button SelectRawButton;
    public Button UpdateButton;
    public Button DeleteButton;
    public Button DeleteAllButton;

    public EditText IdEditText;
    public EditText FloatEditText;
    public EditText TextEditText;

    MyDBHelper dbhelper = new MyDBHelper(this);
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InsertButton = (Button)findViewById(R.id.InsertButton);
        SelectButton = (Button)findViewById(R.id.SelectButton);
        SelectRawButton = (Button)findViewById(R.id.SelectRawButton);
        UpdateButton = (Button)findViewById(R.id.UpdateButton);
        DeleteButton = (Button)findViewById(R.id.DeleteButton);
        DeleteAllButton = (Button)findViewById(R.id.DeleteAllButton);

        IdEditText = (EditText)findViewById(R.id.id_EditText);
        FloatEditText = (EditText)findViewById(R.id.f_EditText);
        TextEditText = (EditText)findViewById(R.id.t_EditText);

        db = dbhelper.getWritableDatabase();

        InsertButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                float Fl = GetFloat();
                String Texs = GetString();
                ContentValues values = new ContentValues();
                int IdTextLen = IdEditText.getText().toString().length();
                if(IdTextLen != 0){
                    int Id = GetID();
                    values.put("ID", Id);
                }
                values.put("F", Fl);
                values.put("T", Texs);
                long RowId = db.insert("MainTable", null, values);
                Log.d("Laba5 insert: ", String.format("rowid - %d", RowId));
            }
        });

        SelectButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int IdValue = GetID();
                String IdString = String.format("%d", IdValue);
                Cursor c = db.query("MainTable",
                        new String[]{"ID", "F", "T"},
                        "ID=?", new String[]{IdString}, null, null, null);
                float FloatValue = 0.0f;
                String TextValue = new String();
                if(c.moveToFirst()){
                    FloatValue = c.getFloat(1);
                    TextValue = c.getString(2);
                }
                SetValues(IdValue, FloatValue, TextValue);
            }
        });

        SelectRawButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int IdValue = GetID();
                Cursor c = db.rawQuery("SELECT ID, F, T FROM MainTable WHERE ID=?", new String[]{String.format("%d", IdValue)});
                float FloatValue = 0.0f;
                String TextValue = new String();
                if(c.moveToFirst()){
                    FloatValue = c.getFloat(1);
                    TextValue = c.getString(2);
                }
                SetValues(IdValue, FloatValue, TextValue);
            }
        });

        UpdateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int IdValue = GetID();
                float FloatValue = GetFloat();
                String TextValue = GetString();
                ContentValues values = new ContentValues();
                values.put("ID", IdValue);
                values.put("F", FloatValue);
                values.put("T", TextValue);
                int c = db.update("MainTable", values, "ID=?", new String[]{String.format("%d", IdValue)});
                Log.d("Laba5 update where: ", "Number of changed rows - " + String.format("%d", c));
                SetValues(IdValue, FloatValue, TextValue);
            }
        });

        DeleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int IdValue = GetID();
                int NumberDeleted = db.delete("MainTable", "ID=?", new String[]{String.format("%d",IdValue)});
                Log.d("Laba5 delete where: ", String.format("deleted %d rows", NumberDeleted));
            }
        });

        DeleteAllButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int NumberDeleted = db.delete("MainTable", null, null);
                Log.d("Laba5 delete all: ", String.format("deleted %d rows", NumberDeleted));
            }
        });
    }

    public int GetID(){
        int Result;
        Result = Integer.valueOf(IdEditText.getText().toString());
        return(Result);
    }

    public float GetFloat(){
        float Result;
        Result = Float.valueOf(FloatEditText.getText().toString());
        return(Result);
    }

    public String GetString(){
        String Result;
        Result = TextEditText.getText().toString();
        return(Result);
    }

    public void SetValues(int IdValue, float FloatValue, String TextValue){
        IdEditText.setText(String.valueOf(IdValue));
        FloatEditText.setText(String.valueOf(FloatValue));
        TextEditText.setText(TextValue);
    }
}
