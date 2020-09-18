package com.example.smartroomfinder.models;

public class Users {

    private String _id;
    private String fname;
    private String lname;
    private String email;
    private String password;
    private String address;
    private String number;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }


    public Users(String fname, String lname, String email, String password, String address, String number, String _id) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.number = number;
        this._id = _id;
    }

    public Users() {
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}