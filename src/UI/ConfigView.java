/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import App.Controller;
import App.Commands;
import App.Database;
import App.ThreadManager;
import App.Utils;
import Daemons.DaemonManager;
import Daemons.DaemonManager.DaemonType;
import java.lang.reflect.Method;

/**
 *
 * @author niekv
 */
public class ConfigView extends javax.swing.JPanel {

    protected Controller controller;

    /**
     * Creates new form ConfigView
     *
     * @param controller
     */
    public ConfigView(Controller controller) {
        initComponents();
        this.controller = controller;
        this.setDaemons();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbl_db_running = new javax.swing.JLabel();
        btn_dbStop = new javax.swing.JButton();
        btn_dbStart = new javax.swing.JButton();
        btn_forceStopDb = new javax.swing.JButton();
        btn_connect = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lbl_barge_tool_connection = new javax.swing.JLabel();
        lbl_vs_connection = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btn_disconnect = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btn_hbaConf = new javax.swing.JButton();
        btn_servoyLog = new javax.swing.JButton();
        btn_vsBat = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btn_shutdown_vs = new javax.swing.JButton();

        setBackground(new java.awt.Color(15, 124, 160));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Database", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 18), new java.awt.Color(69, 74, 76))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(69, 74, 76));
        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Database State :");

        lbl_db_running.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_db_running.setForeground(new java.awt.Color(255, 255, 255));
        lbl_db_running.setText("...");

        btn_dbStop.setText("Stop");
        btn_dbStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dbStopActionPerformed(evt);
            }
        });

        btn_dbStart.setText("Start");
        btn_dbStart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_dbStartMouseClicked(evt);
            }
        });
        btn_dbStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dbStartActionPerformed(evt);
            }
        });

        btn_forceStopDb.setText("Force Stop Database");
        btn_forceStopDb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_forceStopDbActionPerformed(evt);
            }
        });

        btn_connect.setText("Connect");
        btn_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_connectActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Barge Tool Connection :");

        lbl_barge_tool_connection.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_barge_tool_connection.setForeground(new java.awt.Color(255, 255, 255));
        lbl_barge_tool_connection.setText("...");

        lbl_vs_connection.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_vs_connection.setForeground(new java.awt.Color(255, 255, 255));
        lbl_vs_connection.setText("...");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("VS Connection :");

        btn_disconnect.setText("Disconnect");
        btn_disconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_disconnectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_db_running, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                    .addComponent(lbl_barge_tool_connection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_vs_connection, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_connect, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_disconnect, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_dbStart, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_dbStop, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_forceStopDb, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_dbStop)
                    .addComponent(btn_dbStart)
                    .addComponent(jLabel1)
                    .addComponent(lbl_db_running))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lbl_barge_tool_connection)
                    .addComponent(btn_disconnect)
                    .addComponent(btn_connect))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lbl_vs_connection)
                    .addComponent(btn_forceStopDb))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Config", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 18), new java.awt.Color(69, 74, 76))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(69, 74, 76));
        jPanel2.setOpaque(false);

        btn_hbaConf.setText("pg_hba.conf");
        btn_hbaConf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_hbaConfMouseClicked(evt);
            }
        });

        btn_servoyLog.setText("servoy-log.txt");
        btn_servoyLog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_servoyLogMouseClicked(evt);
            }
        });

        btn_vsBat.setText("Vessel Solution.bat");
        btn_vsBat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_vsBatMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_hbaConf, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_servoyLog, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_vsBat, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_hbaConf)
                    .addComponent(btn_servoyLog)
                    .addComponent(btn_vsBat))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Runtime Client", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 18), new java.awt.Color(69, 74, 76))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(69, 74, 76));
        jPanel3.setOpaque(false);

        btn_shutdown_vs.setText("Shut Down Solution");
        btn_shutdown_vs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_shutdown_vsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(325, Short.MAX_VALUE)
                .addComponent(btn_shutdown_vs, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_shutdown_vs)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(389, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_dbStartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dbStartMouseClicked
        
    }//GEN-LAST:event_btn_dbStartMouseClicked

    private void btn_hbaConfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hbaConfMouseClicked
        Commands.open_pg_hba_conf();
    }//GEN-LAST:event_btn_hbaConfMouseClicked

    private void btn_servoyLogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_servoyLogMouseClicked
        Commands.open_servoy_log();
    }//GEN-LAST:event_btn_servoyLogMouseClicked

    private void btn_vsBatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_vsBatMouseClicked
        Commands.open_vessel_solution_bat();
    }//GEN-LAST:event_btn_vsBatMouseClicked

    private void btn_dbStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dbStopActionPerformed
        Commands.stopDatabase();
    }//GEN-LAST:event_btn_dbStopActionPerformed

    private void btn_dbStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dbStartActionPerformed
        Commands.startDatabase();
    }//GEN-LAST:event_btn_dbStartActionPerformed

    private void btn_forceStopDbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_forceStopDbActionPerformed
        Commands.forceStopDatabase();
    }//GEN-LAST:event_btn_forceStopDbActionPerformed

    private void btn_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_connectActionPerformed
        Database.getInstance().connect();
    }//GEN-LAST:event_btn_connectActionPerformed

    private void btn_disconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_disconnectActionPerformed
        Database.getInstance().disconnect();
    }//GEN-LAST:event_btn_disconnectActionPerformed

    private void btn_shutdown_vsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_shutdown_vsMouseClicked
        Method killVesselSolution = Utils.getMethodByName("killVesselSolution", Commands.class);
        ThreadManager.runInSeperateThread(killVesselSolution, null);
//        Commands.killVesselSolution();
    }//GEN-LAST:event_btn_shutdown_vsMouseClicked

    public void setDBStateLabel(boolean isRunning) {
        lbl_db_running.setText((isRunning) ? "Running" : "Not Running");
    }

    public void setBargeToolConnLabel(boolean isConnected) {
        lbl_barge_tool_connection.setText((isConnected) ? "Connected" : "Not Connected!");
    }

    public void setVSConnLabel(boolean isConnected) {
        lbl_vs_connection.setText((isConnected) ? "Connected" : "Not Connected!");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_connect;
    private javax.swing.JButton btn_dbStart;
    private javax.swing.JButton btn_dbStop;
    private javax.swing.JButton btn_disconnect;
    private javax.swing.JButton btn_forceStopDb;
    private javax.swing.JButton btn_hbaConf;
    private javax.swing.JButton btn_servoyLog;
    private javax.swing.JButton btn_shutdown_vs;
    private javax.swing.JButton btn_vsBat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbl_barge_tool_connection;
    private javax.swing.JLabel lbl_db_running;
    private javax.swing.JLabel lbl_vs_connection;
    // End of variables declaration//GEN-END:variables

    private void setDaemons() {
        DaemonManager.addSubscription(DaemonType.DATABASE, this, "setDBStateLabel");
        DaemonManager.addSubscription(DaemonType.BARGETOOL_CONNECTION, this, "setBargeToolConnLabel");
        DaemonManager.addSubscription(DaemonType.VESSEL_SOLUTION_CONNECTION, this, "setVSConnLabel");
    }
}
