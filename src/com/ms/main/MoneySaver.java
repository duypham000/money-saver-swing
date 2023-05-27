/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.ms.main;

import com.db.dao.AccountAdapter;
import com.db.dao.ConnectDB;
import com.db.models.Account;
import com.ms.dialogs.Login_Form;
import com.ms.layout.HomeLayout;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.JFrame;

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
                Login_Form login = new Login_Form();
                login.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        int id = login.getId();
                        System.out.println(id);
                        if (id != -1) {
                            var homeFrm = new HomeLayout(id);
                            homeFrm.setExtendedState(JFrame.MAXIMIZED_BOTH);
                            homeFrm.setVisible(true);
                            login.dispose();
                        } else {
                            login.dispose();
                            return;
                        }
                    }
                });
                login.setVisible(true);
            }
        });
    }

}
