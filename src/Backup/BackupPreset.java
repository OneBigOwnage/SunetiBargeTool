/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backup;

/**
 * Abstract template class for backup presets.
 *
 * @author niekv
 */
public abstract class BackupPreset {
    
    public enum PresetType {
        INCLUDE_SELECTED,
        EXCLUDE_SELECTED;
    }
    
    protected final PresetType presetType;
    protected final String[] selectedTables;
    
    
    public BackupPreset(PresetType presetType, String[] selectedTables) {
        this.presetType = presetType;
        this.selectedTables = selectedTables;
    }
    
    
    public String[] getSelectedTables() {
        return this.selectedTables;
    }
    
    public PresetType getPresetType() {
        return this.presetType;
    }
    
}
