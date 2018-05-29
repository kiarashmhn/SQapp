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
        /*Club club1 = new Club("باشگاه بدنسازی","آدرس ۱","5,000 تومان",3.0,0);
        clubRepository.save(club1);
        Club club2 = new Club("باشگاه ژیمناستیک","آدرس ۲","10,000 تومان",3.5,1);
        clubRepository.save(club2);
        Club club3 = new Club("استخر","آدرس ۳","5,000 تومان",4.5,2);
        clubRepository.save(club3);
        Club club4 = new Club("سالن","آدرس ۴","20,000 تومان",5.0,3);
        clubRepository.save(club4);*/
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
        //clubRepository.save(club1);
    }
    public void updateClub(Club club,User user){
        /*Optional<Club> club1 = clubRepository.findById(club.getId());
        club1.*/
        Club x = user.getClub();
        x.setOpeningTime(club.getOpeningTime());
        x.setClosingTime(club.getClosingTime());
        x.setType(club.getType());
        x.setTagList(createTagArray(club.getTags()));
        //x.setImageArray(StringToImage(club.getImages()));
//                x.setImageArray(club.getImageArray());
//                x.setPlan(club.getPlan());
        x.setVerified(true);
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
