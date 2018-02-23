/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backup.BackupPresets;

import Backup.BackupPreset;

/**
 *
 * @author niekv
 */
public class SimplePreset extends BackupPreset {
    
    public SimplePreset() {
        super(PresetType.EXCLUDE_SELECTED, new String[0]);
    }
    
    
}
