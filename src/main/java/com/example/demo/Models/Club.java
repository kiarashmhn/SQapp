package com.example.demo.Models;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "clubs")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name = "";

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    private String address = "";
    private String price = "";
    private double rate = 0;
    private int image = 0;

    public Club(String name,String address,String price,double rate,int image){
        this.address = address;
        this.name = name;
        this.price = price;
        this.rate = rate;
        this.image = image;

    }
    public Club(){
    }
}
