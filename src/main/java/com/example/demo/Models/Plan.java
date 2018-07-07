package com.example.demo.Models;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "plan")
public class Plan {
    @Id
    @SequenceGenerator(name = "plan_seq", sequenceName = "PLAN_SEQ")
    @GeneratedValue(generator ="plan_seq" ,strategy = GenerationType.AUTO)
    private int id;
    private int price;
    private String date;
    private String time;
    private int status = 0;
    private int day;
    // 0 : available
    // 1 : reserved
    // 2 : not available

    public Plan(int price , String date , String time, int status){
        this.price = price;
        this.date = date;
        this.time = time;
        this.status = status;
    }
    public Plan(){}

    public void setId(int id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getId() {
        return id;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
