package com.google.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
 
public class PropertiesReaderUtility {

    private static Properties properties = new Properties();

    /**
     * constructor which takes file path as input
     * @param fileName
     */
    public PropertiesReaderUtility(String fileName) {
        try {
            properties.load(new FileInputStream(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assert !properties.isEmpty();
    }

    /**
     * gets the value for the said key
     * @param key
     * @return
     */
    public String getProperty(final String key) {
        String property = properties.getProperty(key);
        return property != null ? property.trim() : property;
    }
}
