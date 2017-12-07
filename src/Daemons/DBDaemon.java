/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daemons;

import App.CommandLineWrapper;
import App.Utils;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Pattern;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import sunetibargetool.Config;

/**
 *
 * @author niekv
 */
public class DBDaemon implements Runnable {

    private static DBDaemon instance = null;
    
    private List<DeamonSubscription> databaseDeamonList = new ArrayList<>();
    private final int DAEMON_SLEEP_TIME;
    
    private DBDaemon() {
        this.DAEMON_SLEEP_TIME = Integer.parseInt(Config.get("daemon_sleep_time"));
    }
    
    
    
    @Override
    public void run() {
        try {
            while (true) {
                dispatchDatabaseSubscriptions(isDatabaseRunning());
                Thread.sleep(DAEMON_SLEEP_TIME);
            }
        } catch (InterruptedException ex) {
            System.out.println("Thread was interrupted!");
        }
    }

    /**
     * Starts the instance of the DatabaseDaemon as a Daemon Service.
     * 
     */
    public static void startDeamon() {
        Thread t = new Thread(getInstance());
        t.start();
    }



    /**
     * Method to register callback methods around the application to be called every time the database state is checked.
     * The method passed in must have one, and only one parameter: a boolean.
     * Upon calling, this boolean will be true for Running and false for Not Running.
     * 
     * @param methodName The name of the method you want to subscribe.
     * @param _class The class name that the method is to be found in.
     */
    public void registerDatabaseRunningCallback(String methodName, Object obj) {
        try {
            Method method = obj.getClass().getDeclaredMethod(methodName, boolean.class);
            this.databaseDeamonList.add(new DeamonSubscription(obj, method));
        } catch (NoSuchMethodException | SecurityException ex) {
            System.out.println("Something went wrong trying to add a new subscription to DBDeamon! " + ex);
        }
    }
   
    private void dispatchDatabaseSubscriptions(boolean isRunning) {
        try {
            for (DeamonSubscription sub : databaseDeamonList) {
                Method method = sub.getMethod();
                Object obj = sub.getObject();
                
                method.invoke(obj, isRunning);
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            System.out.println("Something went wrong trying to execute ");
        }
    }
    
    public static DBDaemon getInstance() {
        if (instance == null) {
            instance = new DBDaemon();
        }
        return instance;
    }

}
