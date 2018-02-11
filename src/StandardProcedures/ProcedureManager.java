/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StandardProcedures;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author suneti
 */
abstract public class ProcedureManager {

    /**
     * The list of Standard Procedures.
     */
    private static List<StandardProcedure> procedures;

    public static void load() {
        // Loading the list of standard procedures.
        procedures = new ArrayList<>();
        for (Class<? extends StandardProcedure> procedureClass : StandardProcedure.getSubClasses()) {
            try {
                StandardProcedure procedure = procedureClass.newInstance();
                procedures.add(procedure);
            } catch (InstantiationException | IllegalAccessException ex) {
                System.out.println("There was a problem trying to load a standard procedure!" + ex);
            }
        }

    }

    public static List<StandardProcedure> getProcedures() {
        return procedures;
    }

}
