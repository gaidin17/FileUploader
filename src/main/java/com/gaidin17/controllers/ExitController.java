package com.gaidin17.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Gaidin on 11.02.2017.
 */
@Controller
public class ExitController {

    @RequestMapping(value = "/stop_service", method = RequestMethod.GET)
    public void exit() {
        System.exit(0);
    }
}
