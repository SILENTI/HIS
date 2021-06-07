package com.domain;

public class Token {
    /**
     * 用于存储自动登录的认证信息
     * */

    private String tokenID;//存储登录时的tokenID

    private User user; //存储登录时的信息

    private String ip; //存储登录时所获取的IP地址

    private long start ;//生效时间戳  时间毫秒表示

    private long end ; //失效时间戳   时间毫秒表示

    public Token() {
    }

    public Token(String tokenID, User user, String ip, long start, long end) {
        this.tokenID = tokenID;
        this.user = user;
        this.ip = ip;
        this.start = start;
        this.end = end;
    }

    public String getTokenID() {
        return tokenID;
    }

    public void setTokenID(String tokenID) {
        this.tokenID = tokenID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }
}
