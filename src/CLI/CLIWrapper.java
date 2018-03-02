/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLI;

import App.Logger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteResultHandler;

/**
 *
 * @author niekv
 */
public class CLIWrapper {

    private final String command;
    private final List<String> arguments;
    private final Map<String, Object> argumentValues;

    public CLIWrapper(String command) {
        this.command = command;
        this.arguments = new ArrayList<>();
        this.argumentValues = new HashMap<>();
    }

    /**
     * Method to add an argument to the command that is to be executed. All
     * quoting and whitespace is taken care of automatically.
     *
     * @param argument The argument that is to be passed in.
     */
    public void addArgument(String argument) {
        this.arguments.add(argument);
    }

    /**
     * Method to add a list of arguments to the command that is to be executed.
     * All quoting and whitespace is taken care of automatically.
     *
     * @param arguments The argument that is to be passed in.
     */
    public void addAllArguments(List<String> arguments) {
        this.arguments.addAll(arguments);
    }

    /**
     * Method to add an argument and it's value to the command, in a key-value
     * manner. All quoting and whitespace is taken care of automatically.
     *
     * @param key The key of an command line argument, this is the key
     * corresponding to the argument added by {@code addArgument()} or
     * {@code addAllArguments}.
     * @param value The value that is to be assigned to the key.
     */
    public void addArgumentValue(String key, Object value) {
        this.argumentValues.put(key, value);
    }

    /**
     * Adds argument values to the command line object. All quoting and
     * whitespace is taken care of automatically.
     *
     * @param values A map containing all key-value pairs you want to add to
     * this object.
     */
    public void addAllArgumentValues(Map<String, Object> values) {
        this.argumentValues.putAll(values);
    }

    /**
     * Method to synchronously execute the command of this object on the command
     * line, using the Apache Commons Exec library. Possible errors whilst
     * executing the command will be logged using the Logger.
     */
    public void executeCommand() {
        CommandLine cmd = new CommandLine(this.command);
        DefaultExecutor exe = new DefaultExecutor();
        DefaultExecuteResultHandler handler = new DefaultExecuteResultHandler();

        for (String arg : this.arguments) {
            cmd.addArgument(arg);
        }

        cmd.setSubstitutionMap(this.argumentValues);

        try {
            exe.execute(cmd, handler);
            handler.waitFor();
        } catch (IOException | InterruptedException ex) {
            Logger.error("Error executing command \"\"!\n" + ex, this.command);
        }
    }

    /**
     * Method to asynchronously execute the command of this object on the
     * command line, using the Apache Commons Exec library. Possible errors
     * whilst executing the command will be logged using the Logger.
     */
    public void executeCommandAsynchronous() {
        CommandLine cmd = new CommandLine(this.command);
        DefaultExecutor exe = new DefaultExecutor();
        ExecuteResultHandler handler = new DefaultExecuteResultHandler();

        for (String arg : this.arguments) {
            cmd.addArgument(arg);
        }

        cmd.setSubstitutionMap(this.argumentValues);

        try {
            exe.execute(cmd, handler);
        } catch (IOException ex) {
            Logger.error("Error executing command \"\"!\n" + ex, this.command);
        }

    }

}
