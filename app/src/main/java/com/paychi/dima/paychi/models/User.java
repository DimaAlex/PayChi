package com.paychi.dima.paychi.models;

import java.io.Serializable;

public class User implements Serializable{
    private long userId;
    private String token;
    private String photo;
    private String chatName;
    private long type;
    private long gId;

    public String getToken() {
        return token;
    }

    public long getUserId() {
        return userId;
    }

    public long getGId() {
        return gId;
    }

    public String getName() {
        return chatName;
    }
}
