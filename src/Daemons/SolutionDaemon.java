/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daemons;

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
        
        
        
        
        
        
        
        
        
        return solutionState.NOT_RUNNING;
    }
    
    
    
    @Override
    public void dispatchSubscriptions() {
        
    }
    
}
