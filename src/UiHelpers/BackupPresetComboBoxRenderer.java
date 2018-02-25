/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UiHelpers;

import Backup.BackupPreset;
import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import App.Config;

/**
 *
 * @author niekv
 */
public class BackupPresetComboBoxRenderer extends JLabel implements ListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        if (value != null && BackupPreset.class.isAssignableFrom(value.getClass())) {
            setText(((BackupPreset) value).getName());
        } else {
            setText("Preset Error!");
        }

        Color bgColor = Config.Colors.APPLICATION_DEFAULT_BLUE.getColor();
        Color fontColor = Config.Colors.FONT_COLOR_GREY.getColor();

        setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 5));

        // Generic stuff below
        setBackground(bgColor);
        setForeground(fontColor);
        setFont(UiLib.getDefaultFont());

        return this;
    }
}
