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

    public String getName() {
        return name;
    }
}
