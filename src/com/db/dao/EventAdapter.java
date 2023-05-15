/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.db.dao;

import com.db.models.Account;
import com.db.models.Event;
import com.ms.services.Converter;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author khanh
 */
public class EventAdapter {

    public EventAdapter() {
    }

    public static List<Event> getAllById(int userId) {
        List<Event> res = new ArrayList<Event>();
        try {
            ConnectDB conn = new ConnectDB();
            ResultSet e = conn.query("SELECT * FROM `events` WHERE userId = " + userId);
            while (e.next()) {
                res.add(new Event(e.getInt(1), e.getDouble(2), e.getNString(3), Converter.timeToPicker(e.getString(4)), e.getNString(5), e.getInt(6)));
            }
        } catch (Exception e) {
        }
        return res;
    }

    public static Event getById(int id) {
        try {
            ConnectDB conn = new ConnectDB();
            ResultSet e = conn.query("SELECT * FROM `events` WHERE id = " + id);
            while (e.next()) {
                return new Event(e.getInt(1), e.getDouble(2), e.getNString(3), Converter.timeToPicker(e.getString(4)), e.getNString(5), e.getInt(6));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static void insert(Event event) {
        try {
            ConnectDB conn = new ConnectDB();
            conn.query("INSERT INTO `events` (`id`, `price`, `description`, `time`, `type`, `userId`) VALUES (NULL, '" + event.price + "', '" + event.desc + "', '" + Converter.pickerToTime(event.time) + "', '" + event.type + "', '" + event.userId + "');");
        } catch (Exception e) {
        }
    }

    public static void update(Event event) {
        try {
            ConnectDB conn = new ConnectDB();
            conn.query("UPDATE `events` SET `price` = '" + event.price + "', `description` = N'" + event.desc + "', `type` = N'" + event.type + "', `time` = '" + Converter.pickerToTime(event.time) + "', `userId` = '" + event.userId + "' WHERE `events`.`id` = " + event.id + ";");
        } catch (Exception e) {
        }
    }

    public static void delete(int id) {
        try {
            ConnectDB conn = new ConnectDB();
            conn.query("DELETE FROM `events` WHERE id = " + id);
        } catch (Exception e) {
        }
    }

}
