/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ms.forms;

import com.db.dao.EventAdapter;
import com.db.models.Event;
import com.ms.chart.ModelChart;
import com.ms.services.Converter;
import com.raven.datechooser.EventDateChooser;
import com.raven.datechooser.SelectedAction;
import com.raven.datechooser.SelectedDate;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RAVEN
 */
public class Form_Total extends javax.swing.JPanel {

    /**
     * Creates new form Form_1
     */
    private int userId = -1;
    private List<Event> rawList;

    public Form_Total(int id) {
        initComponents();
        this.userId = id;

        total_chart.addLegend("Chi định kỳ", new Color(186, 123, 247), new Color(186, 123, 247));
        total_chart.addLegend("Thu", new Color(46, 204, 113), new Color(95, 209, 69));
        total_chart.addLegend("Chi linh tinh", new Color(231, 76, 60), new Color(231, 76, 60));

        rawList = EventAdapter.getAllById(this.userId);
        inpt_type.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (inpt_type.getSelectedIndex()) {
                    case 0:
                        rawList = EventAdapter.getAllById(id);
                        break;
                    case 1:
                        rawList = EventAdapter.getAllDayById(id);
                        break;
                    case 2:
                        rawList = EventAdapter.getAllMonthById(id);
                        break;
                    default:
                        rawList = EventAdapter.getAllYearById(id);
                        break;
                }
                setup();
            }
        });

        dateStart.addEventDateChooser(new EventDateChooser() {
            @Override
            public void dateSelected(SelectedAction action, SelectedDate d) {
                if (cbx_start.getState()) {
                    inpt_dateStart.setText(d.toString());
                    setup();
                }
            }
        });
        dateEnd.addEventDateChooser(new EventDateChooser() {
            @Override
            public void dateSelected(SelectedAction action, SelectedDate d) {
                if (cbx_dateEnd.getState()) {
                    inpt_dateEnd.setText(d.toString());
                    setup();
                }
            }
        });

        setup();
    }

    /*
    0: tất cả
    1: hôm nay
    2: tháng này
    3: năm nay
     */
    void setup() {
        total_chart.clear();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Event> eList = new ArrayList<Event>();
        for (int i = 0; i < rawList.size(); i++) {
            Event get = rawList.get(i);
            boolean checkStartDate = false;
            boolean checkEndDate = false;
            boolean checkType = false;

            try {
                checkStartDate = inpt_dateStart.getText().equals(Converter.pickerToTime(get.time).split(" ")[0]) || sdf.parse(inpt_dateStart.getText()).before(sdf.parse(Converter.pickerToTime(get.time).split(" ")[0]));
            } catch (ParseException ex) {
                checkStartDate = true;
            }
            try {
                checkEndDate = inpt_dateEnd.getText().equals(Converter.pickerToTime(get.time).split(" ")[0]) || sdf.parse(Converter.pickerToTime(get.time).split(" ")[0]).before(sdf.parse(inpt_dateEnd.getText()));
            } catch (ParseException ex) {
                checkEndDate = true;
            }

            if (inpt_datatype.getSelectedIndex() == 0 || inpt_datatype.getSelectedItem().equals(get.type)) {
                checkType = true;
            }

            if (checkStartDate && checkEndDate && checkType) {
                eList.add(get);
            }
        }
        double t = 0, lt = 0, dk = 0, total = 0;
        for (int i = 0; i < eList.size(); i++) {
            Event e = eList.get(i);
            total += e.price;
            if (e.type.equals("Thu")) {
                t += e.price;
            } else if (e.type.equals("Ngẫu nhiên")) {
                lt += e.price;
            } else {
                dk += e.price;
            }
            table.addRow(new Object[]{Converter.formatPrice(e.price), e.desc, e.time, e.type});
        }

        txt_thu.setText("Thu: " + Converter.formatPrice(t));
        txt_dinhky.setText("Định kỳ: " + Converter.formatPrice(dk));
        txt_linhtinh.setText("Linh tinh: " + Converter.formatPrice(lt));
        txt_tong.setText("Tổng: " + Converter.formatPrice(total));

        List<double[]> log = new ArrayList<double[]>();

        double[] currentPrice = new double[]{0, 0, 0};

        if (eList.size() == 0) {
            return;
        }
        String curTime = eList.get(0).time.split(" ")[0];
        for (int i = 0; i < eList.size(); i++) {
            Event e = eList.get(i);
            if ((curTime.equals(e.time.split(" ")[0]))) {
                int index = 0;
                if (e.type.equals("Thu")) {
                    index = 1;
                } else if (e.type.equals("Ngẫu nhiên")) {
                    index = 2;
                }
                currentPrice[index] += e.price;
            } else {
                total_chart.addData(new ModelChart(curTime, currentPrice));
                curTime = e.time.split(" ")[0];
                log.add(currentPrice);
                int index = 0;
                if (e.type.equals("Thu")) {
                    index = 1;
                } else if (e.type.equals("Ngẫu nhiên")) {
                    index = 2;
                }
                currentPrice = new double[]{0, 0, 0};
                currentPrice[index] += e.price;
            }
            if (i == eList.size() - 1) {
                total_chart.addData(new ModelChart(curTime, currentPrice));
                log.add(currentPrice);
            }
        }
        total_chart.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateStart = new com.raven.datechooser.DateChooser();
        dateEnd = new com.raven.datechooser.DateChooser();
        total_chart = new com.ms.chart.CurveChart();
        jPanel1 = new javax.swing.JPanel();
        inpt_ctn = new javax.swing.JPanel();
        inpt_type = new javax.swing.JComboBox<>();
        inpt_dateStart = new javax.swing.JTextField();
        btn_start = new javax.swing.JButton();
        inpt_dateEnd = new javax.swing.JTextField();
        btn_end = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbx_start = new java.awt.Checkbox();
        cbx_dateEnd = new java.awt.Checkbox();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txt_dinhky = new javax.swing.JLabel();
        txt_thu = new javax.swing.JLabel();
        txt_linhtinh = new javax.swing.JLabel();
        txt_tong = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        inpt_datatype = new javax.swing.JComboBox<>();
        spTable = new javax.swing.JScrollPane();
        table = new com.raven.swing.Table();

        dateStart.setForeground(new java.awt.Color(52, 152, 219));

        setBackground(new java.awt.Color(242, 242, 242));

        inpt_type.setBackground(new java.awt.Color(255, 255, 255));
        inpt_type.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        inpt_type.setForeground(new java.awt.Color(127, 140, 141));
        inpt_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Hôm nay", "Tháng này", "Năm nay" }));

        inpt_dateStart.setEditable(false);
        inpt_dateStart.setBackground(new java.awt.Color(255, 255, 255));
        inpt_dateStart.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        inpt_dateStart.setForeground(new java.awt.Color(0, 0, 0));
        inpt_dateStart.setCaretColor(new java.awt.Color(127, 140, 141));
        inpt_dateStart.setFocusable(false);
        inpt_dateStart.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inpt_dateStartKeyPressed(evt);
            }
        });

        btn_start.setText("***");
        btn_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_startActionPerformed(evt);
            }
        });

        inpt_dateEnd.setEditable(false);
        inpt_dateEnd.setBackground(new java.awt.Color(255, 255, 255));
        inpt_dateEnd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        inpt_dateEnd.setForeground(new java.awt.Color(0, 0, 0));
        inpt_dateEnd.setCaretColor(new java.awt.Color(127, 140, 141));
        inpt_dateEnd.setFocusable(false);
        inpt_dateEnd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inpt_dateEndKeyPressed(evt);
            }
        });

        btn_end.setText("***");
        btn_end.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_endActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(127, 140, 141));
        jLabel6.setText("Ngày kết thúc");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(127, 140, 141));
        jLabel7.setText("Ngày bắt đầu");

        cbx_start.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbx_startItemStateChanged(evt);
            }
        });

        cbx_dateEnd.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbx_dateEndItemStateChanged(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(127, 140, 141));
        jLabel8.setText("Xem theo");

        txt_dinhky.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_dinhky.setText("Định kỳ: 13000000");

        txt_thu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_thu.setText("Thu: 7000000");

        txt_linhtinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_linhtinh.setText("Linh tinh: 1000000");

        txt_tong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_tong.setText("Tổng: 15000000");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(txt_thu, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dinhky, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txt_linhtinh, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_tong, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_thu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_dinhky, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_linhtinh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(127, 140, 141));
        jLabel9.setText("Dạng");

        inpt_datatype.setBackground(new java.awt.Color(255, 255, 255));
        inpt_datatype.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        inpt_datatype.setForeground(new java.awt.Color(127, 140, 141));
        inpt_datatype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Hàng ngày", "Hàng tháng", "Ngẫu nhiên", "Thu" }));
        inpt_datatype.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                inpt_datatypeItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout inpt_ctnLayout = new javax.swing.GroupLayout(inpt_ctn);
        inpt_ctn.setLayout(inpt_ctnLayout);
        inpt_ctnLayout.setHorizontalGroup(
            inpt_ctnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inpt_ctnLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inpt_ctnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(inpt_type, 0, 136, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(inpt_ctnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(inpt_dateStart)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(inpt_ctnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_start, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_start, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(inpt_ctnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(inpt_dateEnd)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(inpt_ctnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_end, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_dateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(inpt_ctnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inpt_datatype, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(inpt_ctnLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        inpt_ctnLayout.setVerticalGroup(
            inpt_ctnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inpt_ctnLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(inpt_ctnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inpt_ctnLayout.createSequentialGroup()
                        .addGroup(inpt_ctnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(inpt_ctnLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel8))
                            .addGroup(inpt_ctnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(inpt_ctnLayout.createSequentialGroup()
                                    .addGroup(inpt_ctnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(inpt_ctnLayout.createSequentialGroup()
                                            .addComponent(jLabel7)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                        .addGroup(inpt_ctnLayout.createSequentialGroup()
                                            .addGroup(inpt_ctnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cbx_start, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cbx_dateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(4, 4, 4)))
                                    .addGroup(inpt_ctnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(inpt_dateStart, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btn_start, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(inpt_ctnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(inpt_dateEnd, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_end, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(inpt_type, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(inpt_ctnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inpt_ctnLayout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(32, 32, 32))
                                    .addGroup(inpt_ctnLayout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(inpt_datatype, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(21, 21, 21))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        spTable.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tổng tiền", "Chi tiết", "Thời gian", "Dạng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setShowGrid(false);
        table.getTableHeader().setReorderingAllowed(false);
        spTable.setViewportView(table);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(inpt_ctn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(spTable)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(inpt_ctn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(total_chart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(total_chart, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_endActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_endActionPerformed
        // TODO add your handling code here:
        dateEnd.showPopup(inpt_ctn, btn_end.getX() + btn_end.getWidth(), btn_end.getY() + btn_end.getHeight());
    }//GEN-LAST:event_btn_endActionPerformed

    private void inpt_dateEndKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inpt_dateEndKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_inpt_dateEndKeyPressed

    private void btn_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_startActionPerformed
        // TODO add your handling code here:
        dateStart.showPopup(inpt_ctn, btn_start.getX() + btn_start.getWidth(), btn_start.getY() + btn_start.getHeight());
    }//GEN-LAST:event_btn_startActionPerformed

    private void inpt_dateStartKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inpt_dateStartKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_inpt_dateStartKeyPressed

    private void cbx_startItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbx_startItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
            //do something...
            inpt_dateStart.setText(dateStart.getSelectedDate().toString());
        } else {//checkbox has been deselected
            //do something...
            inpt_dateStart.setText("");
        };
        setup();
    }//GEN-LAST:event_cbx_startItemStateChanged

    private void cbx_dateEndItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbx_dateEndItemStateChanged
        // TODO add your handling code here:

        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
            //do something...
            inpt_dateEnd.setText(dateEnd.getSelectedDate().toString());
        } else {//checkbox has been deselected
            //do something...
            inpt_dateEnd.setText("");
        };
        setup();
    }//GEN-LAST:event_cbx_dateEndItemStateChanged

    private void inpt_datatypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_inpt_datatypeItemStateChanged
        // TODO add your handling code here:
        setup();
    }//GEN-LAST:event_inpt_datatypeItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_end;
    private javax.swing.JButton btn_start;
    private java.awt.Checkbox cbx_dateEnd;
    private java.awt.Checkbox cbx_start;
    private com.raven.datechooser.DateChooser dateEnd;
    private com.raven.datechooser.DateChooser dateStart;
    private javax.swing.JPanel inpt_ctn;
    private javax.swing.JComboBox<String> inpt_datatype;
    private javax.swing.JTextField inpt_dateEnd;
    private javax.swing.JTextField inpt_dateStart;
    private javax.swing.JComboBox<String> inpt_type;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane spTable;
    private com.raven.swing.Table table;
    private com.ms.chart.CurveChart total_chart;
    private javax.swing.JLabel txt_dinhky;
    private javax.swing.JLabel txt_linhtinh;
    private javax.swing.JLabel txt_thu;
    private javax.swing.JLabel txt_tong;
    // End of variables declaration//GEN-END:variables
}
