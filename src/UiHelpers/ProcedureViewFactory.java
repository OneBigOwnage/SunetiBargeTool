/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UiHelpers;

import StandardProcedures.ProcedureManager;
import StandardProcedures.StandardProcedure;
import UI.StandardProcedureView;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;
import App.Config;

/**
 *
 * @author niekv
 */
public class ProcedureViewFactory {

    public enum CONSTANTS {
        EXECUTION_STATUS_FIELD
    }
    
    private static JLabel getTitleLabel(String title) {
        JLabel label = new JLabel(title);
        label.setFont(UiLib.getTitleFont());
        label.setForeground(Config.Colors.FONT_COLOR_WHITE.getColor());
        label.setHorizontalAlignment(JLabel.LEADING);
        return label;
    }

    private static JButton getPrevButton() {
        JButton button = new JButton("< Previous");
        UiLib.UIButtonHelper(button);
        button.setPreferredSize(new Dimension(125, button.getMinimumSize().height));
        button.setMinimumSize(new Dimension(125, button.getMinimumSize().height));
        return button;
    }

    private static JButton getExecuteButton() {
        JButton button = new JButton("Execute Procedure");
        UiLib.UIButtonHelper(button);
        button.setPreferredSize(new Dimension(190, button.getMinimumSize().height));
        button.setMinimumSize(new Dimension(190, button.getMinimumSize().height));
        return button;
    }

    private static JButton getNextButton() {
        JButton button = new JButton("Next >");
        UiLib.UIButtonHelper(button);
        button.setPreferredSize(new Dimension(125, button.getMinimumSize().height));
        button.setMinimumSize(new Dimension(125, button.getMinimumSize().height));
        return button;
    }

    private static JSeparator getSeperatorLine() {
        JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);
        sep.setForeground(Config.Colors.APPLICATION_DEFAULT_GREY.getColor());
        sep.setBackground(Config.Colors.APPLICATION_DEFAULT_GREY.getColor());
        sep.setMaximumSize(new Dimension(5, 1));
        sep.setMinimumSize(new Dimension(5, 1));
        sep.setPreferredSize(new Dimension(5, 1));
        return sep;
    }

    private static JTextComponent getSummaryPane(StandardProcedure procedure) {
        JTextPane pane = new JTextPane();
        pane.setText(procedure.getDescription());
        pane.setBackground(Config.Colors.APPLICATION_DEFAULT_BLUE.getColor());
        pane.setForeground(Config.Colors.FONT_COLOR_WHITE.getColor());
        pane.setFont(UiLib.getDefaultFont());
        pane.setEditable(false);
        return pane;
    }

    private static JTextComponent getWarningPane(StandardProcedure procedure) {
        JTextPane pane = new JTextPane();

        String warningText = "";
        String[] warnings = procedure.getWarningMessages();
        if (warnings != null && warnings.length > 0) {
            for (String warn : procedure.getWarningMessages()) {
                warningText += String.format("• %s\n", warn);
            }
        } else {
            warningText = "There are no warnings for this procedure.";
        }

        pane.setText(warningText);
        pane.setBackground(Config.Colors.APPLICATION_DEFAULT_BLUE.getColor());
        pane.setForeground(Config.Colors.FONT_COLOR_WHITE.getColor());
        pane.setFont(UiLib.getDefaultFont());
        pane.setEditable(false);
        return pane;
    }

    private static Component getExecutionStatusComponent() {
        String startingText = "";

        JTextPane textPane = new JTextPane();
        textPane.setName(CONSTANTS.EXECUTION_STATUS_FIELD.name());
        textPane.setText(startingText);
        textPane.setBackground(Config.Colors.APPLICATION_DEFAULT_BLUE.getColor());
        textPane.setForeground(Config.Colors.FONT_COLOR_WHITE.getColor());
        textPane.setFont(UiLib.getDefaultFont());
        textPane.setEditable(false);
        textPane.setBorder(UiLib.createDefaultLineBorder());
        UiLib.setComponentPadding(textPane, 5, 5, 5, 5);
        
        JScrollPane scrollPane = new JScrollPane(textPane, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);
        
        return scrollPane;
    }

    private static JProgressBar getProgressBar() {
        JProgressBar progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setBorderPainted(false);
        progressBar.setFont(UiLib.getTitleFont());

        return progressBar;
    }

    /**
     * This method creates a summary view for a standard procedure. This view
     * has title, TextPane with the description of the procedure and a 'next'
     * button to go to the warning view.
     *
     * @param procedure The procedure used to initialize this view.
     * @param contentPanel The view on which to call the actions that are linked
     * to the 'next' button.
     * @return A JPanel view
     */
    public static JPanel getSummaryView(final StandardProcedure procedure, final StandardProcedureView contentPanel) {
        JPanel view = new JPanel(new GridBagLayout());
        view.setBackground(Config.Colors.APPLICATION_DEFAULT_BLUE.getColor());

        GridBagConstraints constraints = new GridBagConstraints();

        // Setting default constraints.
        constraints.weightx = 1;

        // Adding the title.
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 0;
        constraints.insets = new Insets(2, 6, 10, 25);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTH;

        view.add(getTitleLabel(procedure.getName()), constraints);

        // Adding the text view.
        constraints.gridy = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(0, 6, 0, 25);

        view.add(getSummaryPane(procedure), constraints);

        // Adding the separator.
        constraints.gridy = 2;
        constraints.weighty = 0;

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.insets = new Insets(10, 6, 0, 25);

        view.add(getSeperatorLine(), constraints);

        // Adding the next button.
        constraints.gridy = 3;
        constraints.weighty = 0;

        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(5, 0, 10, 25);

        JButton nextButton = getNextButton();

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPanel.showWarningView(procedure);
            }
        });
        view.add(nextButton, constraints);

        return view;
    }

    /**
     * This method creates a warning view for a standard procedure. This view
     * has title, TextPane with the warnings of the procedure, a 'prev' button
     * to return to the summary view and a 'next' button to go to the execute
     * view.
     *
     * @param procedure The procedure used to initialize this view.
     * @param contentPanel The view on which to call the actions that are linked
     * to the 'next' and 'previous' buttons.
     * @return A JPanel view
     */
    public static JPanel getWarningView(final StandardProcedure procedure, final StandardProcedureView contentPanel) {
        JPanel view = new JPanel(new GridBagLayout());
        view.setBackground(Config.Colors.APPLICATION_DEFAULT_BLUE.getColor());

        GridBagConstraints constraints = new GridBagConstraints();

        // Setting default constraints
        constraints.weightx = 1;

        // Adding the title.
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 0;
        constraints.insets = new Insets(2, 6, 10, 25);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTH;

        view.add(getTitleLabel(procedure.getName()), constraints);

        // Adding the warning content panel
        constraints.gridy = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(0, 6, 0, 25);

        view.add(getWarningPane(procedure), constraints);

        // Adding the separator.
        constraints.gridy = 2;
        constraints.weighty = 0;

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.insets = new Insets(10, 6, 0, 25);

        view.add(getSeperatorLine(), constraints);

        // Adding the previous button.
        constraints.gridy = 3;
        constraints.weighty = 0;

        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 6, 10, 0);

        JButton prevButton = getPrevButton();

        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPanel.showSummaryView(procedure);
            }
        });
        view.add(prevButton, constraints);

        // Adding the next button.
        constraints.gridy = 3;
        constraints.weighty = 0;

        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(5, 0, 10, 25);

        JButton nextButton = getNextButton();

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPanel.showExecuteView(procedure);
            }
        });
        view.add(nextButton, constraints);

        return view;
    }

    /**
     * This method creates a procedure execute view for a standard procedure.
     * This view has title, a progress-bar with the progress of the procedure, a
     * 'prev' button to return to the warning view and an 'execute' button to
     * execute the procedure.
     *
     * @param procedure The procedure used to initialize this view.
     * @param contentPanel The view on which to call the actions that are linked
     * to the 'previous' button.
     * @return A JPanel view
     */
    public static JPanel getExecuteView(final StandardProcedure procedure, final StandardProcedureView contentPanel) {
        JPanel view = new JPanel(new GridBagLayout());
        view.setBackground(Config.Colors.APPLICATION_DEFAULT_BLUE.getColor());

        GridBagConstraints constraints = new GridBagConstraints();

        // Setting default constraints.
        constraints.weightx = 1;

        // Adding the title.
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 0;
        constraints.insets = new Insets(2, 6, 10, 25);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTH;

        view.add(getTitleLabel(procedure.getName()), constraints);

        // Adding the execution-status-pane
        constraints.gridy = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10, 6, 0, 25);

        view.add(getExecutionStatusComponent(), constraints);

        // Adding the progress bar.
        constraints.gridy = 2;
        constraints.weighty = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.insets = new Insets(25, 6, 0, 25);

        view.add(getProgressBar(), constraints);

        // Adding the separator.
        constraints.gridy = 3;
        constraints.weighty = 0;

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.insets = new Insets(10, 6, 0, 25);

        view.add(getSeperatorLine(), constraints);

        // Adding the prev button.
        constraints.gridy = 4;
        constraints.weighty = 0;

        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 6, 10, 0);

        JButton prevButton = getPrevButton();

        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPanel.showWarningView(procedure);
            }
        });
        view.add(prevButton, constraints);

        // Adding the execute button.
        constraints.gridy = 4;
        constraints.weighty = 0;

        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 0, 10, 0);

        JButton executeButton = getExecuteButton();

        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProcedureManager.execute(procedure);
            }
        });
        view.add(executeButton, constraints);

        return view;
    }

}
