package com.paychi.dima.paychi.models;

/**
 * Created by user on 13.12.2016.
 */

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
