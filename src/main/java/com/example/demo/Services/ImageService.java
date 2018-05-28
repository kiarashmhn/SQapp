package com.example.demo.Services;

import com.example.demo.Models.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {
    public String savePhoto(MultipartFile multipartFile){
        try{
            byte[] bytes = multipartFile.getBytes();
            Image photo = new Image(multipartFile);
            Path path = Paths.get("src/main/resources/static/" + photo.getId() + multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().length()-4));
            System.out.println("beforewrite");
            Files.write(path, bytes);
            System.out.println("afterwrite");
            return photo.getId()+multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().length()-4);
        }
        catch (IOException e){
            return null;
        }
    }
}
