package com.gaidin17.service;

import com.gaidin17.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Evgeny_Akulenko on 2/10/2017.
 */
@Component
public class IdCreatorService {
    private AppConfig appConfig;
    private static volatile AtomicLong clientId ;

    @Autowired
    public IdCreatorService(AppConfig appConfig) {
        this.appConfig = appConfig;
        clientId = new AtomicLong(appConfig.getRequestCount());
    }

    public static long getAndIncrementClientId() {
        long id = clientId.getAndIncrement();
        return id;
    }

    public static long getClientId() {
        return clientId.get();
    }
}
