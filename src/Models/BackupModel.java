/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import App.Controller;
import BackupPresets.BackupPreset;
import java.util.List;

/**
 *
 * @author suneti
 */
public class BackupModel extends BaseModel {

    /**
     * Default constructor for the BackupModel class.
     *
     * @param controller
     */
    public BackupModel(Controller controller) {
        super(controller);
    }

    /**
     * Method to retrieve a list of all the available backup presets.
     *
     * @return A list of all the available backup presets.
     */
    public List<BackupPreset> getBackupPresets() {

        
        return null;
    }
    
}
