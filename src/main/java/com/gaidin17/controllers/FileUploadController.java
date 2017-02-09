package com.gaidin17.controllers;

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

    @RequestMapping("/")
    public String helloWorld(Model model) {
        model.addAttribute("code", "Добрый день");
        return "index";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public
    @ResponseBody
    String handleFileUpload(@RequestParam("file[]") MultipartFile[] files) {
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String name = file.getOriginalFilename();
                try {
                    byte[] bytes = file.getBytes();
                    BufferedOutputStream stream =
                            new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    return "Вам не удалось загрузить " + name + " => " + e.getMessage();
                }
            } else {
                return "Вам не удалось загрузить  потому что файл пустой.";
            }
        }
        return "Вы удачно ФСЕ загрузили";
    }

}

