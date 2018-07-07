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
    public @ResponseBody ResponseObject reserve(@PathVariable int ownerId, @PathVariable int userId, @PathVariable int planId){
        /*userService.createReceipt(planId,ownerId,userId);
        userService.createTransaction(planId,userId);
        ownerService.createReceipt(planId,ownerId,userId,ownerId);
        ownerService.createTransaction(planId,userId,ownerId);*/
        return new ResponseObject(null,1);
    }
}
