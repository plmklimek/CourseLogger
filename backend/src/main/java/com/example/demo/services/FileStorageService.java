package com.example.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileStorageService {
    private final String DIRECTORY_NAME = "uploads//";

    private final Path root = Paths.get("uploads");

    public String save(MultipartFile file) {
        try {
            createDirIfNotExist();

            byte[] bytes = new byte[0];
            bytes = file.getBytes();

            String name = UUID.randomUUID().toString();

            Files.write(Paths.get(DIRECTORY_NAME + getGeneratedName(name, file)), bytes);

            return getGeneratedName(name, file);
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }

            throw new RuntimeException(e.getMessage());
        }
    }

    private void createDirIfNotExist() {
        //create directory to save the files
        File directory = new File(DIRECTORY_NAME);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    private String getGeneratedName(String name, MultipartFile file) {
        return name + "." + Objects.requireNonNull(file.getOriginalFilename())
                .substring(file.getOriginalFilename().lastIndexOf(".") + 1);
    }
}
