/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import HelperClasses.Utils;
import Database.Database;
import HelperClasses.VesselSolutionHelper;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteResultHandler;
import sunetibargetool.SunetiBargeTool;

/**
 *
 * @author niekv
 */
public class Commands {

    public static File highestVersionFolder;
    public static String bargeName;
    public static String brokerName;
    public static String ownerName;
    public static String managerName;

    static {
        setHighestVersionFolder();
    }

    /**
     * Method to start the PostGres database via a pg_ctl command, executed by
     * the CommandLineWrapper.
     */
    public static void startDatabase() {
        if (new VesselSolutionHelper().isPostgresDatabaseRunning()) {
            SunetiBargeTool.log("Database already running...");
            return;
        }

        String pg_ctl = "\"C:\\vessel solution\\database\\postgres_db\\bin\\pg_ctl.exe\"";
        String dir = "\"C:\\vessel solution\\database\\database\"";
        String options = "\"-p 15000\"";
        String log = "\"C:\\vessel solution\\database\\postgres_db\\postgres_log.txt\"";

        String command = String.format("%s start -D %s -o %s -l %s", pg_ctl, dir, options, log);
        CommandLineWrapper.executeCommand(command);
    }

    /**
     * Method to stop the PostGres database via a pg_ctl command, executed by
     * the CommandLineWrapper.
     */
    public static void stopDatabase() {
        Database.getInstance().disconnect();

        String pg_ctl = "\"C:\\vessel solution\\database\\postgres_db\\bin\\pg_ctl.exe\"";
        String dir = "\"C:\\vessel solution\\database\\database\"";
        String log = "\"C:\\vessel solution\\database\\postgres_db\\postgres_log.txt\"";

        String command = String.format("%s stop -D %s -l %s", pg_ctl, dir, log);
        CommandLineWrapper.executeCommand(command, CommandLineWrapper.DEFAULT_WORKING_DIR, "Database Succesfully Stopped!");
    }

    /**
     * Opens the file "C:\vessel solution\database\database\pg_hba.conf" with
     * notepad.
     */
    public static void open_pg_hba_conf() {
        final String pg_hba_conf_filename = "C:\\vessel solution\\database\\database\\pg_hba.conf";
        CommandLineWrapper.openFileWithNotepad(new File(pg_hba_conf_filename));
    }

    /**
     * Opens the file "C:\vessel solution\Vessel Solution.bat" with notepad.
     */
    public static void open_vessel_solution_bat() {
        final String vessel_solution_bat_filename = "C:\\vessel solution\\Vessel Solution.bat";
        CommandLineWrapper.openFileWithNotepad(new File(vessel_solution_bat_filename));
    }

    /**
     * Opens the servoy-log of the highest version folder, with notepad.
     */
    public static void open_servoy_log() {
        if (highestVersionFolder != null) {
            final File servoy_log_filename = new File(highestVersionFolder.toString() + "\\servoy_log.txt");
            CommandLineWrapper.openFileWithNotepad(servoy_log_filename);
        }
    }

    /**
     * Gets the highest version folder from "C:\vessel solution\", also sets the
     * internal class variable highestVersionFolder to point to this directory.
     *
     * @return A File representing the directory of the highest version folder.
     */
    public static File setHighestVersionFolder() {
        File vsDir = new File("C:\\vessel solution\\");

        Collection<File> allFiles = new TreeSet<>();
        allFiles.addAll(Arrays.asList(vsDir.listFiles()));
        Collection<File> versionFolders = new TreeSet<>();

        for (File file : allFiles) {
            // If file is a directory, and contains a "servoy_runtime.jar", then it is added versionFolders.
            if (file.isDirectory()) {
                List<String> tmpList = Arrays.asList(file.list());
                if (tmpList.contains("servoy_runtime.jar")) {
                    versionFolders.add(file);
                }
            }
        }

        Object[] versionFoldersArray = versionFolders.toArray();

        return highestVersionFolder = new File(versionFoldersArray[versionFoldersArray.length - 1].toString());
    }

    /**
     * Kills all java and javaw.exe processes, excluding this Barge Tool's own
     * java process.
     */
    public static void killVesselSolution() {
        // Retrieve own PID, which will later be used to not kill own process.
        int ownPID = Utils.getOwnProcessPID();

        // Get all processes from the Utils class.
        List<WindowsProcess> javaProcessesList = Utils.getProcesses("javaw.exe", Utils.FILTER_APPLY_COMMAND);
        javaProcessesList.addAll(Utils.getProcesses("java.exe", Utils.FILTER_APPLY_COMMAND));

        // Kill all processes that contain the word "java", exluding this very java process.
        for (WindowsProcess wProcess : javaProcessesList) {
            if (wProcess.getProcessPID() != ownPID) {
                CommandLineWrapper.executeCommand(String.format("\"%s\\system32\\taskkill.exe\" -f -pid \"%s\"", System.getenv("windir"), wProcess.getProcessPID()));
            }
        }
    }

    /**
     * Force stops the PostGres database, rolling back all active transactions.
     * Also deletes the postmaster.pid file, which is left behind because of the
     * -f parameter.
     */
    public static void forceStopDatabase() {
        Database.getInstance().disconnect();
        String pg_ctl = "\"C:\\vessel solution\\database\\postgres_db\\bin\\pg_ctl.exe\"";
        String dir = "\"C:\\vessel solution\\database\\database\"";
        String log = "\"C:\\vessel solution\\database\\postgres_db\\postgres_log.txt\"";

        String command = String.format("%s stop -D %s -m f -l %s", pg_ctl, dir, log);

        OutputStream outputStream = new ByteArrayOutputStream();

        ExecuteResultHandler handler = new ExecuteResultHandler() {
            @Override
            public void onProcessComplete(int i) {
//                deletePostmasterPid();
            }

            @Override
            public void onProcessFailed(ExecuteException ee) {
                SunetiBargeTool.log("Not able to forcefully stop database!");
            }
        };

        CommandLineWrapper.executeCommand(command, CommandLineWrapper.DEFAULT_WORKING_DIR, outputStream, handler);
//        CommandLineWrapper.executeCommand(command, CommandLineWrapper.DEFAULT_WORKING_DIR, "Database Succesfully Stopped!");
    }

    /**
     * Method to delete the postmaster.pid file, since it is left behind when
     * the database was not closed nicely
     */
    public static void deletePostmasterPid() {
        final String fileLocation = "C:\\vessel solution\\database\\database\\postmaster.pid";
        File postmasterPIDFile = new File(fileLocation);
        if (postmasterPIDFile.exists() && !postmasterPIDFile.delete()) {
            SunetiBargeTool.log("Could not delete postmaster.pid file!");
        }
    }
}
