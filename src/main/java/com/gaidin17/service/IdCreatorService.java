package com.gaidin17.service;

import com.gaidin17.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Evgeny_Akulenko on 2/10/2017.
 */
@Component
public class IdCreatorService {
    private static volatile long clientId;
    private static AppConfig appConfig;

    @Autowired
    public IdCreatorService(AppConfig appConfig) {
        clientId = appConfig.getRequestCount();
    }

    public static long getClientId() {
        long id = clientId;
        clientId++;
        return id;
    }

    public static AppConfig getAppConfig() {
        return appConfig;
    }

    public static void setAppConfig(AppConfig appConfig) {
        IdCreatorService.appConfig = appConfig;
    }
}
