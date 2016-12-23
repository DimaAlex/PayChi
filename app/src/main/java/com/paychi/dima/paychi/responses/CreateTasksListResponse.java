package com.paychi.dima.paychi.responses;

import com.paychi.dima.paychi.models.TaskList;

import java.util.ArrayList;

public class CreateTasksListResponse {
    String result;
    TaskList data;
    private int error_code; // 0 - success, another error message
    private String error_message;

    public int getErrorCode() {
        return error_code;
    }

    public String getErrorMessage() {
        return error_message;
    }
}