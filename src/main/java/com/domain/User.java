package com.domain;

public class User {

    private Integer uid;
    private String username;
    private String password;
    private String other1;
    private String other2;

    public User(Integer uid, String username, String password, String other1, String other2) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.other1 = other1;
        this.other2 = other2;
    }

    public User() {
    }

    public String getOther1() {
        return other1;
    }

    public void setOther1(String other1) {
        this.other1 = other1;
    }

    public String getOther2() {
        return other2;
    }

    public void setOther2(String other2) {
        this.other2 = other2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
