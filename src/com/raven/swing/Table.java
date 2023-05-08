package com.raven.swing;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Table extends JTable {

    public Table() {
        setShowHorizontalLines(true);
        setGridColor(new Color(230, 230, 230));
        setRowHeight(40);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                TableHeader header = new TableHeader(o + "");
                jtable.setShowGrid(false);
                if (i1 == 3) {
                    header.setHorizontalAlignment(JLabel.CENTER);
                }
                return header;
            }
        });
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean selected, boolean bln1, int row, int col) {
                Color bg;
                if (selected) {
                    bg = (new Color(52, 152, 219));
                } else {
                    if (row % 2 == 0) {
                        bg = (new Color(255, 255, 255));
                    } else {
                        bg = (new Color(223, 230, 233));
                    }
                }
                if (col == 3) {
                    String type = (String) o;
                    CellStatus cell = new CellStatus(type, bg);
                    return cell;
                } else {
                    Component com = super.getTableCellRendererComponent(jtable, o, selected, bln1, row, col);
                    com.setBackground(Color.WHITE);
                    setBorder(noFocusBorder);
                    if (selected) {
                        com.setForeground(new Color(255, 255, 255));
                        com.setBackground(new Color(52, 152, 219));
                    } else {
                        if (row % 2 == 0) {
                            com.setBackground(new Color(255, 255, 255));
                        } else {
                            com.setBackground(new Color(223, 230, 233));
                        }
                        com.setForeground(new Color(102, 102, 102));
                    }
                    return com;
                }
            }
        });
    }

    public void addRow(Object[] row) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(row);
    }

    public String getData(int row, int col) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        return model.getValueAt(row, col).toString();
    }
}
