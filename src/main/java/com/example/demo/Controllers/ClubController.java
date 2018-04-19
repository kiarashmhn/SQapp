package com.example.demo.Controllers;

import com.example.demo.Models.Club;
import com.example.demo.Models.ResponseObject;
import com.example.demo.Services.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api/clubs")
public class ClubController {
    @Autowired
    private ClubService clubService;

    public ClubController(ClubService clubService){
        this.clubService = clubService;
    }

    @GetMapping
    public @ResponseBody ResponseObject findAllClubs(){
        return new ResponseObject(clubService.findAll(),1);
    }
    @GetMapping("/{name}")
    public @ResponseBody ResponseObject findClub(@PathVariable String name){
        return new ResponseObject(clubService.findClubByName(name),1);
    }

    @PostMapping
    public @ResponseBody ResponseObject createClub(@RequestBody Club club){
        try{
            clubService.createClub(club);
            return new ResponseObject(null,1);
        }
        catch (NullPointerException e){
            return new ResponseObject(null,2);
        }
    }
}
