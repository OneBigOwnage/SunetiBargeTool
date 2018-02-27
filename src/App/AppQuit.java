package App;

import Database.Database;
import HelperClasses.VesselSolutionHelper;

/**
 * Class governing the end of the app, ending services and closing connections.
 *
 * @author niekv
 */
public class AppQuit {

    private final VesselSolutionHelper vsHelper;

    public AppQuit() {
        vsHelper = new VesselSolutionHelper();
    }

    /**
     * Retrieves a new Thread object, containing the checks and actions that
     * must be executed when closing this app.
     *
     * @return
     */
    public Thread getShutDownHook() {

        return new Thread(new Runnable() {
            @Override
            public void run() {
                Database db = Database.getInstance();
                if (!vsHelper.isVesselSolutionConnectedToDatabase()) {
                    db.setAutoReconnect(false);
                    db.disconnect();
                    Commands.stopDatabase();
                } else {
                    db.disconnect();
                }
            }
        });
    }

    /**
     * Wrapper for the exit method of this java app.
     */
    public void quit() {
        System.exit(0);
    }
}
