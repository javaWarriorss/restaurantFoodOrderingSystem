package com.example.restaurantfoodorderingsystem.controllers;

import com.example.restaurantfoodorderingsystem.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//@Controller
//public class UploadController {
//
//    /// method for  upload image
//    public static String uploads = System.getProperty("user.dir") + "/zino";
//
//    @GetMapping("/uploadImage") public String displayUploadForm() {
//        return "/uploadImage";
//    }
//
//    @PostMapping("/upload") public String uploadImage(Model model, @RequestParam("image") MultipartFile file) throws IOException {
//        StringBuilder fileNames = new StringBuilder();
//        Path fileNameAndPath = Paths.get(uploads, file.getOriginalFilename());
//        fileNames.append(file.getOriginalFilename());
//        Files.write(fileNameAndPath, file.getBytes()); // change
//        model.addAttribute("msg", "Uploaded images: " + fileNames.toString());
//        return "uploadImage";
//    }


    @Controller public class UploadController {

        public FileService fileService;
        @Autowired
        public UploadController(FileService fileService){
            this.fileService = fileService;
        }

//        public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

        @GetMapping("/uploadimage") public String displayUploadForm() {
            return "uploadImage";
        }



        @PostMapping("/upload") public String uploadImage(Model model, @RequestParam("image") MultipartFile file) throws IOException {
            this.fileService.save(file);

//            StringBuilder fileNames = new StringBuilder();
//            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
//            fileNames.append(file.getOriginalFilename());
//            System.out.println(file.getOriginalFilename());
//            Files.write(fileNameAndPath, file.getBytes());
//            System.out.println(file.getBytes());

            model.addAttribute("msg", "Uploaded images: " + file.getOriginalFilename());
            return "uploadImage";
        }
    }



