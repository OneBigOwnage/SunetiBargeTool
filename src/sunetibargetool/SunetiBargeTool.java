/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sunetibargetool;

import App.Config;
import App.Commands;
import App.Controller;
import App.ThreadManager;
import HelperClasses.Utils;
import Database.Database;
import Daemons.DaemonManager;
import Daemons.DatabaseDaemon;
import Daemons.VSConnectionDaemon;
import java.lang.reflect.Method;

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

        Method appStartMethod = Utils.getMethodByName("appStart", SunetiBargeTool.class);
        ThreadManager.runInSeperateThread(appStartMethod, null);

        AddShutDownHook();

        // Bootstrap all the daemons, via the DaemonManager.
        DaemonManager.defaultLoad();

        // Create new Controller and send action 'showLoginView'.
        controller = new Controller();

        // *** IMPORTANT ***
        // In production, uncomment first line and delete second line!
        // Second line is used to skip the Login part of the application.
//        controller.showLoginView();
        controller.afterLogin();
        // *** IMPORTANT ***
    }

    /**
     * Forwards given string to the controller to log. If the controller is not
     * initialized yet, System.out.println() will be used.
     *
     * @param text The text that is to be logged.
     */
    public static void log(String text) {
        if (controller != null) {
            controller.log(text);
        } else {
            System.out.println("LOG: " + text);
        }
    }

    public static void appStart() {
        boolean autoStart = Boolean.parseBoolean(Config.get("appstart_start_db"));
        boolean autoConnect = Boolean.parseBoolean(Config.get("appstart_connect_db"));
        int appStartWaitTime = Config.getInteger("appstart_wait_time");

        if (autoStart && !DatabaseDaemon.isDatabaseRunning()) {
            log("Going to start database...");
            Commands.startDatabase();
        }

        if (autoConnect) {
            log("Going to auto connect to the database...");
            try {
                Thread.sleep(appStartWaitTime);
            } catch (InterruptedException ex) {
                log("appStart-thread was interrupted!");
            }
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
                    log("Disconnecting from database & shutting down database...");
                    Database.getInstance().disconnect();
                    Commands.stopDatabase();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                } else {
                    log("Disconnecting from database...");
                    Database.getInstance().disconnect();
                }
            }
        });
    }

    /**
     * Getter for the main Controller in this application.
     *
     * @return
     */
    public static Controller getController() {
        return controller;
    }
}
