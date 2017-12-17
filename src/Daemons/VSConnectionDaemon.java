/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daemons;

import Database.Database;
import Database.Query;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import sunetibargetool.SunetiBargeTool;

/**
 *
 * @author niekv
 */
public class VSConnectionDaemon extends BaseDaemon {

    private static boolean isConnected = false;
    
    public VSConnectionDaemon(int sleepTime) {
        super(sleepTime);
    }

    public static boolean isVesselSolutionConnected() {
        return isConnected;
    }
    
    public static void setIsVesselSolutionConnected() {
        if (!DatabaseDaemon.isDatabaseRunning()) {
            isConnected = false;
            return;
        } else if (!Database.getInstance().hasConnection()) {
            isConnected = false;
            return;
        }

        Query query = new Query(
                "SELECT * "
                + "FROM PG_STAT_ACTIVITY "
                + "WHERE datname = 'bcm' "
                + "AND application_name = '' "
                + "AND client_addr = '127.0.0.1';");
        Object vsConnResult = Database.getInstance().executeQuery(query);

        try {
            if (vsConnResult instanceof ResultSet && ((ResultSet) vsConnResult).next()) {
                isConnected = true;
            }
        } catch (SQLException ex) {
            SunetiBargeTool.log("Could not check if Vessel Solution is connected to database: " + ex);
        }
    }

    @Override
    public void dispatchSubscriptions() {
        setIsVesselSolutionConnected();

        for (DeamonSubscription sub : this.subscribtionList) {
            Method method = sub.getMethod();
            Object obj = sub.getObject();
            try {
                method.invoke(obj, isConnected);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                SunetiBargeTool.log("Something went wrong trying to execute VSConnectionDaemon: " + ex);
            }
        }
    }
}
