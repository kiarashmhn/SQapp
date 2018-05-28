package com.example.demo.Models;

import javax.persistence.*;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @SequenceGenerator(name = "tag_seq", sequenceName = "TAG_SEQ")
    @GeneratedValue(generator ="tag_seq" ,strategy = GenerationType.AUTO)
    private Long Id;
    private String name = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tag(String name){
        this.name = name;
    }
}
