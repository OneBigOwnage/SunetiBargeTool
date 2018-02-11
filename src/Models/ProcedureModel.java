/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import App.Controller;
import StandardProcedures.StandardProcedure;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author niekv
 */
public class ProcedureModel extends BaseModel {
    
    private ArrayList<StandardProcedure> procedureList;
    
    public ProcedureModel(Controller controller) {
        super(controller);
        loadProcedureList();
    }
    
    private void loadProcedureList() {
        List<Class> subClassNames = StandardProcedure.getSubClassNames();
    }
    
}
