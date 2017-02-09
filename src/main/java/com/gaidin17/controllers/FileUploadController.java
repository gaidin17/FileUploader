package com.gaidin17.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileUploadController {
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("code","Greetings from Spring Boot!" );
        return "hello";
    }
}
