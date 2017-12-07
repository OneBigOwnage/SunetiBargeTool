/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sunetibargetool;

import App.Controller;
import Daemons.DaemonManager;
import UI.UILib;

/**
 *
 * @author Productie 831
 */
public class SunetiBargeTool {

    private static Controller controller;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Load the configuration file from the JAR.
        Config.load();
        UILib.fillMaps();

        // Create new Controller and send action 'showLoginView'.
        controller = new Controller();
        controller.showLoginView();
//        controller.afterLogin();

        // Bootstrap all the daemons, via the DaemonManager.
        try {
            Class.forName(DaemonManager.class.getName());
        } catch (ClassNotFoundException ex) {
            controller.log("Class '" + DaemonManager.class.getName() + "' not found! " + ex);
        }
    }

    public static Controller getController() {
        return controller;
    }

}
