package com.example.demo.Models;
import javax.persistence.*;
@Entity(name = "receipt")
@Table(name = "reciept")
public class Receipt {
    @Id
    @SequenceGenerator(name = "receipt_seq", sequenceName = "RECEIPT_SEQ")
    @GeneratedValue(generator ="receipt_seq" ,strategy = GenerationType.AUTO)
    private int id;
    private int price;
    private String date;
    private String time;
    private String clubName;
    private String clubAddress;
    public Receipt(){

    }

    public Receipt(int price, String date, String time, String clubName, String clubAddress) {
        this.price = price;
        this.date = date;
        this.time = time;
        this.clubName = clubName;
        this.clubAddress = clubAddress;
    }

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

    public String getClubAddress(String address) {
        return clubAddress;
    }

    public void setClubAddress(String clubAddress) {
        this.clubAddress = clubAddress;
    }
}