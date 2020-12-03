package com.example.foodapp;

import org.json.JSONObject;

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
            recipeHold.setIngredients(recipe.getJSONArray("ingredientLines"));
            recipeHold.setCookTime(recipe.getDouble("totalTime"));
            recipeHold.setRecipeSource(recipe.getString("source"));
            recipeHold.setUrl(recipe.getString("url"));
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
