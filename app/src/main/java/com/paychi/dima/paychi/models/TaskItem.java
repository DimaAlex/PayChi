package com.paychi.dima.paychi.models;

public class TaskItem {
    private long itemId;
    private String title;
    private String description;
    private String cost;
    private long listId;
    private int state;
    private int target;

    public TaskItem(String title, String desc, String cost, long listId, int state, int target){
        this.title = title;
        this.description = desc;
        this.cost = cost;
        this.listId = listId;
        this.state = state;
        this.target = target;
    }

    public String getTitle() {
        return title;
    }

    public long getItemId() {
        return itemId;
    }
}

