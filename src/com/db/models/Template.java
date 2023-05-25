/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.db.models;

/**
 *
 * @author khanh
 */
public class Template {

    public Template(int id, double price, String desc, String name, String type, int userId) {
        this.id = id;
        this.price = price;
        this.desc = desc;
        this.name = name;
        this.type = type;
        this.userId = userId;
    }

    public int id;
    public double price;
    public String desc;
    public String name;
    public String type;
    public int userId;
}
