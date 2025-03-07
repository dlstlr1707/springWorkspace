package com.example.basicboardv2prac.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {
    private final String UPLOADED_FOLDER = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "workspace" + File.separator + "springWorkspace" + File.separator + "uploads" + File.separator;

    public String fileUpload(MultipartFile file) {
        try{
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());

            Files.write(path, bytes);

            return UPLOADED_FOLDER + file.getOriginalFilename();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
