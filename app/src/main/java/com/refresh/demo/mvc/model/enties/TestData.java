package com.refresh.demo.mvc.model.enties;

import java.io.Serializable;


public class TestData implements Serializable {
    private int userid;
    private int sbid;
    private String jlsj;
    private String lr;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getSbid() {
        return sbid;
    }

    public void setSbid(int sbid) {
        this.sbid = sbid;
    }

    public String getJlsj() {
        return jlsj;
    }

    public void setJlsj(String jlsj) {
        this.jlsj = jlsj;
    }

    public String getLr() {
        return lr;
    }

    public void setLr(String lr) {
        this.lr = lr;
    }
}
