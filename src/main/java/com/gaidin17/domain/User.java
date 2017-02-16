package com.gaidin17.domain;

/**
 * Created by Evgeny_Akulenko on 2/14/2017.
 */
public class User {
    private String userId;
    private long userCode;

    public User(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public long getUserCode() {
        return userCode;
    }

    public void setUserCode(long userCode) {
        this.userCode = userCode;
    }
}
