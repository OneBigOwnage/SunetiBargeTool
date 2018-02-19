/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import App.Controller;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import sunetibargetool.SunetiBargeTool;

/**
 *
 * @author niekv
 */
public class SideBar extends javax.swing.JPanel {

    protected Controller controller;

    /**
     * Creates new form sideBar
     *
     * @param c The Controller
     */
    public SideBar(Controller c) {
        initComponents();
        this.controller = c;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btn_log = new javax.swing.JLabel();

        setBackground(new java.awt.Color(15, 124, 160));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_icon_ship.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bargeInfo(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_icon_config.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                config(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_icon_sql.png"))); // NOI18N
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SQL(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_icon_logout.png"))); // NOI18N
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel4MousePressed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_icon_backup.png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                onMousePressedBackup(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_icon_procedure.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                standardProcedures(evt);
            }
        });

        btn_log.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_icon_log.png"))); // NOI18N
        btn_log.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_log.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                log(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel4)
                    .addComponent(btn_log))
                .addGap(0, 3, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addComponent(jLabel2)
                .addGap(0, 0, 0)
                .addComponent(jLabel3)
                .addGap(0, 0, 0)
                .addComponent(jLabel5)
                .addGap(0, 0, 0)
                .addComponent(jLabel7)
                .addGap(0, 0, 0)
                .addComponent(btn_log)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(jLabel4))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Method to draw a line next to the menu icons.
     *
     * @param graphics
     */
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        // Draw a line.
        Graphics2D graphicsNew = (Graphics2D) graphics;
        Line2D line = new Line2D.Float(62, 0, 62, getHeight());
        graphicsNew.draw(line);
    }


    private void bargeInfo(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bargeInfo
        this.controller.showBargeInfoView();
    }//GEN-LAST:event_bargeInfo

    private void config(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_config
        controller.showConfigView();
    }//GEN-LAST:event_config

    private void SQL(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SQL
        controller.showSQLView();
    }//GEN-LAST:event_SQL

    private void log(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_log
        controller.showLogView();
    }//GEN-LAST:event_log

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed
        SunetiBargeTool.exitApp();
    }//GEN-LAST:event_jLabel4MousePressed

    private void standardProcedures(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_standardProcedures
        controller.showStandardProcedureView();
    }//GEN-LAST:event_standardProcedures

    private void onMousePressedBackup(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onMousePressedBackup
        controller.showBackupView();
    }//GEN-LAST:event_onMousePressedBackup

    public void setBgColor(Color color) {
        setBackground(color);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btn_log;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
