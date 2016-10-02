package com.example.dima.oop3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static java.lang.Math.*;


public class MainActivity extends AppCompatActivity {

    private char chVar = 'C';
    private int intVar = 123;
    private short shVar = 1234;
    private byte byVar = 12;
    private long lVar = 12345;
    private boolean bVar = true;
    private String sVar = "hello";
    static int sint;

    private TextView mWtfTV;
    private Button mWtfButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWtfButton = (Button)findViewById(R.id.wtfButton);
        mWtfTV = (TextView)findViewById(R.id.wtfTV);

        String str1 = sVar + intVar;
        String str2 = sVar + chVar;
        String str3 = sVar + 12.0;
        //byVar = byVar + 123;
        //int temp1 =  1234.0 + lVar;
        lVar = 2137482647 + 20;
        bVar = bVar && false;
        bVar = bVar ^ false;
        //bVar = bVar + bVar;

        Log.d("OOP3", str2);
        Log.d("OOP3", str1);
        Log.d("OOP3", str3);
        Log.d("OOP3", String.valueOf(lVar));
        Log.d("OOP3", String.valueOf(bVar));

        char ch = 'a' + '\u0061' + 97;
        Log.d("OOP3", String.valueOf(ch));
        float tempf = 3.45f % 2.4f;
        Log.d("OOP3", String.valueOf(tempf));
        double d1 = 1.0 / 0.0;
        Log.d("OOP3", String.valueOf(d1));
        double d2 = 0.0/0.0;
        Log.d("OOP3", String.valueOf(d2));
        double d3 = log(-345);
        Log.d("OOP3", String.valueOf(d3));
        float f1 = Float.intBitsToFloat(0x7f800000);
        Log.d("OOP3", String.valueOf(f1));
        float f2 = Float.intBitsToFloat(0xff800000);
        Log.d("OOP3", String.valueOf(f2));

        float mPi = Math.round(PI);
        Log.d("OOP3", String.valueOf(mPi));
        float mE = Math.round(Math.E);
        Log.d("OOP3", String.valueOf(mE));
        float minf = Math.min(mPi, mE);
        Log.d("OOP3", String.valueOf(minf));
        double rand = Math.random();
        Log.d("OOP3", String.valueOf(rand));

        Integer tInt1 = new Integer(10);
        Log.d("OOP3", String.valueOf(tInt1));
        Double tDouble1 = new Double(20.0);
        Log.d("OOP3", String.valueOf(tDouble1));
        Float tFloat1 = new Float(30.0f);
        Log.d("OOP3", String.valueOf(tFloat1));

        tInt1 = tInt1 >> 1;
        Log.d("OOP3", String.valueOf(tInt1));
        tFloat1 = tFloat1 + 10.0f;
        Log.d("OOP3", String.valueOf(tFloat1));
        tDouble1 = tDouble1 - 1.0;
        Log.d("OOP3", String.valueOf(tDouble1));
        tDouble1 = tDouble1 * tDouble1;
        Log.d("OOP3", String.valueOf(tDouble1));

        tFloat1 = Float.MIN_VALUE;
        Log.d("OOP3", String.valueOf(tFloat1));
        tDouble1 = Double.MAX_VALUE;
        Log.d("OOP3", String.valueOf(tDouble1));
        Integer int2 = Integer.parseInt("123");
        Log.d("OOP3", String.valueOf(int2));
        String intStr1 = Integer.toHexString(int2);
        Log.d("OOP3", intStr1);
        int int3 = Integer.bitCount(int2);
        Log.d("OOP3", String.valueOf(int3));
        String intStr2 = Integer.toString(Integer.MIN_VALUE);
        Log.d("OOP3", intStr2);

        int sInt1 = Integer.parseInt("2345");
        Log.d("OOP3", String.valueOf(sInt1));
        int sInt2 = Integer.valueOf("2345");
        Log.d("OOP3", String.valueOf(sInt2));

        byte[] bMass = "adsf".getBytes();
        Log.d("OOP3", String.valueOf(bMass));
        String strMass = bMass.toString();
        Log.d("OOP3", strMass);

        Boolean tempBool1 = Boolean.valueOf("true");
        Log.d("OOP3", String.valueOf(tempBool1));
        Boolean tempBool2 = Boolean.parseBoolean("false");
        Log.d("OOP3", String.valueOf(tempBool2));

        String newStr1 = "true";
        String newStr2 = "true";
        boolean newBool = (newStr1 == newStr2);
        int newBool1 = newStr1.compareTo(newStr2);
        newStr2 = null;
        boolean newBool2 = (newStr1 == newStr2);
       // int newBool3 = newStr1.compareTo(newStr2);

        String watafakStr = "hello world";
        watafakStr.split("");
        boolean haveHell = watafakStr.contains("hell");
        int len = watafakStr.length();

        char[][] c1 = new char[3][];
        char[] c2[] = new char[1][2];
        char c3[][] = new char[4][4];

        boolean comRes = c2 == c3;
        c2 = c3;


        WrapperString ws = new WrapperString("asdfa");
        ws.replace('a', 's');
        Log.d("OOP3", ws.getmString());

        mWtfButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                mWtfTV.setText("WTF");
            }
        });

    }
}
