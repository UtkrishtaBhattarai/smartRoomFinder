package com.example.smartroomfinder.serverresponse;

public class OrderResponse {

    private String status;

    public OrderResponse(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
