package com.example.demo.Controllers;

import com.example.demo.Models.Owner;
import com.example.demo.Models.ResponseObject;
import com.example.demo.Services.ImageService;
import com.example.demo.Services.OwnerService;
import com.example.demo.security.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
public class ImageApiHandler {
    @Autowired
    private ImageService imageService;
    @Autowired
    private OwnerService ownerService;

    public ImageApiHandler(ImageService imageService,OwnerService ownerService){
        this.imageService = imageService;
        this.ownerService = ownerService;
    }

    @PostMapping("/images/{username}/{password}")
    public ResponseObject uploadPhoto(@RequestParam("photo") MultipartFile multipartFile,@PathVariable String username,@PathVariable String password) {
        Owner user1 = ownerService.findByUserName(username);
        try {
            if (user1.getPassWord().equals(MD5.getMD5(password))) {
                if (multipartFile.isEmpty()) {
                    return new ResponseObject(null, 7);
                }
                String s = imageService.savePhoto(multipartFile, user1);
                if (s == null) {
                    return new ResponseObject(null, 8);
                }
                return new ResponseObject(s, 1);
            } else
                return new ResponseObject(null, 2);
        }
        catch (Exception e){
            return new ResponseObject(null,2);
        }
    }
}
