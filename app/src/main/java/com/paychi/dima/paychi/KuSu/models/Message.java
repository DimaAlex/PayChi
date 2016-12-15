package com.paychi.dima.paychi.KuSu.models;
import com.paychi.dima.paychi.KuSu.Constants;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;

/**
 * Created by KuSu on 15.12.2016.
 */

public class Message implements Serializable {
    private long messageId;
    private long date;
    private String text;
    private long type;
    private String link;
    private long dialogId;

    public long getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return calendar.getTime().toString();
    }

    public boolean isSystem() {
        return type == Constants.MESSAGE_TYPE_SYSTEM;
    }

    public long getId() {
        return messageId;
    }

    public void setDialogId(long dialogId) {
        this.dialogId = dialogId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setType(int type) {
        this.type = type;
    }
}
