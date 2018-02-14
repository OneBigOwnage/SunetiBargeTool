/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelperClasses;

import StandardProcedures.StandardProcedure;
import java.util.LinkedList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author niekv
 */
public class ProcedureListModel extends AbstractListModel<StandardProcedure> {

    private final List<StandardProcedure> list;

    public ProcedureListModel() {
        list = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return list.size();
    }

    /**
     * This returns a StandardProcedure object, at given index.
     *
     * @param index
     * @return
     */
    @Override
    public StandardProcedure getElementAt(int index) {
        return list.get(index);
    }

    /**
     * Method used to add a Standard Procedure to the list.
     *
     * @param procedure
     */
    public void add(StandardProcedure procedure) {
        list.add(procedure);
    }
}
