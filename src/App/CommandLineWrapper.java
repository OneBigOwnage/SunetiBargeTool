/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteResultHandler;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;

/**
 *
 * @author niekv
 */
public class CommandLineWrapper {

    public static final File DEFAULT_WORKING_DIR = new File(".");

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
                System.out.println(String.format("Process failed: %s\n%s", ex, defaultOutputStream.toString()));
            }
        };

        // Pass given arguments + created defaults to the Overloaded function.
        executeCommand(command, workingDir, defaultOutputStream, defaultHandler);
    }

    /**
     * Method to quickly open given file
     *
     * @param file
     */
    public static void openFileWithNotepad(File file) {
        try {
            String notepadPath = "C:\\Windows\\notepad.exe";
            String command = String.format("\"%s\" \"%s\"", notepadPath, file.getAbsolutePath());
            executeCommand(command, DEFAULT_WORKING_DIR, String.format("Opened file '%s' successfully!", file.toString()));
        } catch (Exception ex) {
            System.out.println("Opening file with notepad failed! " + ex);
        }
    }
}
