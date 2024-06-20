package com.zybooks.highapp;

import com.google.gson.annotations.SerializedName;

// GlobalQuote.java
public class GlobalQuote {
    @SerializedName("01. symbol")
    private String symbol;

    @SerializedName("05. price")
    private String price;

    // Getters and setters
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}