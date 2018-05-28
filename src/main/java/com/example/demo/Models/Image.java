package com.example.demo.Models;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kiarash23 on 5/28/2018.
 */
/*@Entity
@Table(name = "images")*/
public class Image {
    /*@Id
    @SequenceGenerator(name = "image_seq", sequenceName = "image_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "image_seq")*/
    private int id;
    private static int count = 0;
    //@Lob
    //private byte[] imageBytes;
    private MultipartFile file;

    public int getId() {
        return id;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Image(MultipartFile file){
        this.file = file;
        this.id = count;
        count++;
    }

    public Image(){}


    /*public byte[] getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }*/
}
