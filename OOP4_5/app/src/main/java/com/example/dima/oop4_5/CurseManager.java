package com.example.dima.oop4_5;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * Created by GOREVOJD on 22.09.2016.
 */

public class CurseManager {

    public static void PrintCurse(Curse curse){
        Log.d("LAB4_5", curse.toString());
    }

    public static String SerializeCurse(Curse curse){
        Gson gs = new Gson();
        return gs.toJson(curse.getCursePupils(), curse.getCursePupils().getClass());
    }

    public static Curse DeserializeCurse(String s){
        Gson gs = new Gson();
        ArrayList<Pupil> temparray = gs.fromJson(s, new TypeToken<ArrayList<Pupil>>(){}.getType());
        Curse Result = new Curse(temparray);
        return Result;
    }

    public static int countStudentsOnCurse(Curse cur){
        int res = 0;
        for(int i = 0; i < cur.getCursePupils().size(); i++){
            res = cur.getCursePupils().get(i).getClass() == Student.class ? res + 1 : res;
        }
        return res;
    }

    public static int countListenersOnCourse(Curse cur){
        int res = 0;
        for(int i = 0; i < cur.getCursePupils().size(); i++){
            res = cur.getCursePupils().get(i).getClass() == Listener.class ? res + 1 : res;
        }
        return res;
    }

    public static int getPayFeeOfListeners(Curse cur){
        int res = 0;
        for(int i = 0; i < cur.getCursePupils().size(); i++){
            if(cur.getCursePupils().get(i).getClass() == Listener.class){
                res += ((Listener)cur.getCursePupils().get(i)).getPayFee();
            }
        }
        return res;
    }

    public static ArrayList<Student> getThreeOfBestStudents(Curse cur){
        ArrayList<Student> res = new ArrayList<Student>();
        for(int i = 0; i < cur.getCursePupils().size(); i++){
            if(cur.getCursePupils().get(i).getClass() == Student.class){
                res.add((Student)cur.getCursePupils().get(i));
            }
        }
        Collections.sort(res, Student.StudentMarkComparator);
        ArrayList<Student> res2 = new ArrayList<Student>();
        int temp = 0;
        for(int i = 0; i < res.size(); i++){
            res2.add(res.get(i));
            if(temp == 3){
                break;
            }
            else{
                temp++;
            }
        }
        return res;
    }

    public static ArrayList<Student> SortByYear(Curse cur){
        ArrayList<Student> res = new ArrayList<Student>();
        for(int i = 0; i < cur.getCursePupils().size(); i++){
            if(cur.getCursePupils().get(i).getClass() == Student.class){
                res.add((Student)cur.getCursePupils().get(i));
            }
        }
        Collections.sort(res, Student.StudentYearComparator);
        return res;
    }

    public static void PrintStudents(ArrayList<Student> sts){
        for(int i = 0; i < sts.size(); i++){
            Log.d("LAB", sts.get(i).toString() + "\n");
        }
    }

    /*
    public static Curse createCurse(){
        //Context cont = MainActivity.getApplicationContext();
        FileInputStream fis = cont.openFileInput("Curse.txt");
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
            //mNameTV.setText(sb.toString());
            br.close();
        }
        catch(IOException e){
            Log.d("Log_02", "Can not read line while reading the file.");
        }
    } */

    public static Curse generateCurse(){
        Curse ret = new Curse();
        ret.generateGroup(10, 15);
        return ret;
    }
}
