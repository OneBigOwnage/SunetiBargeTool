/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daemons;

import sunetibargetool.SunetiBargeTool;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import sunetibargetool.Config;

/**
 *
 * @author niekv
 */
public abstract class BaseDaemon implements Runnable {
    
    protected final int daemonSleepTime;
    protected final int daemonStartupSleepTime;
    protected final List<DeamonSubscription> subscribtionList;
    
    protected BaseDaemon(int sleepTime) {
        this.daemonSleepTime = sleepTime;
        this.daemonStartupSleepTime = Config.getInteger("daemon_startup_wait_time");
        this.subscribtionList = new ArrayList<>();
    }
            
            
    /**
     * Not implemented because this should be implemented one class lower.
     */
    @Override
    public void run() {
        // Give the daemon an initial wait time, to wait for
        // all other processes to be started.
        try {
           Thread.sleep(this.daemonStartupSleepTime); 
        } catch (InterruptedException e) {
            SunetiBargeTool.log("A Daemon thread was interruped on startup!");
        }
        
        while (true) {            
            this.dispatchSubscriptions();
            try {
                Thread.sleep(this.daemonSleepTime);
            } catch (InterruptedException ex) {
                SunetiBargeTool.log("Daemon thread was interrupted!" + ex);
            }
        }
    }
    
    /**
     * Method used to add a method to the internal method list in the daemon.
     * @param object
     * @param method
     */
    public void addSubscription(Object object, Method method) {
        this.subscribtionList.add(new DeamonSubscription(object, method));
    }
    
    public abstract void dispatchSubscriptions();
}
