package com.example.dima.oop4_5;

import java.util.Comparator;

/**
 * Created by dima on 19.09.16.
 */
public class Student extends Pupil implements Comparable<Student>{

    private int yearBirth;
    private int mark;


    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
    @Override
    public String toString() {
        return "Student{}";
    }

    public int compareTo(Student st){
        return  this.mark > st.mark ? 1 : (this.mark == st.mark ? 0 : -1);
    }

    public int getYearBirth() {
        return yearBirth;
    }

    public void setYearBirth(int yearBirth) {
        this.yearBirth = yearBirth;
    }

    public Student(String mName, String mFamilyName) {
        super(mName, mFamilyName);
    }

    public Student() {
        GenerateRandomName();
    }

    public static int CompareByYear(Student st1, Student st2){
        return st1.yearBirth > st2.yearBirth ? 1 : (st1.yearBirth == st2.yearBirth ? 0 : -1);
    }

    public static int CompareByMark(Student st1, Student st2){
        return st1.mark > st2.mark ? 1 :(st1.mark == st2.mark ? 0 : -1);
    }

    public static Comparator<Student> StudentMarkComparator = new Comparator<Student>(){
        public int compare(Student st1, Student st2){
            return CompareByMark(st1, st2);
        }
    };

    public static Comparator<Student> StudentYearComparator = new Comparator<Student>(){
        public int compare(Student st1, Student st2){
            return CompareByYear(st1, st2);
        }
    };
}
