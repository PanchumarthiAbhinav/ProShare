package com.example.proshare;

public class filemodel {

    String title, reelurl;

    public filemodel() {
    }

    public filemodel(String title, String reelurl) {
        this.title = title;
        this.reelurl = reelurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReelurl() {
        return reelurl;
    }

    public void setReelurl(String reelurl) {
        this.reelurl = reelurl;
    }
}
