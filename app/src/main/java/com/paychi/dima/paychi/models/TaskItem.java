package com.paychi.dima.paychi.models;

public class TaskItem {
    private long itemId;
    private String title;
    private String description;
    private String cost;
    private long listId;
    private int state;
    private int target;

    public String getTitle() {
        return title;
    }

    public long getItemId() {
        return itemId;
    }
}

