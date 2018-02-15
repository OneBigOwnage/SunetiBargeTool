/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daemons;

import HelperClasses.Utils;
import App.WindowsProcess;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author suneti
 */
public class SolutionDaemon extends BaseDaemon {

    public enum solutionState { NOT_RUNNING, STARTING, RUNNING}
    
    public SolutionDaemon(int sleepTime) {
        super(sleepTime);
    }

    public solutionState getSolutionState() {
        List<WindowsProcess> processList = new ArrayList<>();
        processList.addAll(Utils.getProcesses("javaw.exe", Utils.FILTER_APPLY_COMMAND));
        processList.addAll(Utils.getProcesses("java.exe", Utils.FILTER_APPLY_COMMAND));
        
                
        
        
        
        
        
        
        
        
        
        return solutionState.NOT_RUNNING;
    }
    
    
    
    @Override
    public void dispatchSubscriptions() {
        
    }
    
}
