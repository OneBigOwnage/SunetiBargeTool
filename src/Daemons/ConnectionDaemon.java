/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daemons;

import App.Database;
import Daemons.DeamonSubscription;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author niekv
 */
public class ConnectionDaemon extends BaseDaemon implements Runnable {

    private final int DAEMON_SLEEP_TIME;
    private final List<DeamonSubscription> subscriptionList;

    public ConnectionDaemon(int sleepTime) {
        this.DAEMON_SLEEP_TIME = sleepTime;
        this.subscriptionList = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            while (true) {
                this.dispatchSubscriptions(this.hasConnection());
                Thread.sleep(DAEMON_SLEEP_TIME);
            }
        } catch (InterruptedException ex) {
            System.out.println("ConnectionDaemon-Thread was interrupted!");
        }
    }

    public boolean hasConnection() {
        return Database.getInstance().hasConnection();
    }

    public void subscribeMethod(String methodName, Object obj) {
        try {
            Method method = obj.getClass().getDeclaredMethod(methodName, boolean.class);
            this.subscriptionList.add(new DeamonSubscription(obj, method));
        } catch (NoSuchMethodException | SecurityException ex) {
            System.out.println("Something went wrong trying to add a new subscription to ConnectionDaemon! " + ex);
        }
    }

    private void dispatchSubscriptions(boolean isConnected) {
        try {
            for (DeamonSubscription sub : this.subscriptionList) {
                Method method = sub.getMethod();
                Object obj = sub.getObject();

                method.invoke(obj, isConnected);
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            System.out.println("Something went wrong trying to execute a ConnectionDaemon-subscription: " + ex);
        }
    }

}
