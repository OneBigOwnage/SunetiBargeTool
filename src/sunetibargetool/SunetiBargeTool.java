/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sunetibargetool;

import App.Commands;
import App.Controller;
import Database.Database;
import Daemons.DaemonManager;
import Daemons.DatabaseDaemon;
import Daemons.VSConnectionDaemon;
import UI.UILib;

/**
 *
 * @author niekv
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

        appStart();
        AddShutDownHook();

        // Create new Controller and send action 'showLoginView'.
        controller = new Controller();
//        controller.showLoginView();
        controller.afterLogin();

        DaemonManager.defaultLoad();

        // Bootstrap all the daemons, via the DaemonManager.
        DaemonManager.defaultLoad();
    }

    public static void log(String text) {
        if (controller != null) {
            controller.log(text);
        }
    }

    private static void appStart() {
        boolean autoStart = Boolean.parseBoolean(Config.get("appstart_start_db"));
        boolean autoConnect = Boolean.parseBoolean(Config.get("appstart_connect_db"));

        if (autoStart && !DatabaseDaemon.isDatabaseRunning()) {
            Commands.startDatabase();
        }

        if (autoConnect) {
            Database.getInstance().connect();
        }
    }

    public static void exitApp() {
        System.exit(0);
    }

    private static void AddShutDownHook() {
        // Add shutdownHook to disconnect from database, so it is not left running when Barge Tool is shut down.
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                if (!VSConnectionDaemon.isVesselSolutionConnected()) {
                    System.out.println("Disconnecting from database & shutting down database...");
                    Database.getInstance().disconnect();
                    Commands.stopDatabase();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                } else {
                    System.out.println("Disconnecting from database...");
                    Database.getInstance().disconnect();
                }
            }
        });
    }
}
