/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import App.Controller;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author niekv
 */
public class SQLModel extends BaseModel {

    public enum iteratorDirection {FORWARD, BACKWARD};
    
    private final List<String> queryList;
    private ListIterator<String> iterator;
    private iteratorDirection lastOperation;
    
    public SQLModel(Controller controller) {
        super(controller);
        this.queryList = new ArrayList<>();
        this.updateIterator();
    }

    public void addQuery(String query) {
        // If empty String or null, simply return.
        if (query == null || query.equals("")) {
            return;
            // If the list is empty, or if the query is not the same as the last one on the stack,
            // add it to the list.
        } else if (this.queryList.isEmpty() || !this.queryList.get(this.queryList.size() - 1).equals(query)) {
            this.queryList.add(query);
            this.updateIterator();
        }

    }

    public String getNext() {
        String rString = null;
        if (this.iterator.hasNext()) {
            if (this.lastOperation.equals(iteratorDirection.BACKWARD)) {
                this.iterator.next();
            }
            if (this.iterator.hasNext()) {
                rString = this.iterator.next();
            }
        }
        this.lastOperation = iteratorDirection.FORWARD;
        return rString;
    }

    public String getPrevious() {
        String rString = null;
        if (this.iterator.hasPrevious()) {
            if (this.lastOperation.equals(iteratorDirection.FORWARD)) {
                this.iterator.previous();
            }
            if (this.iterator.hasPrevious()) {
                rString = this.iterator.previous();
            }
        }
        this.lastOperation = iteratorDirection.BACKWARD;
        return rString;
    }

    public void printList() {
        System.out.println("Items: ");
        for (String string : this.queryList) {
            System.out.println("- " + string);
        }
        System.out.println("---");
    }
    
    public final void updateIterator() {
        this.iterator = this.queryList.listIterator();
        while (this.iterator.nextIndex() != this.queryList.size()) {
            this.iterator.next();
        }
        this.lastOperation = iteratorDirection.FORWARD;
    }
}
