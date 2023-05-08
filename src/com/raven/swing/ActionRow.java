package com.raven.swing;

import java.awt.Color;
import java.awt.Cursor;

public class ActionRow extends javax.swing.JPanel {

    private int id;

    public ActionRow(int id, Color bg) {
        initComponents();
        setBackground(bg);
        this.id = id;
//        btn_edit.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        btn_delete.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_delete = new com.raven.swing.ActionButton();
        btn_edit = new com.raven.swing.ActionButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.ms.assets/delete.png"))); // NOI18N
        btn_delete.setBorderPainted(false);
        btn_delete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                delete(evt);
            }
        });
        add(btn_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 30, 40));

        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.ms.assets/edit.png"))); // NOI18N
        btn_edit.setBorderPainted(false);
        btn_edit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                edit(evt);
            }
        });
        add(btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 30, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void edit(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit
        // TODO add your handling code here:
        System.err.println("id = " + this.id);
    }//GEN-LAST:event_edit

    private void delete(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delete
        // TODO add your handling code here:
        System.err.println("delete = " + this.id);
    }//GEN-LAST:event_delete


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.ActionButton btn_delete;
    private com.raven.swing.ActionButton btn_edit;
    // End of variables declaration//GEN-END:variables
}
