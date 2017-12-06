/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import App.Controller;
import UI.SQLView;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author niekv
 */
public class SQLModel extends BaseModel {

    private final List<String> queryList;
    private final ListIterator<String> iterator;
    
    public SQLModel(Controller controller) {
        super(controller);
        this.controller = controller;
        this.queryList = new ArrayList<>();
        this.iterator = this.queryList.listIterator();
    }

    public void addQuery(String query) {
        // If empty String or null, simply return.
        if (query == null || query.equals("")) {
            return;
            // If the list is empty, or if the query is not the same as the last one on the stack,
            // add it to the list.
        } else if (this.queryList.isEmpty() || !this.queryList.get(this.queryList.size() - 1).equals(query)) {
            this.queryList.add(query);
        }

    }

    public String getNext(String query) {
        printList();
        System.out.println("Next index: " + iterator.nextIndex());
        return "";
    }

    public String getPrevious(String query) {
        printList();
        if (iterator.previousIndex() != -1) {
            iterator.next();
        }
        return "";
    }

    public void printList() {
        System.out.println("Items: ");
        for (String string : this.queryList) {
            System.out.println("- " + string);
        }
        System.out.println("---");
    }
}
