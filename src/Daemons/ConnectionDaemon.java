/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daemons;

import Database.Database;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author niekv
 */
public class ConnectionDaemon extends BaseDaemon implements Runnable {

    protected static boolean isConnected;
    private static Database db;

    public ConnectionDaemon(int sleepTime) {
        super(sleepTime);
        isConnected = false;
        db = Database.getInstance();
    }

    private static void setIsConnected() {
        isConnected = db.isConnected();
    }

    public static boolean isConnected() {
        return isConnected;
    }

    /**
     *
     */
    @Override
    public void dispatchSubscriptions() {
        setIsConnected();

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
