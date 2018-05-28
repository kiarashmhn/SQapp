package com.example.demo.Controllers;

import com.example.demo.Models.Club;
import com.example.demo.Models.Image;
import com.example.demo.Models.ResponseObject;
import com.example.demo.Models.User;
import com.example.demo.Services.ClubService;
import com.example.demo.Services.ImageService;
import com.example.demo.Services.UserService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RestController

@RequestMapping("/api")
public class ClubController {
    @Autowired
    private ClubService clubService;
    @Autowired
    private UserService UserService;
    @Autowired
    ImageService imageService;

    public ClubController(ClubService clubService,UserService UserService){
        this.clubService = clubService;
        this.UserService = UserService;
        this.imageService = imageService;
    }

    @GetMapping("/clubs")
    public @ResponseBody ResponseObject findAllClubs(){
        return new ResponseObject(clubService.findAll(),1);
    }
    @GetMapping("/clubs/{name}")
    public @ResponseBody ResponseObject findClub(@PathVariable String name){
        return new ResponseObject(clubService.findClubByName(name),1);
    }

    @PostMapping("/clubs/{password}")
    public @ResponseBody ResponseObject registerClub(@PathVariable String password,@RequestBody Club club){
        User user1 = UserService.findByUserName(club.getOwnerUserName());
        if(user1.getPassWord().equals(password)){
            try{
            clubService.createClub(club,user1);
            return new ResponseObject(null,1);
        }
        catch (NullPointerException e){
            return new ResponseObject(null,2);
        }}
        else
            return new ResponseObject(null,3);
    }
    @PutMapping("/clubs/{password}")
    public @ResponseBody ResponseObject updateClub(@PathVariable String password,@RequestBody Club club){
        User user1 = UserService.findByUserName(club.getOwnerUserName());
        if(user1.getPassWord().equals(password)){
        try{
            clubService.updateClub(club,user1);
            return new ResponseObject(null,1);
        }
        catch (NullPointerException e){
            return new ResponseObject(null,2);
        }}
        else
            return new ResponseObject(null,3);
    }
    @PostMapping("/users")
    public @ResponseBody ResponseObject createOwner(@RequestBody User user){
        UserService.createUser(user);
        return new ResponseObject(null,1);
    }
    @GetMapping("/users")
    public @ResponseBody ResponseObject findAllOwners(){
        return new ResponseObject(UserService.findAll(),1);
    }
    @GetMapping("/users/{username}")
    public @ResponseBody ResponseObject getOwner(@PathVariable String username){
        return new ResponseObject(UserService.findByUserName(username),1);
    }
    @PostMapping("/images/{username}/{password}")
    public ResponseObject uploadPhoto(@RequestParam("photo") MultipartFile multipartFile,@PathVariable String username,@PathVariable String password) {
        User user1 = UserService.findByUserName(username);
        if (user1.getPassWord().equals(password)) {
            if (multipartFile.isEmpty()) {
                return new ResponseObject(null, 7);
            }
            String s = imageService.savePhoto(multipartFile,user1);
            if (s == null) {
                return new ResponseObject(null, 8);
            }
            return new ResponseObject(s, 1);
        }
        else
            return new ResponseObject(null, 2);
    }
    @GetMapping("/images/{photo}.{suf}")
    public ResponseEntity<?> downloadPostPhoto(@PathVariable("photo") String photo, @PathVariable("suf") String suf) throws IOException {

        try{

            InputStream in = FileUtils.openInputStream(new File("src/main/resources/static/" + photo + "." +suf));
            return new ResponseEntity(IOUtils.toByteArray(in), HttpStatus.OK);



        } catch (Exception e){

            return new ResponseEntity(null, HttpStatus.NOT_FOUND);

        }
    }

}
