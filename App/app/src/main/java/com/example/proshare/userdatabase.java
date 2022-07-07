package com.example.proshare;

public class userdatabase {

    public String category, description, imageurl, title;
    public userdatabase() {
    }

    public userdatabase(String category, String description, String imageurl, String title) {
        this.category = category;
        this.description = description;
        this.imageurl = imageurl;
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
