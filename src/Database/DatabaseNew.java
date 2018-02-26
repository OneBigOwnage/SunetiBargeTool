/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import App.Config;

/**
 * A Singleton database class, that is used to query the PostGres database.
 *
 * @author suneti
 */
public class DatabaseNew {

    private static DatabaseNew instance;
    private boolean automaticallyReconnect;

    /**
     * Default constructor for this class. Constructor is private because this
     * is a singleton implementation. If you want to get the single instance,
     * use {@code DatabaseNew.getInstance()}.
     */
    private DatabaseNew() {
        automaticallyReconnect = Config.getBoolean("");
    }

    /**
     * Method to retrieve an instance object of this class. If there is already
     * an instance declared, it will return that instance. Otherwise it will
     * return a newly created instance.
     *
     * @return An instance of the DatabaseNew class.
     */
    public static DatabaseNew getInstance() {
        if (null != instance) {
            return instance;
        } else {
            return new DatabaseNew();
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

    }

    /**
     * Method used to disconnect from the PostGres database. This method will
     * check if it is currently possible to close the connection, before trying
     * to do so.
     */
    public void disconnect() {

    }

    /**
     * Method to check if the DatabaseNew class has an active database
     * connection. This method is used mainly before trying to execute a query,
     * to make sure that there is an active connection with the database.
     *
     * @return True if, and only if, there is an active connection with the
     * PostGres Database. False otherwise.
     */
    public boolean isConnected() {
        return false;
    }

    /**
     *
     * @param query
     * @return
     */
    public Object executeQuery(Query query) {
        return false;
    }

    /**
     * Method to check if the DatabaseNew class is allowed to connect to the
     * PostGres Database. This method is mainly used before trying to connect,
     * to make sure it is possible and allowed to connect to the PostGres
     * database.
     *
     * @return True if, and only if, it currently possible to connect to the
     * database.
     */
    public boolean canConnect() {

        return false;
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

        return false;
    }

    /**
     * Method that checks if the current connection allows for executing
     * queries.
     *
     * @return True if, and only if, it is currently possible to
     */
    public boolean canExecuteQuery() {

        return false;
    }

    /**
     * Method to change some of the current configuration of the database.
     */
    public void setDatabaseConfiguration() {

    }

}
