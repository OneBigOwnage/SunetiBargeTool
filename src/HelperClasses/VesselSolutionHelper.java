/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelperClasses;

import App.CommandLineWrapper;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.jutils.jprocesses.JProcesses;
import org.jutils.jprocesses.model.ProcessInfo;

/**
 * Class containing different actions and checks that can be performed on the
 * Servoy Runtime Client.
 *
 * @author niekv
 */
public class VesselSolutionHelper {

    /**
     * Method to check if the Vessel Solution Offline Client is running or not.
     * This method uses the JProcesses library.
     *
     * @return Boolean value: true if the Vessel Solution is running, false if
     * not.
     */
    public boolean isVesselSolutionRunning() {
        JProcesses jProcesses = JProcesses.get().fastMode(true);
        List<ProcessInfo> javawProcesses = jProcesses.listProcesses("java");

        for (ProcessInfo javawProcess : javawProcesses) {
            if (javawProcess.getCommand().contains("servoy_runtime.jar")) {
                return true;
            }
        }

        return false;
    }

    /**
     * Method to check database connectivity of the Vessel Solution Offline
     * Client.
     *
     * @return Boolean value: true if the Vessel Solution is connected to the
     * database, false if not.
     */
    public boolean isVesselSolutionConnectedToDatabase() {

        return false;
    }

    /**
     * Method that uses the pg_ctl.exe file to check if there is currently a
     * database running in the default directory.
     *
     * @return True if it is running, false if not.
     */
    public boolean isPostgresDatabaseRunning() {
        // Construction of the command string.
        final String pg_ctl = "\"C:\\vessel solution\\database\\postgres_db\\bin\\pg_ctl.exe\"";
        final String dir = "\"C:\\vessel solution\\database\\database\"";

        String command = String.format("%s status -D %s", pg_ctl, dir);

        // Create an outputstream, that output of the executing command will be written to.
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
            System.out.println("This should be investigated... " + ex);
            outputStream.toString();
            System.out.println("Breakpoint on this line...");
        }

        // Return true if the outputted line contains "server is running", false otherwise.
        return Utils.regExMatch("(.*server is running.*)", outputStream.toString(), Pattern.DOTALL);
    }

}
