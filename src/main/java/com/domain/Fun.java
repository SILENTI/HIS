package com.domain;

/**
 * 用于临时存储，权限管理的信息
 * */

public class Fun {

    private int fid;
    //序列号
    private String fname;
    //权限管理的名称
    private String ftype;
    //权限管理的类型 ——> 1-菜单 2-按钮
    private String fhref;
    //菜单/null
    private int pid;
    //所属父级的id
    private String auth;
    //所属的权限范围
    private String yl1;
    private String yl2;
    //预留1和预留2字段


    public Fun() {
    }

    public Fun(int fid, String fname, String ftype, String fhref, int pid, String auth, String yl1, String yl2) {
        this.fid = fid;
        this.fname = fname;
        this.ftype = ftype;
        this.fhref = fhref;
        this.pid = pid;
        this.auth = auth;
        this.yl1 = yl1;
        this.yl2 = yl2;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFtype() {
        return ftype;
    }

    public void setFtype(String ftype) {
        this.ftype = ftype;
    }

    public String getFhref() {
        return fhref;
    }

    public void setFhref(String fhref) {
        this.fhref = fhref;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getYl1() {
        return yl1;
    }

    public void setYl1(String yl1) {
        this.yl1 = yl1;
    }

    public String getYl2() {
        return yl2;
    }

    public void setYl2(String yl2) {
        this.yl2 = yl2;
    }
}
