package com.paychi.dima.paychi.models;

public class WishItem {
    private long itemId;
    private String title;
    private String comment;
    private String link;
    private String photo;
    private long cost;
    private long wantRate;
    private long visibility;
    private long listId;

    public Long getItemId() {
        return itemId;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getPhoto() {
        return photo;
    }
}
