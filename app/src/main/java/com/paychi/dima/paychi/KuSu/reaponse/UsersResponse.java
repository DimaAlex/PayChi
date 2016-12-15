package com.paychi.dima.paychi.KuSu.reaponse;

import com.paychi.dima.paychi.KuSu.models.MessageItem;
import com.paychi.dima.paychi.models.User;

import java.util.ArrayList;

public class UsersResponse {
    ArrayList<User> data;
    int error_code; // 0 - success, another error message
    String error_message;

    public String getErrorText() {
        return error_message;
    }

    public boolean isSuccess() {
        return error_code == 0;
    }

    public ArrayList<User> getData() {
        return data;
    }
}
