package com.paychi.dima.paychi.responses;

import com.paychi.dima.paychi.models.TaskItemWrapper;
import com.paychi.dima.paychi.models.User;

import java.util.ArrayList;

public class GetTaskItemsResponse {
    String result;
    ArrayList<TaskItemWrapper> data;
    int error_code; // 0 - success, another error message
    String error_message;

    public ArrayList<TaskItemWrapper> getData() {
        return data;
    }
}
