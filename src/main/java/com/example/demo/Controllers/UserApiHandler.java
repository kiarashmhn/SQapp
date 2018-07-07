package com.example.demo.Controllers;
import com.example.demo.Models.Owner;
import com.example.demo.Models.ResponseObject;
import com.example.demo.Models.User;
import com.example.demo.Security.MD5;
import com.example.demo.Services.OwnerService;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
public class UserApiHandler {
    @Autowired
    private UserService userService;

    public UserApiHandler(UserService UserService){
        this.userService = UserService;
    }
    private void checkResourceOwner(String username, Principal principal) {
        if (principal instanceof OAuth2Authentication) {
            OAuth2Authentication authentication = (OAuth2Authentication) principal;
            if (!authentication.isClientOnly() && !username.equals(principal.getName())) {
                throw new AccessDeniedException(String.format("User '%s' cannot obtain endpoints of user '%s'",
                        principal.getName(), username));
            }
            else{
                User user = userService.findByUserName(username);
                if(user == null){
                    throw  new AccessDeniedException("User not found!");
                }
            }
        }
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
    public @ResponseBody ResponseObject getUser(Principal principal){
        try {
            User user1 = userService.findByUserName(principal.getName());
            return new ResponseObject(user1,1);
        }
        catch (Exception e){
            return new ResponseObject(null,3);
        }
    }
    @PutMapping("")
    public @ResponseBody ResponseObject updateUser(User user) {

        try {
            User user1 = userService.findByUserName(user.getUsername());
            try {
                userService.updateUser(user, user1);
            } catch (Exception e) {
                return new ResponseObject(null, 2);
            }
        } catch (Exception e) {
            return new ResponseObject(null, 3);
        }
        return new ResponseObject(null,1);
    }
    /*@PutMapping("/{username}")
    public @ResponseBody ResponseObject updateUser(@PathVariable String username){
}*/
}