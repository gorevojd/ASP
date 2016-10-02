package com.example.dima.oop4_5;

import java.util.ArrayList;


public class Curse implements ITest {
    private ArrayList<Pupil> cursePupils;

    public Curse(){
        this.cursePupils = new ArrayList<Pupil>();
    }

    public Curse(ArrayList<Pupil> cursePupils) {
        this.cursePupils = cursePupils;
    }

    public ArrayList<Pupil> getCursePupils() {
        return cursePupils;
    }

    public void setCursePupils(ArrayList<Pupil> cursePupils) {
        this.cursePupils = cursePupils;
    }

    public boolean add(Pupil p) throws EduException{
        if(cursePupils.add(p) == true){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean remove(Pupil p){
        //if(cursePupils.remove(cursePupils.indexOf(p)))
        if(cursePupils.remove(cursePupils.toArray()[cursePupils.indexOf(p)]) == true){
            return true;
        }
        else{
            return false;
        }
    }

    public void generateGroup(int studNum, int listNum){
        for(int i = 0; i < studNum; i++){
            this.cursePupils.add(new Student());
        }
        for(int i = 0; i< listNum; i++){
            this.cursePupils.add(new Listener());
        }
    }

    @Override
    public String toString() {
        return "Curse{" +
                "cursePupils=" + cursePupils +
                '}';
    }

}
