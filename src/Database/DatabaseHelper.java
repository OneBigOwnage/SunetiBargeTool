/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author niekv
 */
public abstract class DatabaseHelper {

    private final static Database DATABASE;

    static {
        DATABASE = Database.getInstance();
    }
    
    public static Object getSingleResultFromQuery(Query query) {
        // Get the result from executing a query.
        Object result = DATABASE.executeQuery(query);

        // If the result of executing the query is a ResultSet, parse the 
        // resultset into a new variable so it is easier to work with later on.
        if (result instanceof ResultSet) {
            ResultSet resultSet = (ResultSet) result;
            try {
                // Check if the resultset has a next(), indicating there is
                // at least on value. If it does, return this value.
                if (resultSet.next()) {
                    return resultSet.getObject(1);
                } else {
                    // Return null if there is no next() (first) record.
                    return null;
                }
            } catch (SQLException ex) {
                // Return null in case of an exception.
                return null;
            }
        } else {
            // Return null if the result is not a ResultSet.
            return null;
        }
    }

}
