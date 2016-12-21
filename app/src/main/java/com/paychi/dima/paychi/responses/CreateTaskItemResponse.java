package com.paychi.dima.paychi.responses;

import com.paychi.dima.paychi.models.TaskItem;

public class CreateTaskItemResponse {
    String result;
    TaskItem data;
    int error_code; // 0 - success, another error message
    String error_message;
}
