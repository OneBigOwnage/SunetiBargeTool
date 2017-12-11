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
import java.util.regex.Pattern;
import org.apache.commons.exec.DefaultExecuteResultHandler;

/**
 *
 * @author niekv
 */
public class DatabaseDaemon extends BaseDaemon implements Runnable {

    public DatabaseDaemon(int sleepTime) {
        super(sleepTime);
    }

    /**
     * Method to check if database is currently running.
     *
     * @return {boolean}
     */
    public static boolean isDatabaseRunning() {
        // Construction of the command string.
        String pg_ctl = "\"C:\\vessel solution\\database\\postgres_db\\bin\\pg_ctl.exe\"";
        String dir = "\"C:\\vessel solution\\database\\database\"";

        String command = String.format("%s status -D %s", pg_ctl, dir);

        // Create outputstream, that output of the executing command will be written to.
        final OutputStream outputStream = new OutputStream() {
            private final StringBuilder strBuilder = new StringBuilder();

            @Override
            public void write(int b) throws IOException {
                this.strBuilder.append((char) b);
            }

            @Override
            public String toString() {
                return this.strBuilder.toString();
            }
        };

        CommandLineWrapper.executeCommand(command, CommandLineWrapper.DEFAULT_WORKING_DIR, outputStream, new DefaultExecuteResultHandler());

        try {
            // Don't go past here until the command has at least return either "server is running" or "no server running"
            while (!Utils.regExMatch("(.*server is running.*)|(.*no server running.*)", outputStream.toString(), Pattern.DOTALL)) {
                // Just do nothing...
            }
        } catch (Exception e) {
            System.out.println("This should be investigated...");
        }
        
        // Return true if the outputted line contains "server is running", false otherwise.
        return Utils.regExMatch("(.*server is running.*)", outputStream.toString(), Pattern.DOTALL);
    }

    @Override
    public void dispatchSubscriptions() {
        boolean isRunning = isDatabaseRunning();

        for (DeamonSubscription sub : this.subscribtionList) {
            Method method = sub.getMethod();
            Object obj = sub.getObject();
            try {
                method.invoke(obj, isRunning);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                System.out.println("Something went wrong trying to execute databaseDaemon: " + ex);
            }
        }
    }
}
