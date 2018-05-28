package com.example.demo.Models;

import javax.persistence.*;

/**
 * Created by Kiarash23 on 5/28/2018.
 */
@Entity
@Table(name = "images")
public class Image {
    @Id
    @SequenceGenerator(name = "image_seq", sequenceName = "image_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "image_seq")
    int id;
    byte[] imageBytes;
}
