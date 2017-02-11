package com.gaidin17.config;

import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;

/**
 * Created by Gaidin on 11.02.2017.
 */
@Component
public class AppConfig {
    private static final String PROPERTY_FILE = "AppConfig.properties";
    private Properties properties;
    private String path;

    public AppConfig() {
        path = new File("").getAbsolutePath();
        properties = loadConfig();
    }

    private Properties loadConfig() {
        Properties properties = null;
        try (FileReader reader = new FileReader(path + "\\" + PROPERTY_FILE)) {

            if (reader != null) {
                properties = new Properties();
                properties.load(reader);
            } else {
                File file = new File(path + "\\" + PROPERTY_FILE);
                file.createNewFile();
            }

        } catch (Exception e) {
            properties = null;
            System.out.println("Exception: " + e);
        }
        return properties;
    }

    public void saveConfig(String key, String value) {
        try (FileWriter writer = new FileWriter(path + "\\" + PROPERTY_FILE)) {
            if (properties == null) {
                properties = new Properties();
            }
            properties.setProperty(key, value);

            if (writer != null) {
                properties.store(writer, null);
            }
        } catch (Exception e) {
            properties = null;
            System.out.println("Exception: " + e);
        }
    }



    public long getRequestCount() {
        try {
            return Long.parseLong(properties.getProperty(AppConfigConstants.REQUEST_COUNT));
        } catch (Exception ex) {
            return 0;
        }
    }
    public Properties getProperties() {
        return properties;
    }
}

