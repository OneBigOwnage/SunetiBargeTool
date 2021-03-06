/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import App.Controller;
import Database.Query;
import App.ThreadManager;
import HelperClasses.Utils;
import Daemons.DaemonManager;
import Models.SQLModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import App.Config;
import Database.Database;

/**
 *
 * @author niekv
 */
public class SQLView extends javax.swing.JPanel {

    protected Controller controller;
    protected SQLModel model;
    private boolean isDatabaseRunning = false;
    private boolean hasConnection = false;

    /**
     * Creates new form SQLView
     *
     * @param controller
     * @param model
     */
    public SQLView(Controller controller, SQLModel model) {
        initComponents();
        this.controller = controller;
        this.model = model;
        fixTable();
        setDaemons();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        SQLTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        outputTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbl_conn = new javax.swing.JLabel();
        lbl_result = new javax.swing.JLabel();
        execSQLLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(15, 124, 160));

        jPanel1.setOpaque(false);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 60, 60)));

        SQLTextArea.setBackground(new java.awt.Color(15, 124, 160));
        SQLTextArea.setColumns(20);
        SQLTextArea.setFont(new java.awt.Font("Monospaced", 1, 16)); // NOI18N
        SQLTextArea.setForeground(new java.awt.Color(255, 255, 255));
        SQLTextArea.setLineWrap(true);
        SQLTextArea.setRows(5);
        SQLTextArea.setBorder(null);
        SQLTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SQLTextAreaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(SQLTextArea);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jScrollPane2.setBackground(new java.awt.Color(102, 102, 255));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 60, 60)));
        jScrollPane2.setOpaque(false);

        outputTable.setBackground(new java.awt.Color(15, 124, 160));
        outputTable.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        outputTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        outputTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        outputTable.setFillsViewportHeight(true);
        outputTable.setGridColor(new java.awt.Color(60, 60, 60));
        outputTable.setRowHeight(18);
        outputTable.setSelectionBackground(new java.awt.Color(89, 135, 150));
        jScrollPane2.setViewportView(outputTable);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 60, 60)));
        jPanel2.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Result:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Status:");

        lbl_conn.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        lbl_conn.setForeground(new java.awt.Color(255, 255, 255));
        lbl_conn.setText("Connected");

        lbl_result.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        lbl_result.setForeground(new java.awt.Color(255, 255, 255));
        lbl_result.setMaximumSize(new java.awt.Dimension(947, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_result, javax.swing.GroupLayout.DEFAULT_SIZE, 955, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbl_conn, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lbl_conn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_result, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        execSQLLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/execute_sql_icon.png"))); // NOI18N
        execSQLLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        execSQLLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                execSQLLabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1013, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(execSQLLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(execSQLLabel)
                        .addGap(27, 27, 27)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void execSQLLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_execSQLLabelMousePressed
        model.addQuery(this.SQLTextArea.getText());
        execQuery(evt.isShiftDown());
    }//GEN-LAST:event_execSQLLabelMousePressed

    private void SQLTextAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SQLTextAreaKeyPressed
        int keyCode = evt.getKeyCode();
        boolean isCtrlDown = evt.isControlDown();
        boolean isAltDown = evt.isAltDown();
        boolean isShiftDown = evt.isShiftDown();

        boolean isTriggerKeyDown = (keyCode == KeyEvent.VK_F5 || (keyCode == KeyEvent.VK_R && isCtrlDown) || (keyCode == KeyEvent.VK_ENTER && isCtrlDown));

        // Check any of the hotkeys for query execution are triggered.
        // These are: [F5], [ctrl] + [R], [ctrl] + [enter]
        if (isTriggerKeyDown) {
            model.addQuery(this.SQLTextArea.getText());
            execQuery(isShiftDown);
        }

        // Check if any of the "prev/next query" keys are pressed.
        // These are: [alt] + [left], [alt] + [right]
        if (isAltDown && keyCode == KeyEvent.VK_LEFT) {
            String prevText = model.getPrevious();
            if (prevText != null) {
                this.SQLTextArea.setText(prevText);
            }
        } else if (isAltDown && keyCode == KeyEvent.VK_RIGHT) {
            String nextText = model.getNext();
            if (nextText != null) {
                this.SQLTextArea.setText(nextText);
            }
        }
    }//GEN-LAST:event_SQLTextAreaKeyPressed

    private void fixTable() {
        // Set the color on the header of the table.
        JTableHeader header = outputTable.getTableHeader();
        header.setBackground(new Color(78, 147, 170));
        header.setBorder(null);

        // Set the background from grey to blue.
        jScrollPane2.getViewport().setBackground(new Color(15, 124, 160));
    }

    /**
     * Method that resizes the table column widths. The method adjusts them to
     * the contents of the cells. Also has a min and max width.
     *
     * @param table
     */
    private void fixTableCellWidth() {
        final TableColumnModel columnModel = outputTable.getColumnModel();

        // For each column of outputTable:
        for (int column = 0; column < outputTable.getColumnCount(); column++) {
            // Set a minimum width for the columns.
            int width = 135; // Min width

            // Check the width of each cell, and update 'width' if needed.
            for (int row = 0; row < outputTable.getRowCount(); row++) {
                TableCellRenderer renderer = outputTable.getCellRenderer(row, column);
                Component comp = outputTable.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 2, width);
            }

            // Set the maximum width for the columns.
            if (width > 500) {
                width = 500;
            }
            // And finally set the calculated width to the table column.
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    public void execQuery(boolean selectionOnly) {
        lbl_result.setText("Query running...");
        Query query;

        if (selectionOnly) {
            query = new Query(SQLTextArea.getSelectedText());
        } else {
            query = new Query(SQLTextArea.getText());
        }

        Database db = Database.getInstance();

        Object result = db.executeQuery(query);

        if (result instanceof String) {
            lbl_result.setText(result.toString());
        } else if (result instanceof Integer) {
            lbl_result.setText("Query executed succesfully, rows affected: " + result);
        } else if (result instanceof ResultSet) {
            lbl_result.setText("Result plotted in table");
            parseResultSetInTable((ResultSet) result);
        } else {
            lbl_result.setText("Unexpected response from database, please check the log!");
        }
    }

    private void parseResultSetInTable(ResultSet result) {
        DefaultTableModel tableModel = new DefaultTableModel();
        try {
            // Create table header.
            ResultSetMetaData metadata = result.getMetaData();
            int columnCount = metadata.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                tableModel.addColumn(metadata.getColumnLabel(i));
            }

            // Funnel ResultSet into tablemodel
            while (result.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = result.getObject(i);
                }
                // Add the fetched data to the model.
                tableModel.insertRow(result.getRow() - 1, rowData);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            lbl_result.setText("Unable to parse data into table: " + ex);
        }

        outputTable.setModel(tableModel);
        outputTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        fixTableCellWidth();
    }

    private void setDaemons() {
        DaemonManager.addSubscription(DaemonManager.DaemonType.DATABASE, this, "setDatabaseState");
        DaemonManager.addSubscription(DaemonManager.DaemonType.BARGETOOL_CONNECTION, this, "setConnectionState");
        Method labelMethod = Utils.getMethodByName("setLabels", this);
        ThreadManager.runInSeperateThread(labelMethod, this);
    }

    public void setDatabaseState(boolean isRunning) {
        this.isDatabaseRunning = isRunning;
    }

    public void setConnectionState(boolean isConnected) {
        this.hasConnection = isConnected;
    }

    public void setLabels() {
        while (true) {
            if (this.hasConnection) {
                lbl_conn.setText("There is an active connection with the database.");
            } else if (!this.hasConnection && this.isDatabaseRunning) {
                lbl_conn.setText("The database is running but there is no active connection.");
            } else if (!this.hasConnection && !this.isDatabaseRunning) {
                lbl_conn.setText("The database is not running");
            }

            int daemonSleepTime = Config.getInteger("daemon_sleep_time");

            // Make the thread sleep for 1 second, this is the daemon sleep time.
            try {
                Thread.sleep(daemonSleepTime);
            } catch (InterruptedException ex) {
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea SQLTextArea;
    private javax.swing.JLabel execSQLLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_conn;
    private javax.swing.JLabel lbl_result;
    private javax.swing.JTable outputTable;
    // End of variables declaration//GEN-END:variables
}
