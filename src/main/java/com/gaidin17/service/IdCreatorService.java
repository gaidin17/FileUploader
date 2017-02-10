package com.gaidin17.service;

import org.springframework.stereotype.Component;

/**
 * Created by Evgeny_Akulenko on 2/10/2017.
 */
@Component
public class IdCreatorService {
    private static volatile long clientId = 0;

    public static long getClientId() {
        long id = clientId;
        clientId++;
        return id;
    }
}
