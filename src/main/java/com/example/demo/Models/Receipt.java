package com.example.demo.Models;
import javax.persistence.*;
@Entity
@Table(name = "reciept")
public class Receipt {
    private int price;
    private String date;
    private String time;
    private String clubName;
    private String clubAdress;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getClubName() {
        return clubName;
    }
    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubAdress() {
        return clubAdress;
    }

    public void setClubAdress(String clubAdress) {
        this.clubAdress = clubAdress;
    }
}
