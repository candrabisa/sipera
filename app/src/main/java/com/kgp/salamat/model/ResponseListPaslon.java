package com.kgp.salamat.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseListPaslon {

    @SerializedName("paslon")
    private List<PaslonItem> paslon;
    @SerializedName("error")
    private boolean error;
    @SerializedName("status")
    private int status;

    public List<PaslonItem> getPaslon() {
        return paslon;
    }

    public boolean isError() {
        return error;
    }

    public int getStatus() {
        return status;
    }
}
