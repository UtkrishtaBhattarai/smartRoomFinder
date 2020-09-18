package com.example.smartroomfinder.models;

public class Cart {

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    private String userid;
    private String productid;
    private String name;
    private String price;
    private String description;
    private String location;
    private String number;

    public Cart(String userid, String productid, String name, String price, String description, String location, String number) {
        this.userid = userid;
        this.productid = productid;
        this.name = name;
        this.price = price;
        this.description = description;
        this.location = location;
        this.number=number;
    }


}