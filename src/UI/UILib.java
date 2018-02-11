/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import sunetibargetool.Config;

/**
 *
 * @author niekv
 */
public class UILib {

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

    public static void dispatchComponent(JPanel basePanel) {

    }

}
