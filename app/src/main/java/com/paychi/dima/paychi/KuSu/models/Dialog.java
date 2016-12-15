package com.paychi.dima.paychi.KuSu.models;

import java.io.Serializable;

/**
 * Created by KuSu on 15.12.2016.
 */

public class Dialog implements Serializable {
    private long dialogId;
    private String name;

    private int not_readed;
    private int count;
    private boolean is_private;
    private String text;

    public long getDialogId() {
        return dialogId;
    }

    public String getName() {
        return name;
    }

    public int getNot_readed() {
        return not_readed;
    }

    public int getCount() {
        return count;
    }

    public boolean is_private() {
        return is_private;
    }

    public String getText() {
        return text;
    }
}
