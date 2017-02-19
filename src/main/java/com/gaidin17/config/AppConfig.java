package com.gaidin17.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.Properties;


/**
 * Created by Gaidin on 11.02.2017.
 */
@Component
public class AppConfig {
    private static Logger logger = LoggerFactory.getLogger(AppConfig.class);
    private static String requestCount;

    private Properties properties;
    private String homeDir;

    public Properties getProperties() {
        return properties;
    }

    public AppConfig() {
        homeDir = new File("").getAbsolutePath();
        properties = loadConfig();
    }

    @PostConstruct
    private void initializeProperties() {
        if (properties == null || properties.isEmpty()) {
            properties = new Properties();
            properties.setProperty(AppConfigConstants.REQUEST_COUNT, "0");
            properties.setProperty(AppConfigConstants.FOLDER_FOR_PHOTOS, homeDir);
            saveConfig();
        } else {
            requestCount = properties.getProperty(AppConfigConstants.REQUEST_COUNT);

        }

    }

    private Properties loadConfig() {
        Properties properties = null;
        try (FileReader reader = new FileReader(homeDir + "/" + AppConfigConstants.PROPERTY_FILE)) {

            if (reader != null) {
                properties = new Properties();
                try {
                    properties.load(reader);
                } catch (IOException e) {
                    logger.error("Возникла проблема при загрузке файла {0}", AppConfigConstants.PROPERTY_FILE);
                }
            }

        } catch (FileNotFoundException e) {
            File file = new File(homeDir + "/" + AppConfigConstants.PROPERTY_FILE);

            try {
                file.createNewFile();

            } catch (IOException e1) {
                logger.error("Возникла проблема при создании файла {0}", AppConfigConstants.PROPERTY_FILE);
            }
        } catch (Exception e) {
            logger.error("Возникла проблема при инициализации {0}", AppConfigConstants.PROPERTY_FILE);
        }
        return properties;
    }

    public void saveConfig() {
        try (FileWriter writer = new FileWriter(homeDir + "/" + AppConfigConstants.PROPERTY_FILE)) {
            if (properties == null) {
                properties = new Properties();
            }
            if (writer != null) {
                properties.store(writer, null);
            }
        } catch (Exception e) {
            logger.error("Возникла проблема при сохранении {0}", AppConfigConstants.PROPERTY_FILE);
        }
    }


    public long getRequestCount() {
        return Long.parseLong(properties.getProperty(AppConfigConstants.REQUEST_COUNT));
    }

    public String getFolderForPhotos() {
        return properties.getProperty(AppConfigConstants.FOLDER_FOR_PHOTOS);
    }
}

