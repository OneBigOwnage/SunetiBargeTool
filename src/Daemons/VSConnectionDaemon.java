/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daemons;

import App.Database;
import App.Query;
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

    public VSConnectionDaemon(int sleepTime) {
        super(sleepTime);
    }

    public static boolean isVesselSolutionConnected() {
        if (!DatabaseDaemon.isDatabaseRunning()) {
            return false;
        } else if (!Database.getInstance().hasConnection()) {
            return false;
        }

        boolean isConnected = false;

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

        return isConnected;
    }

    @Override
    public void dispatchSubscriptions() {
        boolean isVSConnected = isVesselSolutionConnected();

        for (DeamonSubscription sub : this.subscribtionList) {
            Method method = sub.getMethod();
            Object obj = sub.getObject();
            try {
                method.invoke(obj, isVSConnected);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                SunetiBargeTool.log("Something went wrong trying to execute VSConnectionDaemon: " + ex);
            }
        }
    }
}
