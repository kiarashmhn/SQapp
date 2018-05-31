package com.example.demo.Services;

import com.example.demo.Models.Club;
import com.example.demo.Models.Tag;
import com.example.demo.Models.User;
import com.example.demo.Repositories.ClubRepository;
import com.example.demo.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ClubService {
    private ClubRepository clubRepository;
    private UserRepository userRepository;
    public ClubService(ClubRepository clubRepository, UserRepository userRepository){
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
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
    public void createClub(Club club,User user){
        Club club1 = new Club(club.getName(),club.getOwner(),club.getTelePhoneNumber(),club.getCellPhoneNumber(),club.getAddress(),club.getOwnerUserName(),club.getLatitude(),club.getLongtitude());
        user.setClub(club1);
        userRepository.save(user);
    }
    public void updateClub(Club club,User user){
        Club x = user.getClub();
        x.setOpeningTime(club.getOpeningTime());
        x.setClosingTime(club.getClosingTime());
        x.setType(club.getType());
        x.setTagList(createTagArray(club.getTags()));
        x.setVerified(true);
        x.setTags(club.getTags());
        user.setClub(x);
        userRepository.save(user);
    }

    private List<Tag> createTagArray(List<String> stringTag){
        List<Tag> tagList = new ArrayList<>();
        for (String name : stringTag) {
            Tag tag = new Tag();
            tag.setName(name);
            tagList.add(tag);
        }
        return tagList;
    }

}
