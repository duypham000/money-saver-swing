/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.db.dao;

import com.db.models.Detail;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author khanh
 */
public class DetailAdapter {

    public DetailAdapter() {
    }

    public static Detail getByUserId(int userId) {
        try {
            ConnectDB conn = new ConnectDB();
            ResultSet e = conn.query("SELECT * FROM `detail` WHERE idUser = " + userId + "");
            while (e.next()) {
                return new Detail(e.getInt(1), e.getInt(2), e.getDouble(3), e.getDouble(4), e.getDouble(5), e.getDouble(6), e.getDouble(7));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static Detail getById(int id) {
        try {
            ConnectDB conn = new ConnectDB();
            ResultSet e = conn.query("SELECT * FROM `detail` WHERE id = " + id);
            while (e.next()) {
                return new Detail(e.getInt(1), e.getInt(2), e.getDouble(3), e.getDouble(4), e.getDouble(5), e.getDouble(6), e.getDouble(7));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static void insert(Detail d) {
        try {
            ConnectDB conn = new ConnectDB();
            conn.query("INSERT INTO `detail` (`id`, `idUser`, `moneyLeft`, `totalMoney`, `maxPriceMonth`, `maxPriceWeek`, `maxPriceDay`) VALUES (NULL, '" + d.idUser + "', '" + d.moneyLeft + "', '" + d.totalMoney + "', '" + d.maxPriceMonth + "', '" + d.maxPriceWeek + "', '" + d.maxPriceDay + "');");
        } catch (Exception e) {
        }
    }

    public static void update(Detail d) {
        try {
            ConnectDB conn = new ConnectDB();
            conn.query("UPDATE `detail` SET `idUser` = '" + d.idUser + "', `moneyLeft` = '" + d.moneyLeft + "', `totalMoney` = '" + d.totalMoney + "', `maxPriceMonth` = '" + d.maxPriceMonth + "', `maxPriceWeek` = '" + d.maxPriceWeek + "', `maxPriceDay` = '" + d.maxPriceDay + "' WHERE `detail`.`id` = " + d.id + ";");
        } catch (Exception e) {
        }
    }

    public static void delete(int id) {
        try {
            ConnectDB conn = new ConnectDB();
            conn.query("DELETE FROM `detail` WHERE id = " + id);
        } catch (Exception e) {
        }
    }

}
