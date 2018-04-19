package com.example.demo.Services;

import com.example.demo.Models.Club;
import com.example.demo.Repositories.ClubRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class ClubService {
    private ClubRepository clubRepository;
    public ClubService(ClubRepository clubRepository){
        this.clubRepository = clubRepository;
    }
    public Club findClubByName(String name){
        Iterable<Club> clubs = clubRepository.findAll();
        for(Club x:clubs)
            if (x.getName().equals(name))
                return x;
        return null;
    }
    public Iterable<Club> findAll(){
        Iterable<Club> clubs = clubRepository.findAll();
        return clubs;
    }
    public void createClub(Club club){
        //Club club1 = new Club(club.getName(),club.getAddress(),club.getPrice(),club.getRate(),club.getImage());
        clubRepository.save(club);
    }

}
