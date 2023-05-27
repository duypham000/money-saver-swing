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

    public Detail(int id, int idUser, long moneyLeft, long totalMoney, long maxPriceMonth, long maxPriceWeek, long maxPriceDay) {
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
    public long moneyLeft;
    public long totalMoney;
    public long maxPriceMonth;
    public long maxPriceWeek;
    public long maxPriceDay;
}
