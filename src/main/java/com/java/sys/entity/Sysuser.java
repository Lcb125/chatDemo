package com.java.sys.entity;

public class Sysuser {
    private Integer uid;
    private String uname;

    @Override
    public String toString() {
        return "Sysuser{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                '}';
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
