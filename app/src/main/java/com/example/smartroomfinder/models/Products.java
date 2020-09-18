package com.example.smartroomfinder.models;

public class Products {
    private String _id;
    private String image;
    private String name;
    private String price;
    private String description;
    private String number;
    private String location;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getlocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String specification) {
        this.number = number;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Products(String name, String price, String image,String number, String description, String location, String _id) {
        this.name = name;
        this.price = price;
        this.number=number;
        this.image = image;
        this.description = description;
        this.location = location;
        this._id = _id;
    }

    @Override
    public String toString() {
        return "tbl_product [name = " + name + ", price = " + price + "," +
                " description = " + description + ",  image = " + image + "]";
    }
}
