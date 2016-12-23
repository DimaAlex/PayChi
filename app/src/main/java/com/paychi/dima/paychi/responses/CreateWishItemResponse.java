package com.paychi.dima.paychi.responses;

import com.paychi.dima.paychi.models.WishItem;

public class CreateWishItemResponse {
    String result;
    WishItem data;
    private int error_code; // 0 - success, another error message
    private String error_message;

    public WishItem getData(){
        return data;
    }

    public int getErrorCode() {
        return error_code;
    }

    public String getErrorMessage() {
        return error_message;
    }
}
