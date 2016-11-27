package com.example.dima.oop4_5;

import android.util.Log;

import java.io.File;
import java.lang.Math.*;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by dima on 19.09.16.
 */
public class Pupil {
    protected String mName;
    protected String mFamilyName;

    public String getmName() {
        return mName;
    }

    @Override
    public String toString() {
        return "Pupil{" +
                "mName='" + mName + '\'' +
                ", mFamilyName='" + mFamilyName + '\'' +
                '}';
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmFamilyName() {
        return mFamilyName;
    }

    public void setmFamilyName(String mFamilyName) {
        this.mFamilyName = mFamilyName;
    }

    public Pupil(){

    }

    public Pupil(String mName, String mFamilyName) {
        this.mName = mName;
        this.mFamilyName = mFamilyName;
    }

    public static String[] names = {"Dima", "Ivan", "Vova", "Borya", "Nikita", "Kolya", "Artur", "Zhenya"};
    public static String[] familyNames = {"Ivanov", "Petrov", "Vasechking", "Soikin", "Vorobiev", "Andreev", "Drozdov", "Golubev"};

    public void GenerateRandomName(){
        Random rand = new Random();
        int temp1 = rand.nextInt(names.length);
        int temp2 = rand.nextInt(familyNames.length);

        mName = names[temp1];
        mFamilyName = familyNames[temp2];
    }

    public static Comparator<Pupil> PupilNameComparator = new Comparator<Pupil>(){
        public int compare(Pupil st1, Pupil st2){
            return st1.getmName().compareTo(st2.getmName());
        }
    };
}
