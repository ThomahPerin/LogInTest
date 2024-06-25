package com.zybooks.highapp;

public class ServerResponse {
    private String status;
    private String message;

    // Constructor, getters, and setters

    public String getMessage(){
        return "ServerRespone for getMessage";
    }

    public Object getStatus() {
        return "success";
    }
}