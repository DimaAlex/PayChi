package com.paychi.dima.paychi.models;

import com.google.gson.annotations.SerializedName;

public class TaskList {
    @SerializedName("listId")
    public long listId;

    @SerializedName("userId")
    public long userId;

    @SerializedName("visibility")
    public long visibility;

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

    public TaskList(String name, long userId, long visibility, String  desc) {
        this.name = name;
        this.userId = userId;
        this.visibility = visibility;
        this.description = desc;
    }

    public String getName() {
        return name;
    }
}
