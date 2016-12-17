package com.paychi.dima.paychi.models;

public class TaskListWrapper {

    TaskList list;
    long visible;
    long inProgress;
    long done;
    long praised;
    long total;

    public TaskList getList() {
        return list;
    }
}
