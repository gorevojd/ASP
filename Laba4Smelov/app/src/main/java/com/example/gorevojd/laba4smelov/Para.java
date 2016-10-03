package com.example.gorevojd.laba4smelov;

/**
 * Created by GOREVOJD on 30.09.2016.
 */

public class Para {
    private Long mValue;
    private String mZametka;

    public Para(){

    }

    public Para(long mValue, String mZametka) {
        this.mValue = mValue;
        this.mZametka = mZametka;
    }

    public String getmZametka() {
        return mZametka;
    }

    public void setmZametka(String mZametka) {
        this.mZametka = mZametka;
    }

    public long getmValue() {
        return mValue;
    }

    @Override
    public String toString() {
        return "Para{" +
                "mValue=" + mValue +
                ", mZametka='" + mZametka + '\'' +
                '}';
    }

    public void setmValue(long mValue) {
        this.mValue = mValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Para para = (Para) o;

        if (mValue != para.mValue) return false;
        return mZametka != null ? mZametka.equals(para.mZametka) : para.mZametka == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (mValue ^ (mValue >>> 32));
        result = 31 * result + (mZametka != null ? mZametka.hashCode() : 0);
        return result;
    }
}
