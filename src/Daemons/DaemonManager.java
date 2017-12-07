/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daemons;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import sunetibargetool.Config;

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
        int daemonSleepTime = Integer.parseInt(Config.get("connection_daemon_sleep_time"));
        
        daemonList.put(DaemonType.BARGETOOL_CONNECTION, new ConnectionDaemon(daemonSleepTime));
        daemonList.put(DaemonType.DATABASE, new DatabaseDaemon(daemonSleepTime));
    }

    public static void addSubscription(DaemonType type, Object obj, String methodName) {
        Method method = null;
        try {
            Method[] allClassMethods = obj.getClass().getDeclaredMethods();
            for (Method m : allClassMethods) {
                if (m.getName().equals(methodName)) {
                    method = m;
                }
            }
        } catch (Exception e) {
        }
        if (method != null) {
            daemonList.get(type).addSubscription(obj, method);
        }
    }
    
    public static <T> T executeDaemonMethod(DaemonType type) {
        BaseDaemon daemon = daemonList.get(type);
        return daemon.daemonAction();
    }
}
