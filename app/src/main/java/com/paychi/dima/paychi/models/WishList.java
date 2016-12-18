package com.paychi.dima.paychi.models;

import com.google.gson.annotations.SerializedName;

public class WishList {
    @SerializedName("listId")
    private long listId;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("visibility")
    private long visibility;

    public Long getListId() {
        return listId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
