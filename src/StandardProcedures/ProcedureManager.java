/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StandardProcedures;

import UI.StandardProcedureView;
import java.util.ArrayList;
import java.util.List;
import App.Config;
import sunetibargetool.SunetiBargeTool;

/**
 *
 * @author niekv
 */
abstract public class ProcedureManager {

    /**
     * The list of Standard Procedures, list is filled when executing the load()
     * function in this class. List is filled dynamically by looking for all
     * declared classes that extend from the StandardProcedure class.
     */
    private static List<StandardProcedure> procedures;

    /**
     * This makes sure the load function is called whenever this class is first
     * used.
     */
    static {
        load();
    }

    /**
     * Loads all standard procedures dynamically.
     */
    private static void load() {
        // Loading the list of standard procedures.
        procedures = new ArrayList<>();
        for (Class<? extends StandardProcedure> procedureClass : StandardProcedure.getSubClasses()) {
            try {
                StandardProcedure procedure = procedureClass.newInstance();
                procedures.add(procedure);
            } catch (InstantiationException | IllegalAccessException ex) {
                SunetiBargeTool.log("There was a problem trying to load a standard procedure!\n" + ex);
            }
        }

    }

    /**
     * Getter for the procedures list of this class.
     *
     * @return The list of all declared classes that extend from the
     * StandardProcedure class.
     */
    public static List<StandardProcedure> getDeclaredProcedures() {
        return procedures;
    }

    /**
     * Overload of the method execute in this class, filling in the runThreaded
     * parameter from the config file.
     *
     * @param procedure The StandardProcedure to execute.
     */
    public static void execute(StandardProcedure procedure) {
        execute(procedure, Config.getBoolean("run_standard_procedure_threaded"));
    }

    /**
     * Method to execute given StandardProcedure, with possibility to run in a
     * separate thread for minimal impact on the UI. This method also takes care
     * of disabling the UI whilst the procedure is being executed, and handling
     * the state of a possible progress bar on the UI.
     *
     * @param procedure
     * @param runThreaded Pass in true to execute procedure in a separate
     * thread.
     */
    public static void execute(final StandardProcedure procedure, boolean runThreaded) {
        final StandardProcedureView procedureView = (StandardProcedureView) SunetiBargeTool.getController().getView(Config.View.STANDARD_PROCEDURE_VIEW);
        procedureView.notifyUIOnProcedureStart(procedure);

        if (runThreaded) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean success = false;
                    try {
                        procedure.performProcedure();
                        success = true;
                    } catch (Exception ex) {
                        SunetiBargeTool.log("Exception was thrown whilst performing a standard procedure:\n" + ex);
                    } finally {
                        procedureView.notifyUIOnProcedureEnd(success);
                    }
                }
            }).start();
        } else {
            boolean success = false;
            try {
                procedure.performProcedure();
                success = true;
            } catch (Exception ex) {
                SunetiBargeTool.log("Exception was thrown whilst performing a standard procedure:\n" + ex);
            } finally {
                procedureView.notifyUIOnProcedureEnd(success);
            }
        }
    }
}
