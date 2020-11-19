package com.example.foodapp;

import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class JSONRecipeThreader extends Thread{
    private JSONObject recipe;
    RecipeHolder recipeHold;
    public JSONRecipeThreader(JSONObject recipe) {
        this.recipe = recipe;
        recipeHold = new RecipeHolder();
    }

    @Override
    public void run() {
        try {
            //System.out.println(recipe);
            recipeHold.setName(recipe.getString("label"));
            recipeHold.setImageurl(recipe.getString("image"));
            //System.out.println(recipe.getString("bookmarked"));
        }
        catch (Exception e) {
            System.out.println("Error in recipethreader");
            e.printStackTrace();
        }

    }

    //Holds the individual recipes
    public RecipeHolder getRecipeHolder() {
        return recipeHold;
    }
}
