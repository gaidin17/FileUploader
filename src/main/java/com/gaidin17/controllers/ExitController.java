package com.gaidin17.controllers;

import com.gaidin17.config.AppConfig;
import com.gaidin17.config.AppConfigConstants;
import com.gaidin17.service.IdCreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Gaidin on 11.02.2017.
 */
@Controller
public class ExitController {
    private IdCreatorService idCreatorService;
    private AppConfig appConfig;

    @Autowired
    public ExitController(IdCreatorService idCreatorService, AppConfig appConfig) {
        this.appConfig = appConfig;
        this.idCreatorService = idCreatorService;
    }

    @RequestMapping(value = "/stop_service", method = RequestMethod.GET)
    public void exit() {
        appConfig.getProperties().setProperty(AppConfigConstants.REQUEST_COUNT, String.valueOf(idCreatorService.getClientId()));
        appConfig.saveConfig();
        System.exit(0);
    }
}
