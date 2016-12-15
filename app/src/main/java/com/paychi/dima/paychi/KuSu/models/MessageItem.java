package com.paychi.dima.paychi.KuSu.models;

import com.paychi.dima.paychi.models.User;

import java.io.Serializable;

/**
 * Created by KuSu on 15.12.2016.
 */

public class MessageItem implements Serializable {
    Message message;
    User user;
    boolean isNew;

    public User getUser() {
        return user;
    }

    public Message getMessage() {
        return message;
    }
}
