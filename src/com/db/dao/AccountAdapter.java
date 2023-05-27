/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.db.dao;

import com.db.models.Account;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author khanh
 */
public class AccountAdapter {

    public AccountAdapter() {
    }

    public static Account getById(int id) {
        try {
            ConnectDB conn = new ConnectDB();
            ResultSet e = conn.query("SELECT * FROM `accounts` WHERE id = " + id);
            while (e.next()) {
                return new Account(e.getInt(1), e.getString(2), e.getString(3));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static List<Account> getAll() {
        List<Account> res = new ArrayList<Account>();
        try {
            ConnectDB conn = new ConnectDB();
            ResultSet e = conn.query("SELECT * FROM `accounts`");
            while (e.next()) {
                res.add(new Account(e.getInt(1), e.getString(2), e.getString(3)));
            }
        } catch (Exception e) {
        }
        return res;
    }

    public static Account getByUsernameAndPass(String usr, String pass) {
        try {
            ConnectDB conn = new ConnectDB();
            ResultSet e = conn.query("SELECT * FROM `accounts` WHERE username = '" + usr + "' AND password = '" + pass + "'");
            while (e.next()) {
                return new Account(e.getInt(1), e.getString(2), e.getString(3));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static void update(int id, String usr, String pass) {
        try {
            ConnectDB conn = new ConnectDB();
            conn.query("UPDATE `accounts` SET username = '" + usr + "', password = '" + pass + "' WHERE id = " + id);
        } catch (Exception e) {
        }
    }

    public static void delete(int id) {
        try {
            ConnectDB conn = new ConnectDB();
            conn.query("DELETE `accounts` WHERE id = " + id);
        } catch (Exception e) {
        }
    }

}
