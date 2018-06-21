package com.example.vikaskumar.coccompleteguide.Models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class BaseDesignModel {

    @SerializedName("Id")
    private int mapId;

    @SerializedName("townhallId")
    private int townhallId;

    @SerializedName("typeId")
    private int typeId;

    @SerializedName("url")
    private String url;

    @SerializedName("favouriteCount")
    private int favouriteCount;

    @SerializedName("isTrending")
    private boolean isTrending;

    @SerializedName("createdDate")
    private Date createdDate;

    @SerializedName("updatedDate")
    private Date updatedDate;

    //@SerializedName("description")
    //private DescriptionModel description;

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

    /*public DescriptionModel getDescription() {
        return description;
    }

    public void setDescription(DescriptionModel description) {
        this.description = description;
    }*/

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
