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

    public WishItem(String title, String comment, String link, long cost, long wantRate, long visibility, long listId) {
        this.title = title;
        this.comment = comment;
        this.link = link;
        this.cost = cost;
        this.wantRate = wantRate;
        this.visibility = visibility;
        this.listId = listId;
    }

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
