package com.paychi.dima.paychi.responses;

import com.paychi.dima.paychi.models.User;

public class UserResponse {
    String result;
    User data;
    public int error_code; // 0 - success, another error message
    public String error_message;

    public User getData() {
        return data;
    }
}
