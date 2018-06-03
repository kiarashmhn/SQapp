package com.example.demo.Services;

import com.example.demo.Models.Owner;
import com.example.demo.Repositories.OwnerRepository;
import com.example.demo.security.MD5;
import org.springframework.stereotype.Service;

@Service

public class OwnerService {
    private OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }
    public Iterable<Owner> findAll(){
        Iterable<Owner> users = ownerRepository.findAll();
        return users;
    }
    public void createOwner(Owner owner) throws Exception{
        Owner owner1 = new Owner(owner.getName(),owner.getUserName(), MD5.getMD5(owner.getPassWord()),owner.getEmail());
        ownerRepository.save(owner1);
    }
    public Boolean checkPassWord(String userName,String password){ // no MD5, because it doesn't use
        Iterable<Owner> owners = ownerRepository.findAll();
        for (Owner x: owners)
            if (x.getPassWord().equals(password) && x.getUserName().equals(userName))
                return true;
        return false;
    }
    public Owner findByUserName(String username){
        Iterable<Owner> owners = ownerRepository.findAll();
        for (Owner x: owners)
            if(x.getUserName().equals(username))
                return x;
        return null;
    }
}
