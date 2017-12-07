/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daemons;

import java.util.ArrayList;
import java.util.List;
import sunetibargetool.Config;

/**
 *
 * @author niekv
 */
public class DaemonHost {
    
    private static List<BaseDaemon> daemonList;
    
    
    private DaemonHost() {
        
    }
    
    static {
        daemonList = new ArrayList<>();
        createDaemons();
    }
    
    public static void subscribe(Class daemonClass) {
        for (BaseDaemon daemon : daemonList) {
        }
    }
    
    
    private static void createDaemons() {
        daemonList.add(new ConnectionDaemon(Integer.parseInt(Config.get("connection_daemon_sleep_time"))));
    }
}
