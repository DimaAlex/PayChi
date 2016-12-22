package com.paychi.dima.paychi.models;

public class TaskListWrapper {

    private TaskList list;
    private long visible;
    private long inProgress;
    private long done;
    private long praised;
    private long total;

    public TaskList getList() {
        return list;
    }

    public long getInProgress() {
        return inProgress;
    }

    public long getDone() {
        return done;
    }

    public long getPraised() {
        return praised;
    }

    public long getTotal() {
        return total;
    }
}
