package com.example.demo.Controllers;

import com.example.demo.Models.Club;
import com.example.demo.Models.ResponseObject;
import com.example.demo.Models.User;
import com.example.demo.Services.ClubService;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api")
public class ClubController {
    @Autowired
    private ClubService clubService;
    @Autowired
    private UserService UserService;

    public ClubController(ClubService clubService,UserService UserService){
        this.clubService = clubService;
        this.UserService = UserService;
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
}
