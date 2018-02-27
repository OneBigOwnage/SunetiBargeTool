/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import App.Controller;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextArea;
import App.Config;

/**
 *
 * @author niekv
 */
public class LogView extends javax.swing.JPanel {

    protected Controller controller;
    private final JTextArea logField;
    private final DateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
    private boolean logToConsole;

    /**
     * Default constructor for this class.
     *
     * @param controller The controller that is used to initialize this class.
     */
    public LogView(Controller controller) {
        initComponents();
        this.logField = logTextArea;
        this.controller = controller;
        this.logToConsole = Config.getBoolean("log_to_console");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_empty_log = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        logTextArea = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(15, 124, 160));

        btn_empty_log.setText("Empty Log");
        btn_empty_log.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_empty_logMousePressed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(255, 51, 51));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 60, 60)));
        jScrollPane1.setOpaque(false);

        logTextArea.setEditable(false);
        logTextArea.setBackground(new java.awt.Color(15, 124, 160));
        logTextArea.setColumns(20);
        logTextArea.setRows(5);
        logTextArea.setBorder(null);
        jScrollPane1.setViewportView(logTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_empty_log, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_empty_log)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_empty_logMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_empty_logMousePressed
        logField.setText("");
    }//GEN-LAST:event_btn_empty_logMousePressed

    public void appendToLog(String text) {
        String currentTime = this.TIME_FORMAT.format(new Date());
        String newLine = "\r\n";
        if (text.endsWith("\n") || text.endsWith("\r\n")) {
            newLine = "";
        }

        String logLine = String.format("[ %s ] - %s%S", currentTime, text, newLine);

        logField.append(logLine);
        // Also System.out.print if flag logToConsole is true.
        if (this.logToConsole) {
            System.out.print(logLine);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_empty_log;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea logTextArea;
    // End of variables declaration//GEN-END:variables
}
