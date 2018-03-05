/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import Daemons.DaemonManager;
import Database.Database;
import HelperClasses.VesselSolutionHelper;
import UI.LogView;
import UI.LoginView;

/**
 *
 * @author niekv
 */
public class AppStart {

    private final VesselSolutionHelper vsHelper;
    private static Controller appController;

    /**
     * Default constructor for this class.
     */
    public AppStart() {
        vsHelper = new VesselSolutionHelper();
    }

    /**
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new AppStart().bootstrap();
    }

    /**
     * Bootstrap method for the Suneti Barge Tool. Among other things, connects
     * to the database.
     */
    public void bootstrap() {
        // Initializes the app configuration.
        Config.load();

        // Load the properties of the Logger class, from the Config class.
        Logger.loadProperties();

        // Adds the shutdown hook to the application.
        Runtime.getRuntime().addShutdownHook(new AppQuit().getShutDownHook());

        // Start the database if it is not already running, and it should be started on AppStart.
        boolean shouldStartAutomatically = Config.getBoolean("appstart_start_db");
        if (!vsHelper.isPostgresDatabaseRunning() && shouldStartAutomatically) {
            Commands.startDatabase();
        }

        // Connect to the database if needed.
        if (Config.getBoolean("appstart_connect_db")) {
            Database.getInstance().connect();
        }

        appController = new Controller();

        Logger.setupLogView((LogView) appController.getView(Config.View.LOG_VIEW));

        // *** IMPORTANT ***
        // In production, uncomment first line and comment/delete second line!
        // Second line is used to skip the Login part of the application.
        appController.showLoginView();
        //appController.afterLogin();
        //Logger.warning("Skipping the login, this is for development only!");
        // *** IMPORTANT ***

        ((LoginView) appController.getView(Config.View.LOGIN_VIEW)).PasswordFieldRequestFocus();

        // Bootstrap all the daemons, via the DaemonManager.
        DaemonManager.defaultLoad();
    }

    public static Controller getController() {
        return appController;
    }

}
