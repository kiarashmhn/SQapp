package com.example.demo.Services;

import com.example.demo.Models.Club;
import com.example.demo.Models.Image;
import com.example.demo.Models.Owner;
import com.example.demo.Repositories.OwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ImageService {
    private OwnerRepository ownerRepository;

    public ImageService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }
    public String savePhoto(MultipartFile multipartFile,Owner owner){
        try{
            byte[] bytes = multipartFile.getBytes();
            String str = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().length()-4);
            Image photo = new Image(str);
            str = photo.getNumber() + str;
            Path path = Paths.get("src/main/resources/static/" +str);
            System.out.println("beforewrite");
            Files.write(path, bytes);
            System.out.println("afterwrite");

            Club club = owner.getClub();
            List<Image> images = club.getImages();
            images.add(photo);
            club.setImages(images);
            ownerRepository.save(owner);
            return str;
        }
        catch (IOException e){
            return null;
        }
    }
}
