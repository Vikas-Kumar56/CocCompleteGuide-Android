package com.example.vikaskumar.coccompleteguide.Models;

import java.util.Date;
import java.util.List;

public class BaseDesignModel {
    private int mapId;
    private int townhallId;
    private int typeId;
    private String url;
    private String description;
    private int favouriteCount;
    private boolean isTrending;
    private Date createdDate;
    private Date updatedDate;

    private String name;
    private String specialFeature;
    private List<String> antiTroopies;
    private String VideoUrl;
 public BaseDesignModel(){

 }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialFeature() {
        return specialFeature;
    }

    public void setSpecialFeature(String specialFeature) {
        this.specialFeature = specialFeature;
    }

    public List<String> getAntiTroopies() {
        return antiTroopies;
    }

    public void setAntiTroopies(List<String> antiTroopies) {
        this.antiTroopies = antiTroopies;
    }

    public String getVideoUrl() {
        return VideoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        VideoUrl = videoUrl;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public int getTownhallId() {
        return townhallId;
    }

    public void setTownhallId(int townhallId) {
        this.townhallId = townhallId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFavouriteCount() {
        return favouriteCount;
    }

    public void setFavouriteCount(int favouriteCount) {
        this.favouriteCount = favouriteCount;
    }

    public boolean isTrending() {
        return isTrending;
    }

    public void setTrending(boolean trending) {
        isTrending = trending;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }


}
