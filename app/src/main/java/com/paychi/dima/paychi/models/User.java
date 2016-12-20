package com.paychi.dima.paychi.models;

import java.io.Serializable;

public class User implements Serializable{
    private long userId;
    private String token;
    private String photo;
    private String chatName;
    private long type;
    private long gId;

    private static User instance;

    public User(long userId, String token, long type) {
        this.userId = userId;
        this.token = token;
        this.type = type;
    }

    public User(long userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public static User getInstance() {
        String token = "2636633";
        long userId = 7;
        long type = 1;

        if (instance == null) {
            instance = new User(userId, token);
        }

        return instance;
    }

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
