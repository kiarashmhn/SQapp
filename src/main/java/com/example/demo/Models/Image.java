package com.example.demo.Models;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kiarash23 on 5/28/2018.
 */
@Entity
@Table(name = "images")
public class Image {
    @Id
    @SequenceGenerator(name = "image_seq", sequenceName = "image_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "image_seq")
    private int id;
    private String name = "";
    private static int count = 0;
    private int number;

    public Image(String name){
        this.name = count+name;
        this.number = count;
        count++;
    }

    public Image(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
