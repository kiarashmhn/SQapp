package com.example.demo.Services;

import com.example.demo.Models.Club;
import com.example.demo.Models.Image;
import com.example.demo.Models.User;
import com.example.demo.Repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {
    private UserRepository userRepository;

    public ImageService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public String savePhoto(MultipartFile multipartFile,User user){
        try{
            byte[] bytes = multipartFile.getBytes();
            String str = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().length()-4);
            Image photo = new Image(str);
            str = photo.getNumber() + str;
            Path path = Paths.get("src/main/resources/static/" +str);
            System.out.println("beforewrite");
            Files.write(path, bytes);
            System.out.println("afterwrite");

            Club club = user.getClub();
            List<Image> images = club.getImages();
            images.add(photo);
            club.setImages(images);
            userRepository.save(user);
            return str;
        }
        catch (IOException e){
            return null;
        }
    }
}
