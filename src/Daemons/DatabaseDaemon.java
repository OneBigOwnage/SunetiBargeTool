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

    protected static boolean isDatabaseRunning = false;
    
    public DatabaseDaemon(int sleepTime) {
        super(sleepTime);
    }

    public static boolean isDatabaseRunning() {
        return isDatabaseRunning;
    }
    
    public void setIsDatabaseRunning() {
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
        } catch (Exception ex) {
            outputStream.toString();
            System.out.println("This should be investigated... " + ex);
        }
        
        // Return true if the outputted line contains "server is running", false otherwise.
        isDatabaseRunning = Utils.regExMatch("(.*server is running.*)", outputStream.toString(), Pattern.DOTALL);
    }

    @Override
    public void dispatchSubscriptions() {
        setIsDatabaseRunning();

        for (DeamonSubscription sub : this.subscribtionList) {
            Method method = sub.getMethod();
            Object obj = sub.getObject();
            try {
                method.invoke(obj, isDatabaseRunning);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                System.out.println("Something went wrong trying to execute databaseDaemon: " + ex);
            }
        }
    }
}
