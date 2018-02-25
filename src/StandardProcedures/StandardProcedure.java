package StandardProcedures;

import HelperClasses.ClassEnumerator;
import UI.StandardProcedureView;
import java.util.ArrayList;
import java.util.List;
import App.Config;
import sunetibargetool.SunetiBargeTool;

/**
 *
 * @author suneti
 */
public abstract class StandardProcedure {

    /**
     * The name of the standard procedure, this is also what is displayed in the
     * list view in the GUI.
     */
    private final String name;

    /**
     * The description of the procedure, this is a detailed definition
     * describing: What the procedure does and/or is used for; In which
     * scenarios it should or should not be performed.
     */
    private final String description;

    /**
     * An array of warning Strings, each String representing a single warning
     * message. These messages will be shown on the warning-view before
     * executing the procedure.
     */
    private final String[] warningMessages;

    /**
     * An estimate of the execution time of the standard procedure. This is
     * used, among other things, the loading time of the progress bar in the
     * execute view. This attribute is manually set in the constructor of this
     * object and is therefore just an indication.
     */
    private double averageExecutionTime;

    /**
     * Default constructor for the StandardProcedure class. This constructor
     * should ideally be used then the averageExecutionTime is either unknown or
     * can vary a lot.
     *
     * @param name The name of the standard procedure
     * @param description The full description of the standard procedure
     * @param warningMessages A String Array, containing the warning messages
     * for the standard procedure
     */
    public StandardProcedure(String name, String description, String[] warningMessages) {
        this.name = name;
        this.description = description;
        this.warningMessages = warningMessages;
    }

    /**
     * Constructor method which accommodates for the setting of
     * averageExecutionTime.
     *
     * @param name The name of the procedure
     * @param description The full description of the procedure
     * @param warningMessages A String Array, containing the warning messages
     * for the procedure
     * @param averageExecutionTime An indication of the average time it takes
     * for this procedure to complete, start to end.
     */
    public StandardProcedure(String name, String description, String[] warningMessages, double averageExecutionTime) {
        this.name = name;
        this.description = description;
        this.warningMessages = warningMessages;
        this.averageExecutionTime = averageExecutionTime;
    }

    /**
     * Getter for the name attribute of this object.
     *
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for the description attribute of this object.
     *
     * @return
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Getter for the warningMessages attribute of this object, in String Array.
     * format.
     *
     * @return
     */
    public String[] getWarningMessages() {
        return this.warningMessages;
    }

    /**
     * Getter for the averageExecutionTime attribute of this object. Remember
     * this is only an estimation.
     *
     * @return
     */
    public double getAverageExecutionTime() {
        return this.averageExecutionTime;
    }

    /**
     * This acts as a template for a method that has to be implemented by the
     * extending class.
     * @throws java.lang.Exception
     */
    abstract public void performProcedure() throws Exception;

    /**
     *
     * @return Returns true if, and only if, the problem that is supposed to be
     * fixed by this procedure is actually fixed.
     */
    abstract public boolean isProblemFixed();

    /**
     * This method is used to get an ArrayList of all subclasses of this list.
     * Can be used to instantiate all standard procedures.
     *
     * @return A list of subclasses from this class.
     */
    public static ArrayList<Class> getSubClasses() {

        ArrayList<Class> subClassList = new ArrayList<>();

        // Get a list of all classes in this package via a 3rd party class.
        List<Class<?>> classList = ClassEnumerator.getClassesForPackage(StandardProcedure.class.getPackage());

        for (Class<?> cls : classList) {
            // If the class is a subclass of this class,
            // add it to the list of known subclasses.
            if (StandardProcedure.class.isAssignableFrom(cls) && !cls.equals(StandardProcedure.class)) {
                subClassList.add(cls);
            }
        }

        // Return the list of known subclasses.
        return subClassList;
    }
    
    protected void sendFeedback(String text) {
        StandardProcedureView view = (StandardProcedureView) SunetiBargeTool.getController().getView(Config.View.STANDARD_PROCEDURE_VIEW);
        view.appendExecutionText(text);
    }
}
