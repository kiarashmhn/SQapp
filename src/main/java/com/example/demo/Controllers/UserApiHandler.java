package com.example.demo.Controllers;

import com.example.demo.Models.Owner;
import com.example.demo.Models.ResponseObject;
import com.example.demo.Models.User;
import com.example.demo.Security.MD5;
import com.example.demo.Services.OwnerService;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserApiHandler {
    @Autowired
    private UserService userService;

    public UserApiHandler(UserService UserService){
        this.userService = UserService;
    }

    /*@PostMapping("/login")
    public @ResponseBody
    ResponseObject login(@RequestBody User user){
        User user1 = userService.findByUserName(user.getUserName());
        try{
            //if(user1.getPassWord().equals(MD5.getMD5(user.getPassWord()))){
            if(user1.getPassWord().equals(user.getPassWord())){
                return new ResponseObject(null,1);
            }
            return new ResponseObject(null,2);}
        catch (Exception e){
            return new ResponseObject(null,3);
        }
    }*/
    @PostMapping("/sign-up")
    public @ResponseBody ResponseObject createOwner(@RequestBody User user) throws Exception{
        User user1 = userService.findByUserName(user.getUserName());
        if (user1 == null){
            userService.createUser(user);
            return new ResponseObject(null,1);}
        else
            return new ResponseObject(null,2);
    }
    @GetMapping("/{username}")
    public @ResponseBody ResponseObject getUser(@PathVariable String username){
        try {
            User user1 = userService.findByUserName(username);
            return new ResponseObject(user1,1);
        }
        catch (Exception e){
            return new ResponseObject(null,3);
        }
    }

}
