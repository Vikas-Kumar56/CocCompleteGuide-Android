package com.example.vikaskumar.coccompleteguide.Models;

import java.util.List;

public class DescriptionModel {
    private String name;
    private String specialFeature;
    private List<String> antiTroopies;
    private String VideoUrl;
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
