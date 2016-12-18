package com.paychi.dima.paychi.responses;

import com.paychi.dima.paychi.models.TaskList;

import java.util.ArrayList;

public class TasksListItemResponse {
    String result;
    ArrayList<TaskList> data;
    int error_code; // 0 - success, another error message
    String error_message;
}