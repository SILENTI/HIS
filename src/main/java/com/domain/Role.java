package com.domain;

public class Role {

    //用于存储角色表的信息

    private Integer rid ;
    private String rname;
    private String rdescription;
    private String yl1;
    private String yl12;

    public Role() {
    }

    public Role(Integer rid, String rname, String rdescription, String yl1, String yl12) {
        this.rid = rid;
        this.rname = rname;
        this.rdescription = rdescription;
        this.yl1 = yl1;
        this.yl12 = yl12;
    }

    public String getYl1() {
        return yl1;
    }

    public void setYl1(String yl1) {
        this.yl1 = yl1;
    }

    public String getYl12() {
        return yl12;
    }

    public void setYl12(String yl12) {
        this.yl12 = yl12;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getRdescription() {
        return rdescription;
    }

    public void setRdescription(String rdescription) {
        this.rdescription = rdescription;
    }
}
