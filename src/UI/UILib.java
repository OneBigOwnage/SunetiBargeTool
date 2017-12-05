/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author niekv
 */
public class UILib {
    
    private static final Map<String, Color> COLORMAP = new HashMap();
    
    public static void UIButtonHelper(final JButton button) {
        final Border btnUpBorder = new MatteBorder(1, 1, 1, 1, COLORMAP.get("BTN_NORMAL_BORDER"));
        final Border btnHoverBorder = new MatteBorder(1, 1, 1, 1, COLORMAP.get("BTN_HOVER_BORDER"));
        
        button.setBackground(COLORMAP.get("BTN_NORMAL_BG"));

        button.setBorder(btnUpBorder);
        button.setFocusPainted(false);
        button.setForeground(COLORMAP.get("BTN_NORMAL_FONT"));
        
        
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                // This is actually overridden by the LAF...
                button.setBackground(COLORMAP.get("BTN_DOWN_BG"));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(COLORMAP.get("BTN_NORMAL_BG"));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(COLORMAP.get("BTN_HOVER_FONT"));
                button.setBorder(btnHoverBorder);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(COLORMAP.get("BTN_NORMAL_FONT"));
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
        LineBorder lineBorder = new LineBorder(COLORMAP.get("TITLED_BORDER_BORDER_COLOR"));
        String borderTitle = ((TitledBorder) panel.getBorder()).getTitle();
        
        TitledBorder titledBorder = new TitledBorder(lineBorder, borderTitle);
        
        Font titleFont = ((TitledBorder) panel.getBorder()).getTitleFont();
        titledBorder.setTitleFont(titleFont);
        titledBorder.setTitleColor(COLORMAP.get("TITLED_BORDER_FONT_COLOR"));
        
        panel.setBorder(titledBorder);
    }
    
    
    public static void fillMaps() {
        // Color Map
        COLORMAP.put("BTN_NORMAL_BG",       new Color(15, 124, 160));
        COLORMAP.put("BTN_NORMAL_BORDER",   new Color(60, 60, 60));
        COLORMAP.put("BTN_NORMAL_FONT",     new Color(60, 60, 60));
        COLORMAP.put("BTN_HOVER_BORDER",    new Color(60, 60, 60));
        COLORMAP.put("BTN_HOVER_FONT",      new Color(255, 255, 255));
        COLORMAP.put("BTN_DOWN_BG",         new Color(8, 75, 96));
        
        COLORMAP.put("TITLED_BORDER_BORDER_COLOR", new Color(60, 60, 60));
        COLORMAP.put("TITLED_BORDER_FONT_COLOR", new Color(60, 60, 60));
    }
    
    public static void dispatchComponent(JPanel basePanel) {
        
    }
    
    
    
    
    
}
