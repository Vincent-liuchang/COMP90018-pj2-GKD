package com.example.mobile_pj2.Data;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

public class Configuration {
    private static Logger log = Logger.getLogger(Configuration.class.getName());

    private static final String CONFIGURATION_FILE = "Buildings.properties";

    private static final Properties properties = new Properties();

    // use static initializer to read the configuration file when the class is loaded
    public static void setConfiguration (Context context) {
        try (InputStream inputStream = context.getAssets().open("Buildings.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            log.warning("Could not read file " + CONFIGURATION_FILE);
        }
    }

    public static Map<String, String> getConfiguration() {

        Map temp = properties;
        Map<String, String> map = new HashMap<String, String>(temp);
        return Collections.unmodifiableMap(map);
    }


    public static String getConfigurationValue(String key) {
        return properties.getProperty(key);
    }

    private Configuration() {
    }

}
