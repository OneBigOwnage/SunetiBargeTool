/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author suneti
 */
public class ThreadManager {

    public static void runInSeperateThread(final Method method, final Object object, final Object... params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    method.invoke(object, params);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    Logger.getLogger(ThreadManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}
