package com.paychi.dima.paychi.KuSu.reaponse;

import com.paychi.dima.paychi.KuSu.models.Dialog;
import com.paychi.dima.paychi.models.TaskListWrapper;

import java.util.ArrayList;

public class DialogsResponse {
    ArrayList<Dialog> data;
    int error_code; // 0 - success, another error message
    String error_message;

    public String getErrorText() {
        return error_message;
    }

    public boolean isSuccess() {
        return error_code == 0;
    }

    public ArrayList<Dialog> getData() {
        return data;
    }
}
