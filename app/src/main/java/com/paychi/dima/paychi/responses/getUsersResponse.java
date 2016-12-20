package com.paychi.dima.paychi.responses;

import com.paychi.dima.paychi.models.User;

import java.util.ArrayList;

public class getUsersResponse {
    String result;
    ArrayList<User> data;
    int error_code; // 0 - success, another error message
    String error_message;

    public ArrayList<User> getData() {
        return data;
    }
}
