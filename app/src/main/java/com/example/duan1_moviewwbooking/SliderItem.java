package com.example.duan1_moviewwbooking;

public class SliderItem {
    private int image;
    private String  url;

    public SliderItem(int image, String url) {
        this.image = image;
        this.url = url;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getImage() {
        return image;
    }
}
