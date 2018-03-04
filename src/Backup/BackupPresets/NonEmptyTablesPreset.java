/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backup.BackupPresets;

import App.Logger;
import Backup.BackupPreset;
import Database.Database;
import Database.Query;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author niekv
 */
public class NonEmptyTablesPreset extends BackupPreset {

    public NonEmptyTablesPreset() {
        super("Non-empty tables",
                "All tables that are not empty, at time of execution.",
                getNonEmptyTables(),
                PresetType.EXCLUDE_SELECTED);
    }

    private static String[] getNonEmptyTables() {
        Query query = new Query(
                "  SELECT _class.relname as _table "
                + "FROM pg_class _class "
                + "INNER JOIN pg_namespace _namespace "
                + "ON _namespace.oid = _class.relnamespace "
                + "WHERE _class.reltuples = 0 "
                + "AND _class.relkind = 'r' "
                + "AND _namespace.nspname = 'public';");

        Object result = Database.getInstance().executeQuery(query);
        String[] tables = new String[0];

        if (result instanceof ResultSet) {
            ResultSet resultSet = (ResultSet) result;
            ArrayList<String> tempTables = new ArrayList<>();

            try {
                while (resultSet.next()) {
                    String table = resultSet.getString(1);
                    tempTables.add(table);
                }

                int loadedTableCount = tempTables.size();
                tables = new String[loadedTableCount];
                tempTables.toArray(tables);

            } catch (SQLException ex) {
                Logger.error("Something went wrong trying to load tables from database: \n{0}", ex.getMessage());
            }
        }

        return tables;
    }

}
