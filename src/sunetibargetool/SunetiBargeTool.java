/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sunetibargetool;

import Daemons.DatabaseDaemon;
import App.Controller;
import UI.UILib;

/**
 *
 * @author Productie 831
 */
public class SunetiBargeTool {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Load the configuration file from the JAR.
        Config.load();
        UILib.fillMaps();
        
        // Create new Controller and send action 'showLoginView'.
        Controller controller = new Controller();
        controller.afterLogin();
        
        // Start the database deamon, to check if the database is running.
        DatabaseDaemon.startDeamon();
    }
}
