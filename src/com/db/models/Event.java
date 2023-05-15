/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.db.models;

/**
 *
 * @author khanh
 */
public class Event {

    public Event(int id, double price, String desc, String time, String type, int userId) {
        this.id = id;
        this.price = price;
        this.desc = desc;
        this.time = time;
        this.type = type;
        this.userId = userId;
    }

    public int id;
    public double price;
    public String desc;
    public String time;
    public String type;
    public int userId;
}
