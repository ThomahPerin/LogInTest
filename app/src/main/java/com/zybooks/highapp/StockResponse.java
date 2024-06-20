package com.zybooks.highapp;


import com.google.gson.annotations.SerializedName;

// StockResponse.java
public class StockResponse {
    @SerializedName("Global Quote")
    private GlobalQuote globalQuote;

    public GlobalQuote getGlobalQuote() {
        return globalQuote;
    }

    public void setGlobalQuote(GlobalQuote globalQuote) {
        this.globalQuote = globalQuote;
    }
}