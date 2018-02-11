package StandardProcedures;

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

}
