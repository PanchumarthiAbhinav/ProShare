package com.example.proshare;

public class videomodel {

    String reelurl, title;

    public videomodel(String reelurl, String title) {
        this.reelurl = reelurl;
        this.title = title;
    }

    public videomodel() {
    }

    public String getReelurl() {
        return reelurl;
    }

    public void setReelurl(String reelurl) {
        this.reelurl = reelurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
