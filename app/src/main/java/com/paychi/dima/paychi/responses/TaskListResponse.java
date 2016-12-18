package com.paychi.dima.paychi.responses;

import com.paychi.dima.paychi.models.TaskListWrapper;

import java.util.ArrayList;

public class TaskListResponse {
    String result;
    ArrayList<TaskListWrapper> data;
    int error_code; // 0 - success, another error message
    String error_message;

    public ArrayList<TaskListWrapper> getData() {
        return data;
    }
}
