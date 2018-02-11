package StandardProcedures;

import App.ClassEnumerator;
import java.util.ArrayList;
import java.util.List;

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
     *
     */
    private final String[] warningMessages;

    /**
     * Default constructor for the StandardProcedure class.
     *
     * @param name
     * @param description
     * @param warningMessages
     */
    public StandardProcedure(String name, String description, String[] warningMessages) {
        this.name = name;
        this.description = description;
        this.warningMessages = warningMessages;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String[] getWarningMessages() {
        return this.warningMessages;
    }

    /**
     *
     */
    abstract public void performProcedure();

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
    public static ArrayList<Class> getSubClassNames() {

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

}
