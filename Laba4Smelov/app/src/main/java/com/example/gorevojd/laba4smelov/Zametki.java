package com.example.gorevojd.laba4smelov;

import java.util.ArrayList;

/**
 * Created by GOREVOJD on 30.09.2016.
 */

public class Zametki{
    public ArrayList<Para> pares;

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
        int findFileRes = FindZametkaByData(para.getmValue());
        if(findFileRes < 0){
            if(pares.size() >= 10){
                return false;
            }
            else{
                pares.add(para);
                return true;
            }
        }
        else{
            return false;
        }
    }

    public int FindZametkaByData(long data){
        int res = -1;
        for(int i = 0; i < pares.size(); i++){
            Para tmpPare = pares.get(i);
            long tempDate = tmpPare.getmValue();
            boolean eq = new Long(tempDate).equals(new Long(data));
            if(eq){
                res = i;
                break;
            }
        }
        return res;
    }


}
