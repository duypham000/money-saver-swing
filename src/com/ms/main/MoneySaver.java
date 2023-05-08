/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.ms.main;

import com.db.dao.AccountAdapter;
import com.db.dao.ConnectDB;
import com.db.models.Account;
import com.ms.layout.HomeLayout;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author khanh
 */
public class MoneySaver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                ConnectDB db = new ConnectDB();
//                Account res = AccountAdapter.getByUsernameAndPass("duyn", "05082002");
                new HomeLayout().setVisible(true);
            }
        });
    }
    
}
