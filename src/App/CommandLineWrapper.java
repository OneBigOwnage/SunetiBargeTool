/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteResultHandler;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;
import sunetibargetool.SunetiBargeTool;

/**
 *
 * @author niekv
 */
public class CommandLineWrapper {

    public static final File DEFAULT_WORKING_DIR = new File(".");
    public static final boolean RETURN_OUTPUT = true;

    /**
     * Method to execute a given command on the command line. This variant of
     * the executeCommand method is just a bare wrapper since most parameters
     * for the process must be supplied.
     *
     * @param command A String representing the command that is to be executed.
     * @param workingDir A File representing the directory where the command
     * will be executed.
     * @param stream OutputStream that the process' output will be sent to.
     * @param handler A handler that will be passed in with the command, useful
     * for tracking when the process is done.
     */
    public static void executeCommand(String command, File workingDir, OutputStream stream, ExecuteResultHandler handler) {
        try {
            CommandLine commandLine = new CommandLine(command);
            Executor executor = new DefaultExecutor();

            executor.setWorkingDirectory(workingDir);
            executor.setStreamHandler(new PumpStreamHandler(stream));
            executor.setExitValues(new int[]{0, 1});
            executor.execute(commandLine, handler);
        } catch (IOException ex) {
            System.out.println("Exception was thrown in executeCommand: " + ex);
        }
    }

    /**
     * Method to execute a given command on the command line. In this version of
     * this method it is possible to give in a working directory and a success
     * message.
     *
     * @param command A String representing the command that is to be executed.
     * @param workingDir A File representing the directory where the command
     * will be executed.
     * @param successMessage The message that is sent to the log if, and only if
     * the command was successfully executed.
     */
    public static void executeCommand(String command, File workingDir, final String successMessage) {

        final OutputStream defaultOutputStream = new OutputStream() {
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

        ExecuteResultHandler defaultHandler = new ExecuteResultHandler() {
            @Override
            public void onProcessComplete(int exitValue) {
                if (successMessage != null) {
                    System.out.println(successMessage);
                    }
            }

            @Override
            public void onProcessFailed(ExecuteException ex) {
                SunetiBargeTool.log(String.format("Process failed: %s\n%s", ex, defaultOutputStream.toString()));
            }
        };

        // Pass given arguments + created defaults to the Overloaded function.
        executeCommand(command, workingDir, defaultOutputStream, defaultHandler);
    }

    /**
     * Simplest form of this method, can be called with only a command String.
     * Will use default values and call overloaded function.
     *
     * @param command String representing a command that is to be executed.
     */
    public static void executeCommand(String command) {
        executeCommand(command, DEFAULT_WORKING_DIR, null);
    }

    /**
     * Synchronous form of the executeCommand method that takes, apart from the
     * command String, a boolean. Since this method also has an overloaded
     * function that just takes a String as command, this function's should only
     * be used with parameter true. For improved readability
     *
     * @param command String representing a command that is to be executed.
     * @param returnOutput
     * @return String representing the output of the command that was sent.
     */
    public static String executeCommand(String command, boolean returnOutput) {
        if (returnOutput) {

            // Since we use this variable inside the enclosed resultHandler,
            // it must be final so a normal boolean can not be used. Hence we
            // use an AtomicBoolean.
            final AtomicBoolean isProcessDone = new AtomicBoolean();

            // Create the outputstream that the output of the executed command is funneled into.
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

            // Create a ExecuteResultHandler, this is 
            ExecuteResultHandler executeResultHandler = new ExecuteResultHandler() {
                /**
                 * When the process is done, set boolean to true so the
                 * {@code while(!isProcessDone.get())} loop stops.
                 *
                 * @param exitValue
                 */
                @Override
                public void onProcessComplete(int exitValue) {
                    /*System.out.println("onProcessComplete");
                    System.out.println("- " + outputStream.toString());*/
                    isProcessDone.set(true);
                }

                @Override
                public void onProcessFailed(ExecuteException ex) {
                    SunetiBargeTool.log(String.format("Process failed: %s\n%s", ex, outputStream.toString()));
                    /*System.out.println("onProcessFailed");
                    System.out.println("- " + outputStream.toString());*/
                    isProcessDone.set(true);
                }
            };

            executeCommand(command, DEFAULT_WORKING_DIR, outputStream, executeResultHandler);

            while (!isProcessDone.get()) {
                // Wait until the process is done...
            }

            // When the process is done, return the the output via the outputstream.
            return outputStream.toString();
        } else {
            executeCommand(command, DEFAULT_WORKING_DIR, null);
            System.out.println("Using executeCommand(String command, boolean returnOutput) with parameter false!");
            return null;
        }
    }

    /**
     * Method to open given file with Notepad.
     *
     * @param file File that is to be opened with Notepad, path to file must be
     * absolute since the command will be executed from the root.
     */
    public static void openFileWithNotepad(File file) {
        try {
            String notepadPath = "C:\\Windows\\notepad.exe";
            String command = String.format("\"%s\" \"%s\"", notepadPath, file.getAbsolutePath());
            executeCommand(command, DEFAULT_WORKING_DIR, String.format("Opened file '%s' successfully!", file.toString()));
        } catch (Exception ex) {
            SunetiBargeTool.log("Opening file with notepad failed! " + ex);
        }
    }
}
