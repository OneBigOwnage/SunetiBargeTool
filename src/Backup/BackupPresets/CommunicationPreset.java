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
public class CommunicationPreset extends BackupPreset {

    public CommunicationPreset() {
        super("Communication",
                "All communication related tables.",
                new String[] {"bcm_communication", "bcm_communication_backup", "bcm_communication_errors"},
                PresetType.INCLUDE_SELECTED);
    }
}
