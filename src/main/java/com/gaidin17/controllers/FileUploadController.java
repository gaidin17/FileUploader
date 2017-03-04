package com.gaidin17.controllers;


import com.gaidin17.config.AppConfig;
import com.gaidin17.domain.User;
import com.gaidin17.service.IdCreatorService;
import com.gaidin17.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


@Controller
public class FileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
    private AppConfig appConfig;
    private UserService userService;

    @Autowired
    public FileUploadController(UserService userService, AppConfig appConfig) {
        this.userService = userService;
        this.appConfig = appConfig;
    }

    @RequestMapping("/")
    public String helloWorld(Model model) {
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        long userCode;
        if (!userService.isUserInSystem(sessionId)) {
            userCode = IdCreatorService.getAndIncrementClientId();
            User user = new User(sessionId);
            user.setUserCode(userCode);
            userService.addUserToSystem(user);
        } else {
            userCode = userService.getUserById(sessionId).getUserCode();
        }
        model.addAttribute("code", userCode);
        model.addAttribute("sessionId", sessionId);
        return "index";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file[]") MultipartFile[] files, @RequestParam("sessionId") String sessionId, Model model) {
        long userCode;
        if (userService.isUserInSystem(sessionId)) {
            User user = userService.getUserById(sessionId);
            userCode = user.getUserCode();
            model.addAttribute("code", userCode);
            File folder = new File(appConfig.getFolderForPhotos() + "/" + userCode + "___" + sessionId);
            if (!folder.exists()) {
                folder.mkdir();
            }
            List<String> notUploadedFiles = new ArrayList<>();
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    String name = file.getOriginalFilename();
                    try {
                        byte[] bytes = file.getBytes();
                        BufferedOutputStream stream =
                                new BufferedOutputStream(new FileOutputStream(new File(folder.getAbsolutePath() + "/" + name)));
                        stream.write(bytes);
                        stream.close();
                    } catch (Exception e) {
                        folder.delete();
                        notUploadedFiles.add(name);
                    }
                } else {
                    model.addAttribute("error", "Необходимо выбрать одну или несколько фотографий для загрузки");
                    return "uploadResult";
                }
            }
            if (!notUploadedFiles.isEmpty()) {
                model.addAttribute("error", "Вам не удалось выгрузить " + notUploadedFiles.size() + " файлов");
            }
        } else {
            model.addAttribute("error", "что-то пошло не так");
        }
        return "uploadResult";
    }
}

