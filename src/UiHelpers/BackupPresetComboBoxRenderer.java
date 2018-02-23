/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UiHelpers;

import Backup.BackupPreset;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import sunetibargetool.Config;

/**
 *
 * @author niekv
 */
public class BackupPresetComboBoxRenderer extends JLabel implements ListCellRenderer {

    /**
     *
     * @param list
     * @param value
     * @param index
     * @param isSelected
     * @param cellHasFocus
     * @return
     */
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        if (value != null && BackupPreset.class.isAssignableFrom(value.getClass())) {
            setText(((BackupPreset) value).getName());
        } else {
            setText("I dont get it...");
        }

        Color bgColor = Config.Colors.APPLICATION_DEFAULT_BLUE.getColor();
        Color fontColor = Config.Colors.FONT_COLOR_GREY.getColor();

        // Generic stuff below
        setBackground(bgColor);
        setForeground(fontColor);
        setFont(UiLib.getDefaultFont());

        return this;
    }
}
