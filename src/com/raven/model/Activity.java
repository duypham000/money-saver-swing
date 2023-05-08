package com.raven.model;

public class Activity {

    public double price;
    public String desc;
    public String dateTime;
    public String type;

    public Activity(double price, String desc, String dateTime, String type) {
        this.price = price;
        this.desc = desc;
        this.dateTime = dateTime;
        this.type = type;
    }

    @Override
    public String toString() {
        return "price: " + this.price + ", desc: " + this.desc + ", dateTime: " + this.dateTime + ", type: " + this.type;
    }

}
