package com.example.foodapp;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;

public class RecipeHolder implements Parcelable {
    private String name;
    private String imageurl;
    private JSONArray ingredients;
    private double cookTime;
    private String recipeSource;
    private String url;

    public RecipeHolder() {

    }

    protected RecipeHolder(Parcel in) {
        try {
            name = in.readString();
            imageurl = in.readString();
            ingredients = new JSONArray(in.readString());
            cookTime = in.readDouble();
            recipeSource = in.readString();
            url = in.readString();
        }
        catch (Exception e) {
            System.out.println("Error at parcel");
        }
    }

    public static final Creator<RecipeHolder> CREATOR = new Creator<RecipeHolder>() {
        @Override
        public RecipeHolder createFromParcel(Parcel in) {
            return new RecipeHolder(in);
        }

        @Override
        public RecipeHolder[] newArray(int size) {
            return new RecipeHolder[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public JSONArray getIngredients() {
        return ingredients;
    }

    public void setIngredients(JSONArray ingredients) {
        this.ingredients = ingredients;
    }

    public double getCookTime() {
        return cookTime;
    }

    public void setCookTime(double cookTime) {
        this.cookTime = cookTime;
    }

    public String getRecipeSource() {
        return recipeSource;
    }

    public void setRecipeSource(String recipeSource) {
        this.recipeSource = recipeSource;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    /*
    private String name;
    private String imageurl;
    private JSONArray ingredients;
    private double cookTime;
    private String recipeSource;
    private String url;
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.imageurl);
        dest.writeString(this.ingredients.toString());
        dest.writeDouble(this.cookTime);
        dest.writeString(this.recipeSource);
        dest.writeString(this.url);
    }
}
