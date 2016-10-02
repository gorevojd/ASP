package com.example.dima.laba3smelov;

/**
 * Created by dima on 19.09.16.
 */
public class PairKeyValue {
    private String key;
    private String value;

    public PairKeyValue(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public PairKeyValue(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
