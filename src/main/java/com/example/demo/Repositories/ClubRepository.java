package com.example.demo.Repositories;

import com.example.demo.Models.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club,Long> {
    Club findClubByName(String name);
}
