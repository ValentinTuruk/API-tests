package framework.utils;

import java.util.ResourceBundle;

public class PropertiesReader {
    public static String getProperty(String fileName, String key) {
        try {
            var properties = ResourceBundle.getBundle(fileName);
            return properties.getString(key);
        } catch (Exception e) {
            System.out.printf("PropertiesReader Error: %s", e.getMessage());
            return null;
        }
    }
    
    public static String getConfigProperty(String key) {
        return getProperty("config", key);
    }
}
