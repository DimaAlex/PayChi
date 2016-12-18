package com.paychi.dima.paychi.models;

import com.google.gson.annotations.SerializedName;

public class TaskList {
    @SerializedName("listId")
    private long listId;

    @SerializedName("userId")
    private long userId;

    @SerializedName("visibility")
    private long visibility;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    public TaskList(String name, long userId, long visibility, String  desc) {
        this.name = name;
        this.userId = userId;
        this.visibility = visibility;
        this.description = desc;
    }

    public String getName() {
        return name;
    }

    public Long getListId() {
        return listId;
    }
}
