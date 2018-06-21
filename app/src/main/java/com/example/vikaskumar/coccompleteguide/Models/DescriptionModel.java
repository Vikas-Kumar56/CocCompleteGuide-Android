package com.example.vikaskumar.coccompleteguide.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DescriptionModel {

    @SerializedName("Name")
    private String name;

    @SerializedName("SpecialFeature")
    private String specialFeature;

    @SerializedName("AntiTroopies")
    private List<String> antiTroopies;

    @SerializedName("VideoUrl")
    private String VideoUrl;

    @SerializedName("Description")
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
