package com.example.demo.Controllers;

import com.example.demo.Models.Club;
import com.example.demo.Models.ResponseObject;
import com.example.demo.Models.User;
import com.example.demo.Services.ClubService;
import com.example.demo.Services.ImageService;
import com.example.demo.Services.UserService;
import com.example.demo.security.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

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
    @PostMapping("/users/login")
    public @ResponseBody ResponseObject login(@RequestBody User user){
        User user1 = UserService.findByUserName(user.getUserName());
        try{
        if(MD5.getMD5(user1.getPassWord()).equals(MD5.getMD5(user.getPassWord()))){
            return new ResponseObject(null,1);
        }
        return new ResponseObject(null,2);}
        catch (Exception e){
            return new ResponseObject(null,3);
        }
    }
    @GetMapping("/clubs")
    public @ResponseBody ResponseObject findAllClubs(){
        try {
            return new ResponseObject(clubService.findAll(), 1);
        }
        catch (Exception e){
            return new ResponseObject(null,2);
        }
    }
    @GetMapping("/clubs/{name}")
    public @ResponseBody ResponseObject findClub(@PathVariable String name){
        try {
            return new ResponseObject(clubService.findClubByName(name),1);
        }
        catch(Exception e){
            return new ResponseObject(null,2);
        }

    }

    @PostMapping("/clubs/{password}")
    public @ResponseBody ResponseObject registerClub(@PathVariable String password,@RequestBody Club club){
        User user1 = UserService.findByUserName(club.getOwnerUserName());
        try{
        if(MD5.getMD5(user1.getPassWord()).equals(MD5.getMD5(password)) && user1.getClub() == null){
            try{
            clubService.createClub(club,user1);
            return new ResponseObject(null,1);
        }
        catch (NullPointerException e){
            return new ResponseObject(null,2);
        }}
        else
            return new ResponseObject(null,3);}
        catch(Exception e){
            return new ResponseObject(null,2);
        }
    }
    @PutMapping("/clubs/{password}")
    public @ResponseBody ResponseObject updateClub(@PathVariable String password,@RequestBody Club club){
        User user1 = UserService.findByUserName(club.getOwnerUserName());
        try {
            if (MD5.getMD5(user1.getPassWord()).equals(MD5.getMD5(password)) && user1.getClub() != null && !user1.getClub().getVerified()) {
                try {
                    clubService.updateClub(club, user1);
                    return new ResponseObject(null, 1);
                } catch (NullPointerException e) {
                    return new ResponseObject(null, 2);
                }
            } else
                return new ResponseObject(null, 3);
        }
        catch (Exception e){
            return new ResponseObject(null,2);
        }
    }
    @PostMapping("/users")
    public @ResponseBody ResponseObject createOwner(@RequestBody User user) throws Exception{
        User user1 = UserService.findByUserName(user.getUserName());
        if (user1 == null){
            UserService.createUser(user);
            return new ResponseObject(null,1);}
        else
            return new ResponseObject(null,2);

    }
    @GetMapping("/users/{username}/{password}")
    public @ResponseBody ResponseObject getOwner(@PathVariable String username,@PathVariable String password){
        User user1 = UserService.findByUserName(username);
        try {
            if (MD5.getMD5(user1.getPassWord()).equals(MD5.getMD5(password))) {
                return new ResponseObject(UserService.findByUserName(username), 1);
            } else
                return new ResponseObject(null, 2);
        }
        catch (Exception e){
            return new ResponseObject(null,3);
        }
    }
    @PostMapping("/images/{username}/{password}")
    public ResponseObject uploadPhoto(@RequestParam("photo") MultipartFile multipartFile,@PathVariable String username,@PathVariable String password) {
        User user1 = UserService.findByUserName(username);
        try {
            if (MD5.getMD5(user1.getPassWord()).equals(MD5.getMD5(password))) {
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
    @GetMapping("/clubs/tags")
    private ResponseObject getTags(){
        ArrayList<ArrayList<String>> arr = new ArrayList<>();
        ArrayList<String> saati = new ArrayList<>();
        ArrayList<String> sansi = new ArrayList<>();
        saati.add("بدنسازی");
        saati.add("ژیمناستیک");
        saati.add("تی آر ایکس");
        saati.add("یوگا");
        saati.add("ایروبیک");

        sansi.add("استخر");
        sansi.add("فوتبال");
        sansi.add("بسکتبال");
        sansi.add("فوتسال");
        sansi.add("هندبال");
        sansi.add("پینتبال");
        sansi.add("والیبال");

        arr.add(saati);
        arr.add(sansi);

        return new ResponseObject(arr,1);
    }


}

