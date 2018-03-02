/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelperClasses;

import App.CommandLineWrapper;
import App.WindowsProcess;
import com.sun.jna.platform.win32.Kernel32;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author niekv
 */
public abstract class Utils {

    public static boolean FILTER_APPLY_COMMAND = true;
    public static boolean FILTER_APPLY_LIST_ADD = false;

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
        if (method == null) {
            System.out.println(String.format("Method '%s' does not exist for object '%s'!", methodName, object.getClass().getName()));
        }
        return method;
    }

    /**
     * Returns a {@code Method} object, given a method name as String, and a
     * Class where this method can be found in.
     *
     * @param methodName The string representing the method that should be
     * searched for
     * @param _class The class that contains the method
     * @return
     */
    public static Method getMethodByName(String methodName, Class _class) {
        Method method = null;

        for (Method m : _class.getDeclaredMethods()) {
            if (m.getName().equals(methodName)) {
                method = m;
                break;
            }
        }
        if (method == null) {
            System.out.println(String.format("Method '%s' does not exist for object '%s'!", methodName, _class.getName()));
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
     * Method to trim given characters from the start & end of a given string.
     *
     * @param string The string that you want to trim
     * @param trimChars The characters that need to be trimmed
     * @return The input String, trimmed on both sides. This can be the as the
     * String that is put in, if the {@code trimChars} did not match.
     */
    public static String trim(String string, String trimChars) {
        return string.replaceAll(String.format("%s$|^%s", trimChars, trimChars), "");
    }

    /**
     * Method to get all active processes. Returns a List of WindowsProcess
     * objects. Possible to give in a process name filter.
     *
     *
     * @param filter Filter to only return processed that contain given String.
     * @param filterMatchExactly Place where the filter will be applied. Use
     * Utils.FILTER_APPLY_COMMAND if you want to exactly match the filter
     * (including extensions like .exe) or use Utils.FILTER_APPLY_LIST_ADD if
     * you want to roughly match the filter. Be aware that
     * Utils.FILTER_APPLY_LIST_ADD will be much slower.
     *
     * @return A list of WindowsProcesses
     */
    public static List<WindowsProcess> getProcesses(String filter, boolean filterMatchExactly) {
        // If the filter is empty, set it to null.
        // This way we don't have to do this check later in both if-statements.
        if (filter.equals("")) {
            filter = null;
        }

        List<WindowsProcess> returnList = new ArrayList<>();

        // Create the command that is to be send off to the command line,
        // Parameter (-nh) removes the header from the list,
        // parameter (-fo "CSV") is to get the list in csv format which
        // makes it easy to work with,
        // Parameter (-fi) makes it possible to filter on name
        // Parameter (-v) gives verbose. So we get more information, including the window title.
        String command;
        if (filter != null && filterMatchExactly == FILTER_APPLY_COMMAND) {
            command = String.format("\"%s\\system32\\tasklist.exe\" -fi \"ImageName eq %s\" -nh -v -fo \"CSV\"", System.getenv("windir"), filter);
        } else {
            command = String.format("\"%s\\system32\\tasklist.exe\" -nh -v -fo \"CSV\"", System.getenv("windir"));
        }

        String csvProcessList = CommandLineWrapper.executeCommand(command, CommandLineWrapper.RETURN_OUTPUT);

        // Split the output on newlines.
        String[] processLines = csvProcessList.split("\r\n");

        if (processLines[0].contains("INFO")) {
            return returnList;
        }

        for (String processLine : processLines) {
            // Split on ',' because the csv is seperated by commas.
            String[] processInfo = processLine.split(",");

            // Remove the surrounding double quotes from each string.
            for (int i = 0; i < processInfo.length; i++) {
                processInfo[i] = Utils.trim(processInfo[i], "\"");
            }

            String processName = processInfo[0];
            String windowTitle = processInfo[8];
            int processPID = Integer.parseInt(processInfo[1]);

            // If the filter does not match given processname, go to the next processLine.
            if (filterMatchExactly == FILTER_APPLY_LIST_ADD && filter != null && !processName.contains(filter)) {
                continue;
            }

            WindowsProcess process = new WindowsProcess(processPID, processName, windowTitle);

            returnList.add(process);
        }
        return returnList;
    }

    /**
     * Gets the current date-time, in given format, as a String object.
     *
     * @param format Format that is put over the current time, using the java
     * date-time standard.
     * @return String representation of the current time, in given format.
     */
    public static String getCurrentTimeFormatted(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        return dateFormat.format(calendar.getTime());
    }
}
