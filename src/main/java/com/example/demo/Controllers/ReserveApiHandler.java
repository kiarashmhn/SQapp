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

@RestController
@RequestMapping("/api/reserve")
public class ReserveApiHandler {
    @Autowired
    private UserService userService;
    @Autowired
    private OwnerService ownerService;
    public ReserveApiHandler(UserService userService, OwnerService ownerService){
        this.userService = userService;
        this.ownerService = ownerService;
    }

    @PostMapping("/{ownerUserName}/{userId}/{planId}")
    public @ResponseBody ResponseObject reserve(@PathVariable String ownerUserName,@PathVariable Long userId,@PathVariable Long planId){
        userService.createReceipt(ownerUserName,userId,planId);
        userService.createTransaction(ownerUserName,userId,planId);
        ownerService.createReceipt(ownerUserName,userId,planId);
        ownerService.createTransaction(ownerUserName,userId,planId);
        return new ResponseObject(null,1);
    }
}
