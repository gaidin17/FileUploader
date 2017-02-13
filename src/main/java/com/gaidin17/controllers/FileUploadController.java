package com.gaidin17.controllers;


import com.gaidin17.config.AppConfig;
import com.gaidin17.service.IdCreatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;


@Controller
public class FileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
    private AppConfig appConfig;

    @Autowired
    public FileUploadController(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @RequestMapping("/")
    public String helloWorld() {
        return "index";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file[]") MultipartFile[] files, Model model) {
        long userCode = IdCreatorService.getAndIncrementClientId();
        model.addAttribute("code", userCode);
        File folder = new File(appConfig.getHomeDir() + "\\" + userCode);

        if (folder.exists()) {
            folder.mkdir();
        } else {
            model.addAttribute("error", "Вам не удалось загрузить фотографии, обратитесь к менеджеру");
            return "error";
        }

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String name = file.getOriginalFilename();
                try {
                    byte[] bytes = file.getBytes();
                    BufferedOutputStream stream =
                            new BufferedOutputStream(new FileOutputStream(new File(folder + "\\" + name)));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    folder.delete();
                    model.addAttribute("error", "Вам не удалось загрузить" + name + " => " + e.getMessage());
                    return "error";
                }
            } else {
                model.addAttribute("error", "Необходимо выбрать одну или несколько фотографий для загрузки");
                return "error";
            }
        }
        return "uploadResult";
    }
}

