/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import App.Controller;
import Models.BackupModel;
import UiHelpers.UiLib;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import javax.swing.DefaultListModel;
import sunetibargetool.Config;

public class BackupView extends javax.swing.JPanel {

    private final Controller controller;
    private final BackupModel model;

    public enum SWITCH_TYPE {
        TO_LEFT,
        TO_RIGHT;
    }

    /**
     * Creates new form BackupView
     *
     * @param controller The controller used to initialize this view.
     * @param model The model used to initialize this view.
     */
    public BackupView(Controller controller, BackupModel model) {
        initComponents();
        initComponentsExtra();
        this.controller = controller;
        this.model = model;
        loadData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        presetPanel = new javax.swing.JPanel();
        loadPresetButton = new javax.swing.JButton();
        presetPicker = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        backupPresetDescription = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        tablePanel = new javax.swing.JPanel();
        createBackupButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        excludedTablesList = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        includedTablesList = new javax.swing.JList<>();

        setBackground(new java.awt.Color(15, 124, 160));
        setPreferredSize(new java.awt.Dimension(967, 479));

        loadPresetButton.setText("Load Preset");
        loadPresetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onLoadPresetButtonClicked(evt);
            }
        });

        presetPicker.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Choose a Backup Preset --", "Preset 01", "Preset 02" }));
        presetPicker.setBorder(null);

        jScrollPane1.setBorder(null);

        backupPresetDescription.setEditable(false);
        backupPresetDescription.setBorder(null);
        jScrollPane1.setViewportView(backupPresetDescription);

        jLabel1.setFont(UiLib.getTitleFont());
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Backup Presets");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout presetPanelLayout = new javax.swing.GroupLayout(presetPanel);
        presetPanel.setLayout(presetPanelLayout);
        presetPanelLayout.setHorizontalGroup(
            presetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(presetPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(presetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(loadPresetButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(presetPicker, 0, 273, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        presetPanelLayout.setVerticalGroup(
            presetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, presetPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(presetPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(loadPresetButton)
                .addContainerGap())
        );

        createBackupButton.setText("Create Backup");

        jScrollPane2.setBorder(UiLib.createDefaultLineBorder());
        jScrollPane2.setOpaque(false);

        excludedTablesList.setBackground(Config.Colors.APPLICATION_DEFAULT_BLUE.getColor());
        excludedTablesList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                excludedTablesListMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(excludedTablesList);

        jScrollPane3.setBorder(UiLib.createDefaultLineBorder());

        includedTablesList.setBackground(Config.Colors.APPLICATION_DEFAULT_BLUE.getColor());
        includedTablesList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                includedTablesListMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(includedTablesList);

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tablePanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 306, Short.MAX_VALUE)
                .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(createBackupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tablePanelLayout.createSequentialGroup()
                .addContainerGap(174, Short.MAX_VALUE)
                .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(128, 128, 128)
                .addComponent(createBackupButton)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(presetPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(presetPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void onLoadPresetButtonClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onLoadPresetButtonClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_onLoadPresetButtonClicked

    private void excludedTablesListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_excludedTablesListMousePressed
        Rectangle listCellBounds = excludedTablesList.getCellBounds(excludedTablesList.getFirstVisibleIndex(), excludedTablesList.getLastVisibleIndex());
        if (null != listCellBounds && listCellBounds.contains(evt.getPoint())) {
            switchTable(SWITCH_TYPE.TO_RIGHT);
        } else {
            excludedTablesList.clearSelection();
        }
    }//GEN-LAST:event_excludedTablesListMousePressed

    private void includedTablesListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_includedTablesListMousePressed
        Rectangle listCellBounds = includedTablesList.getCellBounds(includedTablesList.getFirstVisibleIndex(), includedTablesList.getLastVisibleIndex());
        if (null != listCellBounds && listCellBounds.contains(evt.getPoint())) {
            switchTable(SWITCH_TYPE.TO_LEFT);
        } else {
            includedTablesList.clearSelection();
        }
    }//GEN-LAST:event_includedTablesListMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane backupPresetDescription;
    private javax.swing.JButton createBackupButton;
    private javax.swing.JList<String> excludedTablesList;
    private javax.swing.JList<String> includedTablesList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton loadPresetButton;
    private javax.swing.JPanel presetPanel;
    private javax.swing.JComboBox<String> presetPicker;
    private javax.swing.JPanel tablePanel;
    // End of variables declaration//GEN-END:variables

    /**
     * Method to do various fixes and style customizations to the GUI.
     */
    private void initComponentsExtra() {
        // Set various backgrounds to the default color of this application.
        this.setBackground(Config.Colors.APPLICATION_DEFAULT_BLUE.getColor());
        tablePanel.setBackground(Config.Colors.APPLICATION_DEFAULT_BLUE.getColor());
        presetPanel.setBackground(Config.Colors.APPLICATION_DEFAULT_BLUE.getColor());
        backupPresetDescription.setBackground(Config.Colors.APPLICATION_DEFAULT_BLUE.getColor());

        // Style the preset picker.
        // TODO: Actually style the preset picker.
        presetPicker.setBounds(new Rectangle(12, 9, 331, 26));

        // Style the description pane.
        UiLib.setComponentPadding(backupPresetDescription, 5, 5, 5, 5);
        backupPresetDescription.setFont(UiLib.getDefaultFont());
    }

    /**
     * Method to load all data from the model, into the view.
     */
    private void loadData() {
        DefaultListModel exModel = new DefaultListModel();
        DefaultListModel inModel = new DefaultListModel();

        exModel.addElement("table 01");
        exModel.addElement("table 02");
        exModel.addElement("table 03");

        inModel.addElement("table 01");
        inModel.addElement("table 02");
        inModel.addElement("table 03");

        this.excludedTablesList.setModel(exModel);
        this.includedTablesList.setModel(inModel);
    }

    /**
     * Method to draw a separation line between the two main panels in this
     * view.
     *
     * @param graphics
     */
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        // Draw a line.
        int margin = 0;
        Graphics2D graphicsNew = (Graphics2D) graphics;
        Line2D line = new Line2D.Float(presetPanel.getWidth(), 0 + margin, presetPanel.getWidth(), getHeight() - margin);
        graphicsNew.draw(line);
    }

    /**
     * Method to pass a table from one side to the other.
     *
     * @param switchType The direction of the change.
     */
    public void switchTable(SWITCH_TYPE switchType) {
        int leftIndex = excludedTablesList.getSelectedIndex();
        int rightIndex = includedTablesList.getSelectedIndex();
        DefaultListModel<String> leftModel = (DefaultListModel) excludedTablesList.getModel();
        DefaultListModel<String> rightModel = (DefaultListModel) includedTablesList.getModel();

        if (switchType.equals(SWITCH_TYPE.TO_RIGHT) && leftIndex >= 0) {
            String tableName = leftModel.getElementAt(leftIndex);

            rightModel.add(0, tableName);
            leftModel.removeElement(tableName);

            includedTablesList.setModel(rightModel);
            excludedTablesList.setModel(leftModel);

        } else if (switchType.equals(SWITCH_TYPE.TO_LEFT) && rightIndex >= 0) {
            String tableName = rightModel.getElementAt(rightIndex);

            leftModel.add(0, tableName);
            rightModel.removeElement(tableName);

            includedTablesList.setModel(rightModel);
            excludedTablesList.setModel(leftModel);
        }
    }
}
