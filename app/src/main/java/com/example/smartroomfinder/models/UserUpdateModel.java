package com.example.smartroomfinder.models;

public class UserUpdateModel {

    private String _id;
    private String fname;
    private String lname;
    private String email;
    private String address;
    private String number;

    public UserUpdateModel(String _id, String fname, String lname, String email, String address, String number) {
        this._id = _id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.address = address;
        this.number = number;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getNumber() {
        return number;
    }
}
