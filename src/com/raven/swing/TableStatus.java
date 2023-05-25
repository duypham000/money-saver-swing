package com.raven.swing;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;

public class TableStatus extends JLabel {

    public String getType() {
        return type;
    }

    public TableStatus() {
        setForeground(Color.WHITE);
    }

    private String type;

    public void setType(String type) {
        this.type = type;
        setText(type);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        if (type != null) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint g;
            if (type.equals("Ngẫu nhiên")) {
                g = new GradientPaint(0, 0, new Color(231, 76, 60), 0, getHeight(), new Color(231, 76, 60));
            } else if (type.equals("Thu")) {
                g = new GradientPaint(0, 0, new Color(46, 204, 113), 0, getHeight(), new Color(39, 174, 96));
            } else {
                g = new GradientPaint(0, 0, new Color(186, 123, 247), 0, getHeight(), new Color(167, 94, 236));
            }
            g2.setPaint(g);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 1, 1);
        }
        super.paintComponent(grphcs);
    }
}
