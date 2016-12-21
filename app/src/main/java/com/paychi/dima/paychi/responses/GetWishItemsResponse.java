package com.paychi.dima.paychi.responses;

import com.paychi.dima.paychi.models.WishItem;

import java.util.ArrayList;

public class GetWishItemsResponse {
    String result;
    ArrayList<WishItem> data;
    int error_code; // 0 - success, another error message
    String error_message;

    public ArrayList<WishItem> getData() {
        return data;
    }
}
