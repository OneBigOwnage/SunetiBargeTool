/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import UiHelpers.UiLib;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import App.Config;
import sunetibargetool.SunetiBargeTool;

/**
 *
 * @author niekv
 */
public class UserInterface extends javax.swing.JFrame {

    private String sidebarPlace;
    private JPanel sidebar;

    /**
     * Default constructor of this GUI
     */
    public UserInterface() {
        initComponents();
        initExtra();
    }

    /**
     * Constructor to create this GUI with
     *
     * @param sidebar
     * @param sidebarPlace
     */
    public UserInterface(JPanel sidebar, String sidebarPlace) {
        initComponents();
        initExtra();
        setupSidebar(sidebar, sidebarPlace);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Suneti Barge Tool");
        setMinimumSize(new java.awt.Dimension(930, 465));
        setName("mainFrame"); // NOI18N
        setSize(new java.awt.Dimension(1100, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UserInterface().setVisible(true);
            }
        });
    }

    private void initExtra() {
        // Set the icon in top left corner & taskbar.
        URL iconURL = getClass().getClassLoader().getResource("main_icon.png");
        ImageIcon icon = new ImageIcon(iconURL);
        setIconImage(icon.getImage());

        // Sets the layout to borderlayout, to easily place panels.
        setLayout(new BorderLayout());

        try {
            String laf = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(laf);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            System.out.println("Could not set custom LAF!");
        }

        // Define some colors for easy use in the rest of this method.
        Color defaultGrey = Config.Colors.APPLICATION_DEFAULT_GREY.getColor();
        Color defaultBlue = Config.Colors.APPLICATION_DEFAULT_BLUE.getColor();
        Color defaultWhite = Config.Colors.FONT_COLOR_WHITE.getColor();

        // Sets the (preferred) size of this JFrame, can be adjusted later on.
        setPreferredSize(new Dimension(1100, 500));
        setSize(getPreferredSize());

        // Give some decent looks to the progress bars in this application.
        UIManager.put("ProgressBar.background", defaultBlue);
        UIManager.put("ProgressBar.foreground", defaultGrey);
        UIManager.put("ProgressBar.selectionBackground", defaultGrey);
        UIManager.put("ProgressBar.selectionForeground", defaultBlue);

        // Style the comboboxes in this application.
        UIManager.put("ComboBox.background", defaultBlue);
        UIManager.put("ComboBox.selectionBackground", defaultBlue);
        UIManager.put("ComboBox.selectionForeground", defaultWhite);
        UIManager.put("ComboBox.font", UiLib.getDefaultFont());
    }

    /**
     *
     * @param sidebar
     * @param place
     */
    public final void setupSidebar(JPanel sidebar, String place) {
        this.sidebar = sidebar;
        this.sidebarPlace = place;
    }

    /**
     * Removes the current view from the GUI if there is any and sets given view
     * as the currently displayed view.
     *
     * @param view The view that is to be shown on the GUI.
     */
    public void showView(JPanel view) {
        String viewPlace = BorderLayout.CENTER;
        BorderLayout layout = (BorderLayout) getContentPane().getLayout();
        Component currentView = layout.getLayoutComponent(viewPlace);

        // If there currently a view displayed, remove it.
        if (currentView != null) {
            remove(currentView);
        }

        // Add the new view to the GUI.
        add(view, viewPlace);
        revalidate();
        repaint();
    }

    /**
     * Shows or hides the side-bar. Outputs a message when the side-bar is not
     * set, when the or is already in the given state.
     *
     * @param show True for show, false for hide.
     */
    public void showSidebar(boolean show) {
        // Don't do anything when either of these is not filled in.
        if (this.sidebar == null || this.sidebarPlace == null) {
            SunetiBargeTool.log("Not possible to set sidebar, either the sidebar or sidebarPlace is not filled in correctly");
            return;
        }

        try {
            BorderLayout layout = (BorderLayout) getContentPane().getLayout();
            if (show && null == layout.getLayoutComponent(this.sidebarPlace)) {
                getContentPane().add(this.sidebar, this.sidebarPlace);
            } else if (!show && layout.getLayoutComponent(this.sidebarPlace) != null) {
                layout.removeLayoutComponent(this.sidebar);
            }
        } catch (NullPointerException | IllegalArgumentException ex) {
            SunetiBargeTool.log("Currently not possible to show/hide the sidebar: " + ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
