package com.example.dima.oop3;

/**
 * Created by dima on 18.09.16.
 */
public class WrapperString {
    private String mString;

    public WrapperString(String str) {
        this.mString = str;
    }

    public String getmString() {
        return mString;
    }

    public void setmString(String mString) {
        this.mString = mString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WrapperString that = (WrapperString) o;

        return mString != null ? mString.equals(that.mString) : that.mString == null;

    }

    @Override
    public int hashCode() {
        return mString != null ? mString.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "WrapperString{" +
                "mString='" + mString + '\'' +
                '}';
    }

    public void replace(char oldchar, char newchar){
        mString = mString.replace(oldchar, newchar);
    }
}
