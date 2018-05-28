package com.example.demo.Models;

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
    private byte[] imageBytes;


    public byte[] getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }
}
