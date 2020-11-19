package com.example.foodapp;

public class RecipeHolder {
    private String name;
    private String imageurl;
    public RecipeHolder() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getName()
    {
        return name;
    }

    public String getImageurl() {
        return imageurl;
    }
}
