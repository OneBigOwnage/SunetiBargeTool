/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daemons;

import App.Database;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author niekv
 */
public class ConnectionDaemon extends BaseDaemon implements Runnable {

    public ConnectionDaemon(int sleepTime) {
        super(sleepTime);
    }
    
    public static boolean hasConnection() {
        return Database.getInstance().hasConnection();
    }

    /**
     *
     */
    @Override
    public void dispatchSubscriptions() {
        boolean isConnected = hasConnection();
        
        try {
            for (DeamonSubscription sub : this.subscribtionList) {
                Method method = sub.getMethod();
                Object obj = sub.getObject();

                method.invoke(obj, isConnected);
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            System.out.println("Something went wrong trying to execute a ConnectionDaemon-subscription: " + ex);
        }
    }
}
