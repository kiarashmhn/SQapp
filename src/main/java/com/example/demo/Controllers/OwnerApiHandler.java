package com.example.demo.Controllers;

import com.example.demo.Models.Owner;
import com.example.demo.Models.ResponseObject;
import com.example.demo.Models.User;
import com.example.demo.Services.OwnerService;
import com.example.demo.Security.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/owners")
public class  OwnerApiHandler {
    @Autowired
    private OwnerService ownerService;

    public OwnerApiHandler(OwnerService OwnerService){
        this.ownerService = OwnerService;
    }

    @PostMapping("/login")
    public @ResponseBody ResponseObject login(@RequestBody Owner user){
        Owner user1 = ownerService.findByUserName(user.getUserName());
        try{
            //if(user1.getPassWord().equals(MD5.getMD5(user.getPassWord()))){
            if(user1.getPassWord().equals(user.getPassWord())){
                return new ResponseObject(user1,1);
            }
            return new ResponseObject(user1,2);}
        catch (Exception e){
            return new ResponseObject(user1,3);
        }
    }
    @PostMapping("/sign-up")
    public @ResponseBody ResponseObject createOwner(@RequestBody Owner user) throws Exception{
        Owner user1 = ownerService.findByUserName(user.getUserName());
        if (user1 == null){
            ownerService.createOwner(user);
            return new ResponseObject(null,1);}
        else
            return new ResponseObject(null,2);
    }
    @GetMapping("/{username}/{password}")
    public @ResponseBody ResponseObject getOwner(@PathVariable String username,@PathVariable String password){
        Owner user1 = ownerService.findByUserName(username);
        try {
            //user1.getPassWord().equals(MD5.getMD5(password))
            if (user1.getPassWord().equals(password)) {
                return new ResponseObject(ownerService.findByUserName(username), 1);
            } else
                return new ResponseObject(null, 2);
        }
        catch (Exception e){
            return new ResponseObject(null,3);
        }
    }


}
