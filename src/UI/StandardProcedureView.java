/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import UiHelpers.UiLib;
import App.Controller;
import HelperClasses.ProcedureListModel;
import Models.ProcedureModel;
import StandardProcedures.StandardProcedure;
import UiHelpers.CustomListCellRenderer;
import UiHelpers.ProcedureViewFactory;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import sunetibargetool.Config;

/**
 *
 * @author niekv
 */
public class StandardProcedureView extends javax.swing.JPanel {

    protected Controller controller;
    protected ProcedureModel model;
    private Thread progressBarAnimationThread;

    /**
     * Creates new form StandardProcedureView
     *
     * @param controller The controller to initialize the view with.
     * @param model The model to initialize the view with.
     */
    public StandardProcedureView(Controller controller, ProcedureModel model) {
        initComponents();
        this.controller = controller;
        this.model = model;
        fixUI();
        procedureList.setModel(model.getProcedureListModel());
        if (procedureList.getModel().getSize() > 0) {
            procedureList.setSelectedIndex(0);
        }
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
        searchField = new javax.swing.JTextField();
        procedureListScrollPane = new javax.swing.JScrollPane();
        procedureList = new javax.swing.JList<>();
        content_panel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(15, 124, 160));

        jPanel1.setBackground(new java.awt.Color(15, 124, 160));

        searchField.setBackground(new java.awt.Color(15, 124, 160));
        searchField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchFieldKeyTyped(evt);
            }
        });

        procedureList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        procedureList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                procedureListSelectionChanged(evt);
            }
        });
        procedureListScrollPane.setViewportView(procedureList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchField)
                    .addComponent(procedureListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(procedureListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                .addContainerGap())
        );

        content_panel.setBackground(Config.Colors.APPLICATION_DEFAULT_BLUE.getColor());

        javax.swing.GroupLayout content_panelLayout = new javax.swing.GroupLayout(content_panel);
        content_panel.setLayout(content_panelLayout);
        content_panelLayout.setHorizontalGroup(
            content_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 319, Short.MAX_VALUE)
        );
        content_panelLayout.setVerticalGroup(
            content_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 391, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(content_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(content_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyTyped
        // Implement search here, should probably with done by filtering on name
        // in the procedureModel thing or whatever.
    }//GEN-LAST:event_searchFieldKeyTyped

    private void procedureListSelectionChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_procedureListSelectionChanged
        // If statement is to only change UI once per change, not twice. ( https://stackoverflow.com/questions/12461627/ )
        if (!evt.getValueIsAdjusting()) {
            showSummaryView(getSelectedProcedure());
        }
    }//GEN-LAST:event_procedureListSelectionChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel content_panel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JList<String> procedureList;
    private javax.swing.JScrollPane procedureListScrollPane;
    private javax.swing.JTextField searchField;
    // End of variables declaration//GEN-END:variables

    /**
     * Various UI changes and fixes, used when initializing this view.
     */
    private void fixUI() {
        UiLib.styleTextField(searchField);
        procedureListScrollPane.setBorder(BorderFactory.createEmptyBorder());
        procedureList.setCellRenderer(new CustomListCellRenderer());
        procedureList.setBorder(new LineBorder(Config.Colors.APPLICATION_DEFAULT_GREY.getColor(), 2));
        procedureList.setBackground(Config.Colors.APPLICATION_DEFAULT_BLUE.getColor());
        content_panel.setLayout(new BorderLayout());
    }

    /**
     * Removes all components from the content_panel of this view, then adds the
     * given view to the content_panel.
     *
     * @param view The view that is to be set on the content_panel.
     */
    private void setContentPanel(JPanel view) {
        content_panel.removeAll();
        content_panel.add(view, BorderLayout.CENTER);
        content_panel.revalidate();
        content_panel.repaint();
    }

    /**
     * Method to enable/disable the components on this view.
     *
     * @param enabled True for enabled, false for disabled.
     */
    private void setViewEnabled(boolean enabled) {
        List<Component> compList = UiLib.getAllComponents(this);

        for (Component component : compList) {
            if (component instanceof JButton || component instanceof JList || component instanceof JTextField) {
                component.setEnabled(enabled);
            }
        }
    }

    public void showSummaryView(StandardProcedure procedure) {
        setContentPanel(ProcedureViewFactory.getSummaryView(procedure, this));
    }

    public void showWarningView(StandardProcedure procedure) {
        setContentPanel(ProcedureViewFactory.getWarningView(procedure, this));
    }

    public void showExecuteView(StandardProcedure procedure) {
        setContentPanel(ProcedureViewFactory.getExecuteView(procedure, this));
    }

    public StandardProcedure getSelectedProcedure() {
        ProcedureListModel dataModel = (ProcedureListModel) procedureList.getModel();
        int selectedIndex = procedureList.getSelectedIndex();
        return (StandardProcedure) dataModel.getElementAt(selectedIndex);
    }

    /**
     * Notifies the UI that a procedure has been started, disabling some
     * components and starting the progress bar.
     *
     * @param procedure StandardProcedure that is about to be executed.
     */
    public void notifyUIOnProcedureStart(StandardProcedure procedure) {
        setViewEnabled(false);
        JProgressBar progressBar = UiLib.getProgressBar(content_panel);
        double fillTime;
        if (procedure.getAverageExecutionTime() == 0.0) {
            fillTime = 10.0;
        } else {
            fillTime = procedure.getAverageExecutionTime();
        }
        (progressBarAnimationThread = UiLib.getAnimateProgressBarThread(progressBar, fillTime)).start();
    }

    /**
     * Notifies the UI that a procedure has ended it's execution. Method also
     * takes a boolean parameter indicating whether or not the execution was
     * successful.
     *
     * @param successful Whether or not the procedure has been executed
     * successfully.
     */
    public void notifyUIOnProcedureEnd(boolean successful) {
        setViewEnabled(true);
        progressBarAnimationThread.interrupt();
        // This is in place because it takes the JVM a little time
        // to interrupt the thread.
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
        }

        String endMsg = "Procedure Failed!";
        if (successful) {
            endMsg = "Procedure Successful!";
        }

        JProgressBar progressBar = UiLib.getProgressBar(content_panel);
        progressBar.setIndeterminate(false);
        progressBar.setString(endMsg);
        progressBar.setValue(100);
    }

}
