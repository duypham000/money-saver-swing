/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.db.models;

/**
 *
 * @author khanh
 */
public class Detail {

    public Detail(int id, int idUser, double moneyLeft, double totalMoney, double maxPriceMonth, double maxPriceWeek, double maxPriceDay) {
        this.id = id;
        this.idUser = idUser;
        this.moneyLeft = moneyLeft;
        this.totalMoney = totalMoney;
        this.maxPriceMonth = maxPriceMonth;
        this.maxPriceWeek = maxPriceWeek;
        this.maxPriceDay = maxPriceDay;
    }

    public int id;
    public int idUser;
    public double moneyLeft;
    public double totalMoney;
    public double maxPriceMonth;
    public double maxPriceWeek;
    public double maxPriceDay;
}
