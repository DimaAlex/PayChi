package com.paychi.dima.paychi.responses;

import com.paychi.dima.paychi.models.TaskItem;

public class CreateTaskItemResponse {
    String result;
    private TaskItem data;
    private int error_code; // 0 - success, another error message
    private String error_message;

    public TaskItem getData() {
        return data;
    }

    public int getErrorCode() {
        return error_code;
    }

    public String getErrorMessage() {
        return error_message;
    }
}
