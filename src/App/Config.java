/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import com.sun.javafx.fxml.PropertyNotFoundException;
import java.awt.Color;
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

    /**
     * All views used in this application, packed inside an enumeration.
     */
    public enum View {
        LOGIN_VIEW,
        BARGE_INFO_VIEW,
        CONFIG_VIEW,
        SQL_VIEW,
        BACKUP_VIEW,
        STANDARD_PROCEDURE_VIEW,
        LOG_VIEW,
        SIDE_BAR;
    }

    /**
     * All models used in this application, packed inside an enumeration.
     */
    public enum Model {
        BASE_MODEL,
        BARGE_INFO_MODEL,
        BACKUP_MODEL,
        SQL_MODEL,
        PROCEDURE_MODEL;
    }

    /**
     * The Properties object of this class. In here all the configuration and
     * properties of the application are stored. This object is loaded from the
     * properties file inside the build directory or inside the JAR if it's
     * compiled. This class provides a methods to retrieve property values from
     * this object.
     */
    private static Properties properties;

    /**
     * Method to load the properties file that is included in the JAR into
     * memory. Will throw (output) errors if the loading fails.
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
                // Will never happen because of the prepended if-statement.
            }
        }
    }

    /**
     * Method to check if the internal properties of this class are loaded from
     * the .properties file.
     *
     * @return True if, and only if, the .properties file is loaded.
     */
    public static boolean isPropertiesLoaded() {
        return (null != properties && !properties.isEmpty());
    }

    /**
     * Method to retrieve a property from the internal properties object. That
     * object is created from the attached 'bargetoolconfig.properties' file.
     *
     * @param key The key that is attached to the value of the property that is
     * to be looked for.
     * @return
     */
    public static Object getProperty(String key) {
        try {
            Object value = properties.getProperty(key);
            if (value != null) {
                return value;
            } else {
                throw new PropertyNotFoundException(String.format("Property '%s' was not found!", key));
            }
        } catch (PropertyNotFoundException e) {

            return null;
        }
    }

    /**
     * Method to retrieve a property from the properties variable.
     *
     * @deprecated
     * @param key The key that will be searched for in the properties.
     * @return
     */
    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static String getString(String key) {
        return (String) getProperty(key);
    }

    public static int getInteger(String key) {
        return Integer.parseInt((String) getProperty(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean((String) getProperty(key));
    }

    /**
     * All the different and default colors used in this application, neatly
     * sorted inside an enumeration. Use the {@code getColor()} method to
     * retrieve a usable Color.
     */
    public enum Colors {
        APPLICATION_DEFAULT_BLUE(new Color(15, 124, 160)),
        APPLICATION_DEFAULT_GREY(new Color(60, 60, 60)),
        LIST_SELECTED_ITEM_BACKGROUND(new Color(98, 126, 135)),
        FONT_COLOR_WHITE(new Color(255, 255, 255)),
        FONT_COLOR_GREY(new Color(60, 60, 60)),
        BTN_NORMAL_BG(new Color(15, 124, 160)),
        BTN_NORMAL_BORDER(new Color(60, 60, 60)),
        BTN_NORMAL_FONT(new Color(60, 60, 60)),
        BTN_HOVER_BORDER(new Color(60, 60, 60)),
        BTN_HOVER_FONT(new Color(255, 255, 255)),
        BTN_DOWN_BG(new Color(8, 75, 96)),
        TITLED_BORDER_BORDER_COLOR(new Color(60, 60, 60)),
        TITLED_BORDER_FONT_COLOR(new Color(60, 60, 60));

        private final Color color;

        private Colors(Color c) {
            this.color = c;
        }

        public Color getColor() {
            return color;
        }
    }

}
