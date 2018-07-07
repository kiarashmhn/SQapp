package com.example.demo.Models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "owners")
public class Owner implements UserDetails {
    @Id
    @SequenceGenerator(name = "owner_seq",sequenceName = "OWNER_SEQ")
    @GeneratedValue(generator = "owner_seq",strategy = GenerationType.AUTO)
    private Long id;
    private String name = "";
    private String userName = "";
    private String passWord = "";
    private String email = "";

    private Double credit = 0.0;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Club club = null;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,targetEntity = Receipt.class)
    private List<Receipt> receipts = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,targetEntity = Transaction.class)
    private List<Transaction> transactions = new ArrayList<>();

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

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Owner(String name, String userName, String passWord, String email) {
        this.name = name;
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
    }
    public Owner(){
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.passWord;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
