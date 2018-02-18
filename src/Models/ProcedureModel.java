/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import App.Controller;
import HelperClasses.ProcedureListModel;
import StandardProcedures.ProcedureManager;
import StandardProcedures.StandardProcedure;
import java.util.List;
import javax.swing.ListModel;

/**
 *
 * @author niekv
 */
public class ProcedureModel extends BaseModel {

    private final List<StandardProcedure> procedureList;

    public ProcedureModel(Controller controller) {
        super(controller);
        procedureList = ProcedureManager.getDeclaredProcedures();
    }

    /**
     * Retrieves a ListModel of class ProcedureListModel, filled with all
     * declared standard procedures.
     *
     * @return
     */
    public ListModel getProcedureListModel() {
        ProcedureListModel listModel = new ProcedureListModel();
        for (StandardProcedure procedure : procedureList) {
            listModel.add(procedure);
        }
        return listModel;
    }

    /**
     * Get a filtered ListModel, filled with only the procedures that have given
     * filter in their name.
     *
     * @param filter String that will be filtered on when searching for
     * procedures.
     * @return
     */
    public ListModel getFilteredProcedureListModel(String filter) {
        ProcedureListModel listModel = new ProcedureListModel();
        for (StandardProcedure procedure : procedureList) {
            if (procedure.getName().toUpperCase().contains(filter.toUpperCase())) {
                listModel.add(procedure);
            }
        }
        return listModel;
    }

}
