package com.paychi.dima.paychi.responses;

import com.paychi.dima.paychi.models.WishItem;

public class CreateWishItemResponse {
    String result;
    WishItem data;
    int error_code; // 0 - success, another error message
    String error_message;
}
