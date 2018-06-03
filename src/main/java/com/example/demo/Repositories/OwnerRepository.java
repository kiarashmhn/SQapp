package com.example.demo.Repositories;

import com.example.demo.Models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner,Long> {
    Owner findOwnerByUserName(String username);
}
