package com.example.demo.Services;

import com.example.demo.Models.User;
import com.example.demo.Repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Iterable<User> findAll(){
        Iterable<User> users = userRepository.findAll();
        return users;
    }
    public void createUser(User user){
        User user1 = new User(user.getName(),user.getUserName(),user.getPassWord(),user.getEmail());
        userRepository.save(user1);
    }
    public Boolean checkPassWord(String userName,String password){
        Iterable<User> users = userRepository.findAll();
        for (User x: users)
            if (x.getPassWord().equals(password) && x.getUserName().equals(userName))
                return true;
        return false;
    }
    public User findByUserName(String username){
        Iterable<User> users = userRepository.findAll();
        for (User x: users)
            if(x.getUserName().equals(username))
                return x;
        return null;
    }
}
