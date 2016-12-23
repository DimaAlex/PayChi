package com.paychi.dima.paychi.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable{
    private long userId;
    private String token;

    @SerializedName("photo")
    private String photo;

    @SerializedName("chatName")
    private String chatName;

    @SerializedName("type")
    private long type;

    @SerializedName("gId")
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
        String token = "2580123";
        long userId = 6;
        long type = 1;
//        String token = "2636633";
//        long userId = 7;
//        long type = 2;

        if (instance == null) {
            instance = new User(userId, token, type);
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

    public long getType() {
        return type;
    }
}
