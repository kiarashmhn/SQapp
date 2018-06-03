package com.example.demo.Models;

import javax.persistence.*;

@Entity
@Table(name = "owners")
public class Owner {
    @Id
    @SequenceGenerator(name = "owner_seq",sequenceName = "OWNER_SEQ")
    @GeneratedValue(generator = "owner_seq",strategy = GenerationType.AUTO)
    private Long id;
    private String name = "";
    private String userName = "";
    private String passWord = "";
    private String email = "";
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Club club = null;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Owner(String name, String userName, String passWord, String email) {
        this.name = name;
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
    }
    public Owner(){
    }
}
