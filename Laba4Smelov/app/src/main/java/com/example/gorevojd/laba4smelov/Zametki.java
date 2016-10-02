package com.example.gorevojd.laba4smelov;

import java.util.ArrayList;

/**
 * Created by GOREVOJD on 30.09.2016.
 */

public class Zametki{
    static final int capacity = 10;
    private ArrayList<Para> pares;

    public Zametki(){
        this.pares = new ArrayList<Para>(capacity);
    }

    public Zametki(ArrayList<Para> pares) {
        this.pares = pares;
    }

    public ArrayList<Para> getPares() {
        return pares;
    }

    public void setPares(ArrayList<Para> pares) {
        this.pares = pares;
    }

    public void ChangeZametka(int i, Para para){
        this.pares.set(i, para);
    }

    public void DeleteZametka(int i){
        this.pares.remove(i);
    }

    public boolean AddZametka(Para para){
        if(pares.size() >= Zametki.capacity){
            return false;
        }
        else{
            pares.add(para);
            return true;
        }
    }

    public int FindZametkaByData(long data){
        int res = -1;
        for(int i = 0; i < pares.size(); i++){
            if(pares.get(i).getmValue() == data){
                return i;
            }
        }
        return res;
    }


}
