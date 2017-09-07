package com.example.user.bukbol.API;

import com.google.gson.annotations.SerializedName;

/**
 * Created by denail on 17/09/06.
 */

public class ServerResponse {
    // variable name should be same as in the json response from php
    @SerializedName("success")
    boolean success;
    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }

    public boolean getSuccess() {
        return success;
    }
}
