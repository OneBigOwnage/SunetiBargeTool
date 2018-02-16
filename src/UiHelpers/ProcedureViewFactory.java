/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UiHelpers;

import HelperClasses.Utils;
import StandardProcedures.StandardProcedure;
import UI.StandardProcedureView;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import sunetibargetool.Config;
import sunetibargetool.SunetiBargeTool;

/**
 *
 * @author niekv
 */
public class ProcedureViewFactory {

    public enum viewComponents {
        TITLE,
        CONTENT_PANE,
        NEXT_BUTTON,
        PREV_BUTTON,
        EXECUTE_BUTTON
    }

    /**
     * This method creates a summary view for a standard procedure. This view
     * has title, TextPane with the description of the procedure and a '' button
     * to go to the warning view.
     *
     * @param procedure The procedure used to initialize this view.
     * @param contentPanel
     * @return
     */
    public static JPanel getSummaryView(final StandardProcedure procedure, final StandardProcedureView contentPanel) {
        JPanel view = new JPanel(new GridBagLayout());
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
        constraints.gridy = 2;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(0, 6, 0, 25);

        view.add(getSummaryPane(procedure), constraints);

        // Adding the separator.
        constraints.gridy = 3;
        constraints.weighty = 0;

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.insets = new Insets(10, 6, 0, 25);

        view.add(getBottomLine(), constraints);

        // Adding the next button.
        constraints.gridy = 4;
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

        // Setting various other parameters of the view.
        view.setBackground(Config.Colors.APPLICATION_DEFAULT_BLUE.getColor());

        return view;
    }

    public static JPanel getWarningView(final StandardProcedure procedure, final StandardProcedureView contentPanel) {
        JPanel view = new JPanel(new GridBagLayout());
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
        //
        //
        // Adding the separator.
        constraints.gridy = 3;
        constraints.weighty = 0;

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.insets = new Insets(10, 6, 0, 25);

        view.add(getBottomLine(), constraints);

        // Adding the previous button.
        // Adding the next button.
        constraints.gridy = 4;
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

        return null;
    }

    public static JPanel getExecutionView(StandardProcedure procedure) {
        return null;
    }

    private static JLabel getTitleLabel(String title) {
        JLabel label = new JLabel(title);
        label.setFont(UiLib.getTitleFont());
        label.setForeground(Config.Colors.FONT_COLOR_WHITE.getColor());
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }

    private static JButton getPrevButton() {
        JButton button = new JButton("< Previous");
        UiLib.UIButtonHelper(button);
        button.setMinimumSize(new Dimension(125, button.getSize().height));
        return button;
    }

    private static JButton getNextButton() {
        JButton button = new JButton("Next >");
        UiLib.UIButtonHelper(button);
        button.setPreferredSize(new Dimension(125, button.getMinimumSize().height));
        button.setMinimumSize(new Dimension(125, button.getMinimumSize().height));
        return button;
    }

    private static JSeparator getBottomLine() {
        JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);
        sep.setForeground(Config.Colors.APPLICATION_DEFAULT_GREY.getColor());
        sep.setBackground(Config.Colors.APPLICATION_DEFAULT_GREY.getColor());
        sep.setMaximumSize(new Dimension(5, 1));
        sep.setMinimumSize(new Dimension(5, 1));
        sep.setPreferredSize(new Dimension(5, 1));
        return sep;
    }

    private static JTextPane getSummaryPane(StandardProcedure procedure) {
        JTextPane pane = new JTextPane();
        pane.setText(procedure.getDescription());
        pane.setBackground(Config.Colors.APPLICATION_DEFAULT_BLUE.getColor());
        pane.setForeground(Config.Colors.FONT_COLOR_WHITE.getColor());
        pane.setFont(UiLib.getDefaultFont());
        pane.setFocusable(false);
        pane.setCursor(Cursor.getDefaultCursor());
        return pane;
    }

    private ActionListener getActionListener(final StandardProcedureView procedureViewObject, final Method destinationMethod, final StandardProcedure procedure) {
        
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        };
    }

}
