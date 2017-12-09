/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

/**
 *
 * @author niekv
 */
public class WindowsProcess {

    protected final int PID;
    protected String processName;
    protected String windowTitle;

    /**
     * Simplest constructor for WindowsProcess, with just a name and PID.
     *
     * @param name The name of the process
     * @param pid The PID of the process
     */
    public WindowsProcess(int pid, String name) {
        this.PID = pid;
        this.processName = name;
    }

    /**
     * Full constructor for the WindowsProcess, including a Window Title and
     *
     * @param pid
     * @param name
     * @param windowTitle
     */
    public WindowsProcess(int pid, String name, String windowTitle) {
        this.PID = pid;
        this.processName = name;
        this.windowTitle = windowTitle;
    }

    /**
     * Getter for the process name, as seen in the task manager and tasklist.exe
     *
     * @return String representing the process name.
     */
    public String getProcessName() {
        return this.processName;
    }

    /**
     * Getter for the process PID, as seen in the task manager and tasklist.exe
     *
     * @return Integer representing the process PID.
     */
    public int getProcessPID() {
        return this.PID;
    }

    /**
     * Getter for window title of the WindowsProcess object. Returns null if the
     * process has no window associated with it.
     *
     * @return String representing the window title if there is one, or null if
     * the process has no window associated with it.
     */
    public String getWindowTitle() {
        return this.windowTitle;
    }

}
