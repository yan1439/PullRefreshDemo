package com.refresh.demo.mvc.adapter;

import com.refresh.demo.mvc.model.enties.TestData;

import java.io.Serializable;
import java.util.List;

public class TestResultData implements Serializable {

    private int myhs;
    private int zhs;
    private List<TestData> data;

    public int getMyhs() {
        return myhs;
    }

    public void setMyhs(int myhs) {
        this.myhs = myhs;
    }

    public int getZhs() {
        return zhs;
    }

    public void setZhs(int zhs) {
        this.zhs = zhs;
    }

    public List<TestData> getData() {
        return data;
    }

    public void setData(List<TestData> data) {
        this.data = data;
    }
}