package com.paychi.dima.paychi.responses;

import com.paychi.dima.paychi.models.WishListWrapper;

import java.util.ArrayList;

public class WishListResponse {
    String result;
    ArrayList<WishListWrapper> data;
    int error_code; // 0 - success, another error message
    String error_message;

    public ArrayList<WishListWrapper> getData() {
        return data;
    }
}
