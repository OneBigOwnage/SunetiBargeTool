/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UiHelpers;

import StandardProcedures.StandardProcedure;
import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import sunetibargetool.Config;

/**
 *
 * @author niekv
 */
public class CustomListCellRenderer extends DefaultListCellRenderer implements javax.swing.ListCellRenderer<Object> {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        // Cast the object to a Standard Procedure.
        StandardProcedure procedure = (StandardProcedure) value;
        
        // Set the shown text to be the name of the procedure.
        setText(procedure.getName());
        
        // Creating the default background color, font color and border.
        Color bgColor = Config.Colors.APPLICATION_DEFAULT_BLUE.getColor();
        Color fontColor = Config.Colors.FONT_COLOR_GREY.getColor();

        Border border = BorderFactory.createCompoundBorder(
                new LineBorder(Config.Colors.TITLED_BORDER_BORDER_COLOR.getColor(), 1),
                BorderFactory.createEmptyBorder(0, 5, 0, 5));
        
        
        // Cell has a different border when it has focus.
        if (cellHasFocus) {
            // do something with the border here...
        }

        // Cell has different background/font colors when selected.
        if (isSelected) {
            bgColor = Config.Colors.LIST_SELECTED_ITEM_BACKGROUND.getColor();
        }
        
        // Setting the generated values:
        setBackground(bgColor);
        setForeground(fontColor);
        setBorder(border);
        
        // Generic stuff below
        setEnabled(true);
        setFont(UiLib.getDefaultFont());
            
        return this;
    }
    
}
