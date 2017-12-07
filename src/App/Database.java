/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import Daemons.DatabaseDaemon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import sunetibargetool.Config;

/**
 *
 * @author Productie 831
 */
public class Database {
    private static Database instance;
    private Connection connection;
    
    /**
     * Constructor of Database class, will also start the connection to the PostgreSQL database.
     */
    private Database() {
        this.connect();
    }
    
    
    /**
     * Method to get the instance of the database class.
     * This is because Database is a singleton.
     * 
     * @return 
     */
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
    
    
    /**
     * Starts the connection to the PostgreSQL database.
     * The configuration used to connect is retrieved from the Config class.
     * 
     * @return boolean Returns true if, and only if, a connection has successfully been created.
     */
    public boolean connect() {
        if (!DatabaseDaemon.isDatabaseRunning()) {
            return false;
        }
        boolean successful = false;
        try {
            // Load the postgresql class.
            Class.forName("org.postgresql.Driver");
            
            // Get/Set the configurations for the connection.
            String driver   = Config.get("db_driver");
            String host     = Config.get("db_host");
            String port     = Config.get("db_port");
            String name     = Config.get("db_name");
            
            String uName    = Config.get("db_username");
            String pass     = Config.get("db_pass");
            
            
            String connectionString = String.format("%s://%s:%s/%s", driver, host, port, name);
            
            // Start the connection
            connection = DriverManager.getConnection(connectionString, uName, pass);
            
            successful = true;
            System.out.println("Connected succesfully");
        } catch (ClassNotFoundException | SQLException ex ) {
            System.out.println("Error trying to connect to the database: " + ex);
        }
        return successful;
    }
    
    
    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error trying to disconnect from database: " + ex);
        }
    }
    
    public boolean hasConnection() {
        if (!DatabaseDaemon.isDatabaseRunning()) {
            return false;
        }

        try {
            boolean autoReconnect = Boolean.parseBoolean(Config.get("auto_reconnect_database"));
            if ((connection == null || !connection.isValid(0)) && autoReconnect) {
                connect();
            }
            return connection.isValid(0);
        } catch (SQLException ex) {
            return false;
        }
    }
    
    
    /**
     * Method to fire off queries to the database.
     * 
     * @param query The query you want to execute.
     * @return  False if no connection or error occurred,
     *          ResultSet if it has a result
     *          and an integer indicating affected rows if it is an UPDATE/DELETE/INSERT.
     */
    public Object executeQuery(Query query) {
        try {
            if (!hasConnection()) {
                System.out.println("Database is not running...");
                return false;
            }
            
            // Create new statement.
            Statement statement = connection.createStatement();
            
            // Execute query and fetch result.
            boolean type = statement.execute(query.getQueryString());
            
            
            // If type == true it was a SELECT, so we return the result as ResultSet.
            if (type) {
                ResultSet result = statement.getResultSet();
                return result;
                
            // If type == false it was an UPDATE/DELETE/INSERT, so we return the number of affected rows.
            } else {
                int affectedRows = statement.getUpdateCount();
                return affectedRows;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException occurred: " + ex);
            return ex.getMessage();
        }
    }
}
