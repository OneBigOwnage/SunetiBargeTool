/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import com.sun.jna.platform.win32.Kernel32;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteResultHandler;

/**
 *
 * @author niekv
 */
public abstract class Utils {

    /**
     * Method to quickly RegEx match a pattern on a string, can be given extra
     * flags. Different flags are described here:
     * https://docs.oracle.com/javase/tutorial/essential/regex/pattern.html
     *
     * @param pattern The pattern to match on the text
     * @param text The text that you want to be matched
     * @param flags The different flags that you can give the RegEx matcher.
     * @return {boolean}
     */
    public static boolean regExMatch(String pattern, String text, int... flags) {
        int flagTotal = 0;

        for (int singleFlag : flags) {
            flagTotal += singleFlag;
        }

        Pattern regEx = Pattern.compile(pattern, flagTotal);
        Matcher matcher = regEx.matcher(text);

        return matcher.matches();
    }

    /**
     * Returns a {@code Method} object, given a method name as String, and an
     * object that inhibits this method.
     *
     * @param methodName The string representing the method that should be
     * searched for
     * @param object The object that has the method that is to be searched for
     * @return
     */
    public static Method getMethodByName(String methodName, Object object) {
        Method method = null;

        for (Method m : object.getClass().getDeclaredMethods()) {
            if (m.getName().equals(methodName)) {
                method = m;
                break;
            }
        }

        return method;
    }

    /**
     * Returns the process PID (windows) of the current java process that is
     * this application.
     *
     * @return integer representing the PID, or -1 if an error occurs.
     */
    public static int getOwnProcessPID() {
        try {
            Class.forName(com.sun.jna.win32.StdCallLibrary.class.getName());
            return Kernel32.INSTANCE.GetCurrentProcessId();
        } catch (ClassNotFoundException e) {
            sunetibargetool.SunetiBargeTool.log("Class 'com.sun.jna.win32.StdCallLibrary' not found!");
            return -1;
        }
    }

    /**
     * Gets all running processes (Windows only) as a HashMap. Keys in this
     * HashMap are the PIDs, values are Strings representing the process name.
     * PIDs are the keys in this Map because the PID is unique but the process
     * name isn't.
     *
     * @return A HashMap filled with all the processes retrieved from the
     * system.
     */
    public static Map<Integer, String> getAllJavaProcesses() {
        // Using a HashMap for speed
        Map<Integer, String> returnMap = new HashMap<>();

        // Since we use this variable inside the enclosed resultHandler,
        // it must be final so a normal boolean can not be used. Hence we
        // use an AtomicBoolean.
        final AtomicBoolean isProcessDone = new AtomicBoolean(false);

        // Create the command that is to be send off to the command line,
        // parameter (-nh) removes the header from the list,
        // parameter (-fo "CSV") is to get the list in csv format which
        // makes it easy to work with.
        String command = String.format("\"%s\\system32\\tasklist.exe\" -fi \"ImageName eq javaw.exe\" -nh -v -fo \"CSV\"", System.getenv("windir"));

        //
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

        ExecuteResultHandler resultHandler = new ExecuteResultHandler() {
            // When the process is done, set boolean to true so the
            // while loop stops.
            @Override
            public void onProcessComplete(int exitValue) {
                isProcessDone.set(true);
            }

            @Override
            public void onProcessFailed(ExecuteException ex) {
                isProcessDone.set(true);
                sunetibargetool.SunetiBargeTool.log("Error occurred trying to get process list: " + ex);
            }
        };

        CommandLineWrapper.executeCommand(command, CommandLineWrapper.DEFAULT_WORKING_DIR, outputStream, resultHandler);

        while (!isProcessDone.get()) {
            // Wait until the process is done...
        }

        // When the process is done, retrieve the output via the outputstream.
        String csvProcessList = outputStream.toString();

        String[] processLines = csvProcessList.split("\r\n");

        for (String processLine : processLines) {
            // Split on ',' because csv, the replaceAll statements are to remove
            // leading & trailing double quotes.
            String[] process = processLine.split(",");
            String processName = process[0].replaceAll("\"$|^\"", "");
            int processPID = Integer.parseInt(process[1].replaceAll("\"$|^\"", ""));
            // Place all the processed in the map.
            returnMap.put(processPID, processName);
        }
        return returnMap;
    }

    /**
     * Method to trim given characters from the start & end of a given string.
     *
     * @param string The string that you want to trim
     * @param trimChars The characters that need to be trimmed
     * @return
     */
    public static String trim(String string, String trimChars) {
        return string.replaceAll(String.format("%s$|^%s", trimChars, trimChars), "");
    }
}
