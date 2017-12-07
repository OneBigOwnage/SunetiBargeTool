/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author niekv
 */
public abstract class Utils {
    
    /**
     * 
     * @param pattern 
     * @param text
     * @return {boolean}
     */
    public static boolean regExMatch(String pattern, String text, int... flags) {
        int flagTotal = 0;

        for (int singleFlag : flags) {
            flagTotal += singleFlag;
        }
        
        Pattern regEx = Pattern.compile(pattern, flagTotal);
        Matcher matcher = regEx.matcher(text);

        return matcher.matches();
    }
    
    public static Method getMethodByName(String methodName, Object object) {
        Method method = null;
        
        for (Method m : object.getClass().getDeclaredMethods()) {
            if (m.getName().equals(methodName)) {
                method = m;
                break;
            }
        }
        
        return method;
    }
    
}
