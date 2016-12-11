package com.paychi.dima.paychi;

import com.paychi.dima.paychi.models.User;

public class UserResponse {
    String result;
    User data;
    int error_code; // 0 - success, another error message
    String error_message;
}
