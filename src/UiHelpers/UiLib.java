/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UiHelpers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import sunetibargetool.Config;

/**
 *
 * @author niekv
 */
public final class UiLib {

    public static void UIButtonHelper(final JButton button) {
        final Border btnUpBorder = new MatteBorder(1, 1, 1, 1, Config.Colors.BTN_NORMAL_BORDER.getColor());
        final Border btnHoverBorder = new MatteBorder(1, 1, 1, 1, Config.Colors.BTN_HOVER_BORDER.getColor());

        button.setBackground(Config.Colors.BTN_NORMAL_BG.getColor());

        button.setBorder(btnUpBorder);
        button.setFocusPainted(false);
        button.setForeground(Config.Colors.BTN_NORMAL_FONT.getColor());

        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // This is actually overridden by the LAF...
                button.setBackground(Config.Colors.BTN_DOWN_BG.getColor());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(Config.Colors.BTN_NORMAL_BG.getColor());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Config.Colors.BTN_HOVER_FONT.getColor());
                button.setBorder(btnHoverBorder);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(Config.Colors.BTN_NORMAL_FONT.getColor());
                button.setBorder(btnUpBorder);
            }
        });
    }

    /**
     * Returns all components in a given Container object. This method uses
     * recursion to 'deep' get all components, so it will also get components
     * that are in sub-containers of the given container object.
     *
     * @param container The container from which to get all the components.
     * @return
     */
    public static List<Component> getAllComponents(final Container container) {
        Component[] componentArray = container.getComponents();
        List<Component> componentList = new ArrayList<>();

        for (Component component : componentArray) {
            componentList.add(component);
            if (component instanceof Container) {
                componentList.addAll(getAllComponents((Container) component));
            }
        }

        return componentList;
    }

    public static void UITitledBorderHelper(final JPanel panel) {
        LineBorder lineBorder = new LineBorder(Config.Colors.TITLED_BORDER_BORDER_COLOR.getColor());
        String borderTitle = ((TitledBorder) panel.getBorder()).getTitle();

        TitledBorder titledBorder = new TitledBorder(lineBorder, borderTitle);

        Font titleFont = ((TitledBorder) panel.getBorder()).getTitleFont();
        titledBorder.setTitleFont(titleFont);
        titledBorder.setTitleColor(Config.Colors.TITLED_BORDER_FONT_COLOR.getColor());

        panel.setBorder(titledBorder);
    }

    /**
     * Style a JTextField with application standard colors, shapes, font etc...
     *
     * @param textField
     */
    public static void styleTextField(JTextField textField) {
        // Create all styles and colors.

        Border greyBorder = new LineBorder(Config.Colors.TITLED_BORDER_BORDER_COLOR.getColor());
        Border paddingBorder = BorderFactory.createEmptyBorder(0, 5, 0, 5);
        Border totalBorder = BorderFactory.createCompoundBorder(greyBorder, paddingBorder);

        Color backgroundColor = Config.Colors.APPLICATION_DEFAULT_BLUE.getColor();
        Color fontColor = Config.Colors.FONT_COLOR_WHITE.getColor();

        // Implement the styles & colors into the textfield.
        textField.setFont(getDefaultFont());
        textField.setBorder(totalBorder);
        textField.setBackground(backgroundColor);
        textField.setForeground(fontColor);
    }

    /**
     * Gets the application default font; Segoe UI 14pt.
     *
     * @return The default font of this application
     */
    public static Font getDefaultFont() {
        return new Font("Segoe UI", Font.PLAIN, 14);
    }

    /**
     * Gets the application default title font; Segoe UI 16pt.
     *
     * @return The default title font of this application.
     */
    public static Font getTitleFont() {
        return new Font("Segoe UI", Font.BOLD, 16);
    }

    /**
     * Animates given progress bar from 0 to 100 percent in given amount of
     * seconds. This method can be overloaded to give in start and end points
     * for more flexibility.
     *
     * @param progressBar The JProgressBar you want to animate.
     * @param time The time in seconds from start to end of the animation.
     */
    public static void animateProgressBar(final JProgressBar progressBar, final double time) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Thread.sleep() takes an integer number as milliseconds.
                // So we multiply the time by 1000 to get milliseconds from
                // seconds, and then cast it to an integer which also
                // automatically rounds down.
                int timePerPercent = (int) time * 10;

                for (int percentFilled = 1; percentFilled <= 100; percentFilled++) {
                    progressBar.setValue(percentFilled);

                    try {
                        Thread.sleep(timePerPercent);
                    } catch (InterruptedException ex) {
                        System.out.println("ProgressBar-Thread was interrupted!\n" + ex);
                    }
                }
            }
        }).start();
    }

    /**
     * Creates and returns a line border with the default application grey color
     * and thickness 2.
     *
     * @return A grey LineBorder with thickness 2.
     */
    public static Border createDefaultLineBorder() {
        return new LineBorder(Config.Colors.APPLICATION_DEFAULT_GREY.getColor(), 2);
    }
}
