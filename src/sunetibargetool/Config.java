/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sunetibargetool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;

/**
 *
 * @author niekv
 */
public class Config {

    private static Properties properties;

    /**
     * Method to load the properties file that is included in the JAR into memory.
     * Will throw (output) errors if the loading fails.
     * 
     * Note: The code is split to work in both a batteries-included-jar and IDE.
     */
    public static void load() {
        properties = new Properties();
        File propFile = null;

        
        // Path to the config resource.
        String resource = "/bargeToolConfig.properties";
        URL res = Config.class.getResource(resource);
        
        // This part is for the batteries-included-jar.
        if (res.toString().startsWith("jar:")) {
            try {
                // Read .properties file as inputstream.
                InputStream input = Config.class.getResourceAsStream(resource);
                
                // Create a temporary file outside of the JAR.
                propFile = File.createTempFile("properties", ".tmp");
                
                OutputStream out = new FileOutputStream(propFile);
                
                int read;
                byte[] bytes = new byte[1024];
                
                // Write everything from the config inside the JAR to the temp file.
                while ((read = input.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                
                propFile.deleteOnExit();
            } catch (IOException ex) {
                System.out.println("IOException trying to open properties file! Check if temp-directory has read/write access");
            }
        } else {
            // This part is for the IDE.
            propFile = new File(res.getFile());
        }

        if (propFile != null && !propFile.exists()) {
            throw new RuntimeException("Properties file not found!");
        } else {
            try {
                // Actually load the properties from the file.
                properties.load(new FileInputStream(propFile));
            } catch (IOException ex) {
                // Will never happen because of the prepended if-statement...
            }
        }
    }

    /**
     * Method to retrieve a property from the properties variable.
     * 
     * @param key The key that will be searched for in the properties.
     * @return 
     */
    public static String get(String key) {
        return properties.getProperty(key);
    }
}
