package com.example.smartroomfinder.models;

public class UsersCUD {
    private String fname;
    private String lname;
    private String email;
    private String password;
    private String address;
    private String number;


    //for updating adding
    public UsersCUD(String fname, String lname, String email, String password, String address, String number) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.number = number;
    }
}
