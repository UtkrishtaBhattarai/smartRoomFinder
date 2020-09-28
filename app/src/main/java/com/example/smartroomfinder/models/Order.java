package com.example.smartroomfinder.models;

public class Order {

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    private String _id;
    private String userid;
    private String orderprice;
    private String ordernumber;
    private int trackingordernumber ;
    private String posteduser ;
    private int numberofpeople;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(String orderprice) {
        this.orderprice = orderprice;
    }

    public String getOrderlocation() {
        return orderlocation;
    }

    public void setOrderlocation(String orderlocation) {
        this.orderlocation = orderlocation;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    public int getTrackingordernumber() {
        return trackingordernumber;
    }

    public void setTrackingordernumber(int trackingordernumber) {
        this.trackingordernumber = trackingordernumber;
    }

    public String getPosteduser() {
        return posteduser;
    }

    public void setPosteduser(String posteduser) {
        this.posteduser = posteduser;
    }

    public int getNumberofpeople() {
        return numberofpeople;
    }

    public void setNumberofpeople(int numberofpeople) {
        this.numberofpeople = numberofpeople;
    }


    private String orderlocation;

    public Order(String userid,String orderlocation, String ordernumber, int trackingordernumber, String posteduser, int numberofpeople) {
        this.userid = userid;
        this.orderlocation = orderlocation;
        this.ordernumber = ordernumber;
        this.trackingordernumber = trackingordernumber;
        this.posteduser = posteduser;
        this.numberofpeople = numberofpeople;
    }




}
