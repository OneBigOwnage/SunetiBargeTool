/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import App.Config;
import HelperClasses.VesselSolutionHelper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.postgresql.Driver;
import sunetibargetool.SunetiBargeTool;

/**
 * A Singleton database class, that is used to query the PostGres database.
 *
 * @author suneti
 */
public class Database {

    private static Database instance;
    private Connection connection;
    private boolean automaticallyReconnect;
    private final VesselSolutionHelper vsHelper;

    private final String dbDriver;
    private final String dbHost;
    private final String dbPort;
    private final String dbName;
    private final String dbUserName;
    private final String dbPassword;

    /**
     * Default constructor for this class. Constructor is private because this
     * is a singleton implementation. If you want to get the single instance,
     * use {@code DatabaseNew.getInstance()}.
     */
    private Database() {
        automaticallyReconnect = Config.getBoolean("auto_reconnect_database");

        this.dbDriver = Config.getString("db_driver");
        this.dbHost = Config.getString("db_host");
        this.dbPort = Config.getString("db_port");
        this.dbName = Config.getString("db_name");
        this.dbUserName = Config.getString("db_username");
        this.dbPassword = Config.getString("db_password");

        this.vsHelper = new VesselSolutionHelper();
    }

    /**
     * A method that prepares a String, with among other things the host and
     * port of the PostGres database of the Vessel Solution Offline Client.
     *
     * @return A String, containing the instructions to connect to the PostGres
     * database.
     */
    private String getConnectionString() {
        return String.format("%s://%s:%s/%s", this.dbDriver, this.dbHost, this.dbPort, this.dbName);
    }

    /**
     * Creates and returns a Properties object, filled with the needed
     * credentials to access the PostGres database of the Vessel Solution
     * Offline Client.
     *
     * @return A Properties object, containing the credentials for the database.
     */
    private Properties getCredentials() {
        Properties properties = new Properties();

        properties.put("user", this.dbUserName);
        properties.put("password", this.dbPassword);

        return properties;
    }

    /**
     * Method to retrieve an instance object of this class. If there is already
     * an instance declared, it will return that instance. Otherwise it will
     * return a newly created instance.
     *
     * @return An instance of the Database class.
     */
    public static Database getInstance() {
        if (null != instance) {
            return instance;
        } else {
            return instance = new Database();
        }
    }

    /**
     * Setter for the auto reconnect functionality.
     *
     * @param autoReconnect True to automatically reestablish the connection,
     * false to not do this automatically.
     */
    public void setAutoReconnect(boolean autoReconnect) {
        this.automaticallyReconnect = autoReconnect;
    }

    /**
     * Method used to connect to the PostGres database. This method will check
     * if it is possible to establish a connection before trying to do so.
     */
    public void connect() {
        if (!canConnect()) {
            return;
        }

        try {
            if (!Driver.isRegistered()) {
                Driver.register();
            }

            connection = DriverManager.getConnection(getConnectionString(), getCredentials());

        } catch (SQLException ex) {
            System.out.println("Error registering the PostGreSQL Driver to the DriverManager:\n" + ex);
        }
    }

    /**
     * Method used to disconnect from the PostGres database. This method will
     * check if it is currently possible to close the connection, before trying
     * to do so.
     */
    public void disconnect() {
        if (canDisconnect()) {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Something went wrong trying to close the connection:\n" + ex);
            }
        } else {
            System.out.println("Can not disconnect...?");
        }
    }

    /**
     * Method to check if the Database class has an active database
 connection. This method is used mainly before trying to execute a query,
     * to make sure that there is an active connection with the database.
     *
     * @return True if, and only if, there is an active connection with the
     * PostGres Database. False otherwise.
     */
    public boolean isConnected() {
        if (!vsHelper.isPostgresDatabaseRunning()) {
            return false;
        }

        try {
            return (null != connection && this.connection.isValid(0));
        } catch (SQLException ex) {
            System.out.println("Something went wrong trying to validate the connection:\n" + ex);
            return false;
        }
    }

    /**
     * Method to fire off queries to the database.
     *
     * @param query The query you want to execute.
     * @return False if it it not currently possible to execute a query, because
     * there is no active connection or an error occurred. ResultSet if it has a
     * result and an integer indicating affected rows if given query is an
     * UPDATE/DELETE/INSERT query.
     */
    public Object executeQuery(Query query) {
        if (!canExecuteQuery()) {
            return false;
        }

        try {
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
            SunetiBargeTool.log("SQLException occurred: " + ex);
            return ex.getMessage();
        }

    }

    /**
     * Method to check if the Database class is allowed to connect to the
 PostGres Database. This method is mainly used before trying to connect,
     * to make sure it is possible and allowed to connect to the PostGres
     * database.
     *
     * @return True if, and only if, it currently possible to connect to the
     * database.
     */
    public boolean canConnect() {
        return vsHelper.isPostgresDatabaseRunning();
    }

    /**
     * Method to make sure it is currently possible to disconnect from the
     * PostGres database. This method is mainly used before disconnecting from
     * the PostGres database, to make sure it is safe to disconnect.
     *
     * @return True if, and only if, it is currently possible to disconnect from
     * the PostGres database.
     */
    public boolean canDisconnect() {
        return connection != null;
    }

    /**
     * Method that checks if the current connection allows for executing
     * queries.
     *
     * @return True if, and only if, it is currently possible to
     */
    public boolean canExecuteQuery() {
        if (!vsHelper.isPostgresDatabaseRunning()) {
            return false;
        }

        if (!isConnected() && !this.automaticallyReconnect) {
            return false;
        }

        if (canConnect() && this.automaticallyReconnect && !isConnected()) {
            connect();
        }

        try {
            return connection.isValid(0);
        } catch (SQLException ex) {
            return false;
        }
    }

}
