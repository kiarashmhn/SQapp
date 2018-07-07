package com.example.demo.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clubs")
public class Club {
    @Id
    @SequenceGenerator(name = "club_seq", sequenceName = "CLUB_SEQ")
    @GeneratedValue(generator ="club_seq" ,strategy = GenerationType.AUTO)
    private Long id;

    private String name = "";

    private String ownerUserName = "";

    private String owner = "";

    private String telePhoneNumber = "";

    private String cellPhoneNumber = "";

    private String address = "";

    private String openingTime = "";

    private String closingTime = "";

    private int type = 0;

    private Double latitude = 0.0;

    private Double longtitude = 0.0;

    private String price = "";

    private double rate = 0;

    private Boolean isVerified = false;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,targetEntity = Plan.class)
    @JoinColumn(name = "plan_fk")
    private List<Plan> weeklyPlan = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Image.class)
    @JoinColumn(name = "image_fk")
    private List<Image> images = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tag_fk")
    private List<Tag> tagList = new ArrayList<>();

    @Transient
    private List<String> tags = new ArrayList<>();

    public Club(String name, String owner, String telePhoneNumber, String cellPhoneNumber, String address , String ownerUserName , Double latitude, Double longtitude){
        this.name = name;
        this.owner = owner;
        this.telePhoneNumber = telePhoneNumber;
        this.cellPhoneNumber = cellPhoneNumber;
        this.address = address;
        this.ownerUserName = ownerUserName;
        this.latitude = latitude;
        this.longtitude = longtitude;

    }
    public Club(){
    }

    public String getOwnerUserName() {
        return ownerUserName;
    }

    public void setLatitude(Double latitude){
        this.latitude = latitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLongtitude(Double longtitude){
        this.longtitude = longtitude;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTelePhoneNumber() {
        return telePhoneNumber;
    }

    public void setTelePhoneNumber(String telePhoneNumber) {
        this.telePhoneNumber = telePhoneNumber;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public List<Plan> getWeeklyPlan() {
        return weeklyPlan;
    }

    public void setWeeklyPlan(List<Plan> weeklyPlan) {
        this.weeklyPlan = weeklyPlan;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }

}