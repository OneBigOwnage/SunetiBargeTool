/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daemons;

import HelperClasses.Utils;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import App.Config;

/**
 *
 * @author niekv
 */
public class DaemonManager {

    /**
     * Different types of Daemons.
     */
    public static enum DaemonType {
        BARGETOOL_CONNECTION, VESSEL_SOLUTION_CONNECTION, DATABASE
    };

    private static Map<DaemonType, BaseDaemon> daemonList;

    /**
     * Private so this can't be instantiated from the outside.
     */
    private DaemonManager() {

    }

    /**
     * Instantiate the daemonList as a new HashMap, and fill the map.
     */
    static {
        daemonList = new HashMap<>();
        createDaemons();
    }

    /**
     * Fills the daemonList map with all the available Daemons. Timeout is taken
     * from Config class.
     */
    private static void createDaemons() {
        // Get the sleeptime from the .properties file.
        int daemonSleepTime = Integer.parseInt(Config.get("daemon_sleep_time"));

        // Initialize all the daemons, and add them to the list.
        daemonList.put(DaemonType.BARGETOOL_CONNECTION, new ConnectionDaemon(daemonSleepTime));
        daemonList.put(DaemonType.DATABASE, new DatabaseDaemon(daemonSleepTime));
        daemonList.put(DaemonType.VESSEL_SOLUTION_CONNECTION, new VSConnectionDaemon(daemonSleepTime));
    }

    private static void startAllDaemons() {
        for (Map.Entry<DaemonType, BaseDaemon> daemon : daemonList.entrySet()) {
            new Thread(daemon.getValue()).start();
        }
    }

    public static void addSubscription(DaemonType type, Object obj, String methodName) {
        Method method = Utils.getMethodByName(methodName, obj);

        if (method != null) {
            daemonList.get(type).addSubscription(obj, method);
        } else {
            System.out.println("Method '" + methodName + "' does not exist for '" + obj.getClass().getName() + "'");
        }
    }
    
    public static void defaultLoad() {
        startAllDaemons();
    }
    
    public static void StartDaemon(DaemonType type) {
        // To be implemented
    }
}
