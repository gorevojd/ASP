package com.example.dima.oop4_5;

import com.google.gson.Gson;

import java.util.Random;

/**
 * Created by dima on 19.09.16.
 */

enum organization_name{
    ORG1,
    ORG2,
    ORG3,
    ORG4,
    ORG5
};

public class Listener extends Pupil {

    private float payFee;
    private organization_name orgName;

    public float getPayFee() {
        return payFee;
    }

    public void setPayFee(float payFee) {
        this.payFee = payFee;
    }

    public Listener() {
        GenerateRandomName();
    }

    public Listener(float payFee){
        this.payFee = payFee;
        GenerateRandomName();
    }

    private void GenerateRandOrg(){
        Random rand4 = new Random();
        int rand = rand4.nextInt(5);
        this.orgName = organization_name.values()[rand];
    }

    public Listener(String mName, String mFamilyName) {
        super(mName, mFamilyName);
    }



    @Override
    public String toString() {
        return "Listener{}";
    }
}
