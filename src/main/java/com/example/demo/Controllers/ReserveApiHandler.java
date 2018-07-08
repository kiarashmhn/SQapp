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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


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

    @PostMapping("/{ownerId}/{userId}/{planId}")
    public @ResponseBody ResponseObject reserve(@PathVariable Long ownerId, @PathVariable Long userId, @PathVariable Long planId){
        userService.createReceipt(ownerId,userId,planId);
        userService.createTransaction(ownerId,userId,planId);
        ownerService.createReceipt(ownerId,userId,planId);
        ownerService.createTransaction(ownerId,userId,planId);
        return new ResponseObject(null,1);
    }
}
