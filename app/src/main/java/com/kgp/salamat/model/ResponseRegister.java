package com.kgp.salamat.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseRegister {
   @SerializedName("register")
   private List<RegisterItem> register;

   @SerializedName("message")
    private String message;

   @SerializedName("error")
    private boolean eror;

   @SerializedName("status")
    private int status;

    public List<RegisterItem> getRegister() {
        return register;
    }

    public boolean isEror() {
        return eror;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
