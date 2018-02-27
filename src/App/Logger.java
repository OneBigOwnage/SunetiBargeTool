/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import UI.LogView;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author niekv
 */
public class Logger {

    private static LogView logView;
    private static boolean logWithMsgBox = true;
    private static Level logThresHold = Level.NOTICE;
    private static Level msgBoxThresHold = Level.CRITICAL;

    /**
     * Initialize various class attributes whenever this class is accessed for
     * the first time.
     */
    static {
        loadProperties();
    }

    /**
     * This method is used to dispatch a log message string, to the best
     * possible method of logging at runtime. This can be either: the LogView or
     * {@code System.out.println}. This method also takes care of sending off
     * log messages to the {@code createLogMsgBox()}.
     *
     * @param level The logging level.
     * @param str The string that is to be dispatched, this has to be pre
     * formatted.
     */
    private static void dispatchToBestLoggingMethod(Level level, String str) {
        if (!level.isHigherOrEqual(logThresHold)) {
            return;
        }

        // 01. Log via the logview.
        if (null != logView) {
            logView.appendToLog(str);
            // 02. Log via System.out.println().
        } else {
            System.out.print(String.format("- %s - : %s", level, str));
        }

        // 03. Append to the log file of this application. (to be implemented)
        // ---
        // 04. Also create a message box, when logWithMsgBox is enabled, and the threshold is reached.
        if (logWithMsgBox && level.isHigherOrEqual(msgBoxThresHold)) {
            createLogMsgBox(level, str);
        }
    }

    /**
     * Method to create a message box on the screen, showing the given text. The
     * message box will be in the theme of the level that is passed in.
     *
     * @param level The level of the message
     * @param str The message that is to be shown on screen.
     */
    private static void createLogMsgBox(Level level, String str) {

        String title;
        int msgLevel;

        switch (level) {
            case CRITICAL:
                title = "Critical error occurred!";
                msgLevel = JOptionPane.ERROR_MESSAGE;
                break;
            case ERROR:
                title = "Error occurred!";
                msgLevel = JOptionPane.ERROR_MESSAGE;
                break;
            case WARNING:
                title = "Warning!";
                msgLevel = JOptionPane.WARNING_MESSAGE;
                break;
            case INFO:
                title = "Info";
                msgLevel = JOptionPane.INFORMATION_MESSAGE;
                break;
            case NOTICE:
                title = "Notice";
                msgLevel = JOptionPane.INFORMATION_MESSAGE;
                break;
            default:
                title = "Something happened!?";
                msgLevel = JOptionPane.ERROR;
                break;
        }

        JOptionPane.showMessageDialog(null, str, title, msgLevel);
    }

    /**
     * Method to load all Logger properties, from the Config class.
     */
    public static void loadProperties() {
        Controller c = AppStart.getController();

        if (null != c && null != c.getView(Config.View.LOG_VIEW)) {
            logView = (LogView) AppStart.getController().getView(Config.View.LOG_VIEW);
        }

        if (Config.isPropertiesLoaded()) {
            logWithMsgBox = Config.getBoolean("log_with_msgbox");
            logThresHold = Level.fromString(Config.getString("log_threshold"));
            msgBoxThresHold = Level.fromString(Config.getString("msg_box_threshold"));
        }
    }

    public enum Level {
        CRITICAL(500),
        ERROR(400),
        WARNING(300),
        INFO(200),
        NOTICE(100);

        private static final Map<String, Level> enumMap = new HashMap<>();

        static {
            for (Level level : values()) {
                enumMap.put(level.name(), level);
            }
        }

        private final int number;

        Level(int levelNumber) {
            this.number = levelNumber;
        }

        public boolean isHigherOrEqual(Level l) {
            return (this.number >= l.number);
        }

        public static Level fromString(String levelString) {
            return enumMap.get(levelString.toUpperCase());
        }
    }

    /**
     * Logs String message, using the best possible log mechanic that is
     * available at runtime. Will automatically append a newline character to
     * the end, if this is not already the case.
     *
     * @param level The level of the given log message, use the constants of the
     * Logger class.
     * @param str String to log
     * @param strArgs The arguments of the message, these should be formatted as
     * {x}, where x is an integer value starting at 0;
     */
    public static void log(Level level, String str, String... strArgs) {

        // Replacing all strings with given parameters, at the indicated places.
        if (strArgs.length > 0) {
            for (int i = 0; i < strArgs.length; i++) {
                str = str.replace("{" + i + "}", strArgs[i]);
            }
        }

        // If the string does not already end in a newline character, append one.
        String endOfLine = "";

        if (!str.endsWith("\n") && !str.endsWith("\r\n")) {
            endOfLine = System.lineSeparator();
        }

        dispatchToBestLoggingMethod(level, str.concat(endOfLine));
    }

    /**
     * Shorthand method for logging something with a logging level of
     * 'CRITICAL'.
     *
     * @param str String to log.
     * @param strArgs The arguments of the message, these should be formatted as
     * {x}, where x is an integer value starting at 0;
     */
    public static void critical(String str, String... strArgs) {
        log(Level.CRITICAL, str, strArgs);
    }

    /**
     * Shorthand method for logging something with a logging level of 'ERROR'.
     *
     * @param str String to log.
     * @param strArgs The arguments of the message, these should be formatted as
     * {x}, where x is an integer value starting at 0;
     */
    public static void error(String str, String... strArgs) {
        log(Level.ERROR, str, strArgs);
    }

    /**
     * Shorthand method for logging something with a logging level of 'WARNING'.
     *
     * @param str String to log.
     * @param strArgs The arguments of the message, these should be formatted as
     * {x}, where x is an integer value starting at 0;
     */
    public static void warning(String str, String... strArgs) {
        log(Level.WARNING, str, strArgs);
    }

    /**
     * Shorthand method for logging something with a logging level of 'INFO'.
     *
     * @param str String to log.
     * @param strArgs The arguments of the message, these should be formatted as
     * {x}, where x is an integer value starting at 0;
     */
    public static void info(String str, String... strArgs) {
        log(Level.INFO, str, strArgs);
    }

    /**
     * Shorthand method for logging something with a logging level of 'NOTICE'.
     *
     * @param str String to log.
     * @param strArgs The arguments of the message, these should be formatted as
     * {x}, where x is an integer value starting at 0;
     */
    public static void notice(String str, String... strArgs) {
        log(Level.NOTICE, str, strArgs);
    }

    /**
     * Method to pass in the logView object, which is one of the main methods of
     * logging in this application.
     *
     * @param lView The LogView object that accepts logging.
     */
    public static void setupLogView(LogView lView) {
        logView = lView;
    }

    /**
     * Method to set the class attribute {@code logWithMsgBox}. If this is
     * enabled, the application will create message boxes with the error message
     * as content.
     *
     * @param msgBox True if the app should log with message boxes, false if
     * not.
     */
    public static void setLogWithMsgBox(boolean msgBox) {
        logWithMsgBox = msgBox;
    }

}
