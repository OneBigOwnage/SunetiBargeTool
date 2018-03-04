/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daemons;

import java.lang.reflect.Method;

/**
 *
 * @author niekv
 */
public class DeamonSubscription {

    private final Object object;
    private final Method method;

    /**
     * Default constructor for this class.
     *
     * @param object
     * @param method
     */
    public DeamonSubscription(Object object, Method method) {
        this.object = object;
        this.method = method;
    }

    public Object getObject() {
        return this.object;
    }

    public Method getMethod() {
        return this.method;
    }
}
