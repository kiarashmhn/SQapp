package com.example.demo.Controllers;

import com.example.demo.Models.Club;
import com.example.demo.Models.Owner;
import com.example.demo.Models.ResponseObject;
import com.example.demo.Services.ClubService;
import com.example.demo.Services.ImageService;
import com.example.demo.Services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController

@RequestMapping("/api/clubs")
public class ClubApiHandler {
    @Autowired
    private ClubService clubService;
    @Autowired
    private OwnerService ownerService;

    public ClubApiHandler(ClubService clubService, OwnerService OwnerService, ImageService imageService){
        this.clubService = clubService;
        this.ownerService = OwnerService;
        Club club = new Club("a","a","a","a","a","a",0.1,0.2);
        club.setVerified(true);
        Owner owner = new Owner("kia","kia2","123","kia@kia.com");
        try {
            ownerService.createOwner(owner);
            clubService.createClub(club,owner);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("")
    public @ResponseBody ResponseObject findAllClubs(){
        try {
            return new ResponseObject(clubService.findAll(), 1);
        }
        catch (Exception e){
            return new ResponseObject(null,2);
        }
    }
    @GetMapping("/{name}")
    public @ResponseBody ResponseObject findClub(@PathVariable String name){
        try {
            return new ResponseObject(clubService.findClubByName(name),1);
        }
        catch(Exception e){
            return new ResponseObject(null,2);
        }

    }

    @PostMapping("/{password}")
    public @ResponseBody ResponseObject registerClub(@PathVariable String password,@RequestBody Club club){
        Owner user1 = ownerService.findByUserName(club.getOwnerUserName());
        try{
            //if(user1.getPassWord().equals(MD5.getMD5(password)) && user1.getClub() == null){
            if(user1.getPassWord().equals(password)&&user1.getClub()==null){
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
    @PutMapping("/{password}")
    public @ResponseBody ResponseObject updateClub(@PathVariable String password,@RequestBody Club club){
        Owner user1 = ownerService.findByUserName(club.getOwnerUserName());
        try {
            //if (user1.getPassWord().equals(MD5.getMD5(password)) && user1.getClub() != null && !user1.getClub().getVerified()) {
            if (user1.getPassWord().equals(password) && user1.getClub() != null && !user1.getClub().getVerified()) {
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
    @GetMapping("/tags")
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
