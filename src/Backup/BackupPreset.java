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

    protected final String name;
    protected final String description;
    protected final String[] selectedTables;
    protected final PresetType presetType;

    
    public BackupPreset(String presetName, String presetDescription, String[] selectedTables, PresetType presetType) {
        this.name = presetName;
        this.description = presetDescription;
        this.selectedTables = selectedTables;
        this.presetType = presetType;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String[] getSelectedTables() {
        return this.selectedTables;
    }

    public PresetType getPresetType() {
        return this.presetType;
    }

}
