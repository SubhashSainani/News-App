package com.example.newsapp.models;

import java.io.Serializable;

public class News implements Serializable {
    private String id;
    private String title;
    private String description;
    private String imageUrl;
    private String category;
    private String date;
    private boolean isTopStory;

    public News(String id, String title, String description, String imageUrl, String category, String date, boolean isTopStory) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
        this.date = date;
        this.isTopStory = isTopStory;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isTopStory() {
        return isTopStory;
    }

    public void setTopStory(boolean topStory) {
        isTopStory = topStory;
    }
}