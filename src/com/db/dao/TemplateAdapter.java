/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.db.dao;

import com.db.models.Template;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author khanh
 */
public class TemplateAdapter {

    public TemplateAdapter() {
    }

    public static List<Template> getAllById(int userId) {
        List<Template> res = new ArrayList<Template>();
        try {
            ConnectDB conn = new ConnectDB();
            ResultSet e = conn.query("SELECT * FROM `templates` WHERE userId = " + userId + " ORDER BY time");
            while (e.next()) {
                res.add(new Template(e.getInt(1), e.getDouble(2), e.getNString(3), e.getNString(4), e.getNString(5), e.getInt(6)));
            }
        } catch (Exception e) {
        }
        return res;
    }


    public static Template getById(int id) {
        try {
            ConnectDB conn = new ConnectDB();
            ResultSet e = conn.query("SELECT * FROM `templates` WHERE id = " + id);
            while (e.next()) {
                return new Template(e.getInt(1), e.getDouble(2), e.getNString(3), e.getNString(4), e.getNString(5), e.getInt(6));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static void insert(Template event) {
        try {
            ConnectDB conn = new ConnectDB();
            conn.query("INSERT INTO `templates` (`id`, `price`, `description`, `time`, `type`, `userId`) VALUES (NULL, '" + event.price + "', '" + event.desc + "', '" + event.name + "', '" + event.type + "', '" + event.userId + "');");
        } catch (Exception e) {
        }
    }

    public static void update(Template event) {
        try {
            ConnectDB conn = new ConnectDB();
            conn.query("UPDATE `templates` SET `price` = '" + event.price + "', `description` = N'" + event.desc + "', `type` = N'" + event.type + "', `time` = '" + event.name + "', `userId` = '" + event.userId + "' WHERE `events`.`id` = " + event.id + ";");
        } catch (Exception e) {
        }
    }

    public static void delete(int id) {
        try {
            ConnectDB conn = new ConnectDB();
            conn.query("DELETE FROM `templates` WHERE id = " + id);
        } catch (Exception e) {
        }
    }

}
