package com.example.demo.Controllers;

import com.example.demo.Models.Owner;
import com.example.demo.Models.Plan;
import com.example.demo.Models.ResponseObject;
import com.example.demo.Models.User;
import com.example.demo.Services.ImageService;
import com.example.demo.Services.OwnerService;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/api/reserve")
public class ReserveApiHandler {
    @Autowired
    private UserService userService;
    @Autowired
    private OwnerService ownerService;
    public ReserveApiHandler(UserService userService, OwnerService ownerService){
        this.userService = userService;
        this.ownerService = ownerService;
    }

    @PostMapping("/{ownerId}/{userId}/{planId}")
    public @ResponseBody ResponseObject reserve(@PathVariable Owner owner, @PathVariable User user, @PathVariable Plan plan){
        userService.createReceipt(plan,owner.getClub(),user);
        userService.createTransaction(plan,user);
        ownerService.createReceipt(plan,owner.getClub(),user,owner);
        ownerService.createTransaction(plan,user,owner);
        return new ResponseObject(null,1);
    }
}
