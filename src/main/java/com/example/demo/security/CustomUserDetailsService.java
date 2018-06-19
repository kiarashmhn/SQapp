package com.example.demo.Security;

import com.example.demo.Models.Owner;
import com.example.demo.Repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.HashSet;
import java.util.Set;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final OwnerRepository ownerRepository;
    @Autowired
    public CustomUserDetailsService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Owner user = ownerRepository.findOwnerByUserName(username);

        if (user == null) {
            // Not found...
            throw new UsernameNotFoundException(
                    "User " + username + " not found.");
        }
        return user;
    }
}
