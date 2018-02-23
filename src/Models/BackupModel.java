/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import App.Controller;
import Backup.BackupPreset;
import Database.Database;
import Database.Query;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sunetibargetool.SunetiBargeTool;

/**
 *
 * @author suneti
 */
public class BackupModel extends BaseModel {

    private String[] tables;

    /**
     * Default constructor for the BackupModel class.
     *
     * @param controller
     */
    public BackupModel(Controller controller) {
        super(controller);
        this.loadTablesFromDatabase();
    }

    /**
     * Method to retrieve a list of all the available backup presets.
     *
     * @return A list of all the available backup presets.
     */
    public List<BackupPreset> getBackupPresets() {

        return null;
    }

    /**
     * Getter for the tables attribute of this class. This method takes a
     * boolean value as parameter. Passing in `true` will reload the tables from
     * the database, else the tables that are already stored in this object are
     * returned.
     *
     * @param fresh Whether or not to freshly load the tables from the database
     * again.
     * @return A String array containing all the tables in the database. If the
     * tables are not loaded, null will be returned.
     */
    public String[] getTables(boolean fresh) {
        if (fresh) {
            this.loadTablesFromDatabase();
        }
        return this.tables;
    }

    /**
     * Loads all tables from the database, into the tables list. If something
     * goes wrong whilst loading the tables, the tables attribute of this object
     * is set to null;
     */
    public final void loadTablesFromDatabase() {
        Query query = new Query("");
        Object result = Database.getInstance().executeQuery(query);

        if (result instanceof ResultSet) {
            ResultSet resultSet = (ResultSet) result;
            ArrayList<String> tempTables = new ArrayList<>();

            try {
                while (resultSet.next()) {
                    String table = resultSet.getString(1);
                    tempTables.add(table);
                }
            } catch (SQLException ex) {
                SunetiBargeTool.log("Something went wrong trying to load tables from database!\n" + ex);
            } finally {
                int loadedTableCount = tempTables.size();
                if (loadedTableCount >= 1) {
                    tempTables.toArray(this.tables);
                } else {
                    this.tables = null;
                }
            }
        }
    }
}
