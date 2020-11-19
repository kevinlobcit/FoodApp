package com.example.foodapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONThreader extends Thread{

    private ArrayList<RecipeHolder> recipeBox;

    private JSONObject jsonObject;
    private JSONArray jsonHits;
    public JSONThreader(String jsonStr) throws JSONException {
        recipeBox = new ArrayList<RecipeHolder>();
        jsonObject = new JSONObject(jsonStr);
        jsonHits = jsonObject.getJSONArray("hits");
    }

    @Override
    public void run() {
        //make more threads for each recipe taken from jsonHits into JSONObjects
        try {
            //take the objects and process them
            //jsonHits contains all the recipes
            //Split jsonhits into j

            System.out.println("Starting recipethread1");
            JSONObject jsonHitRecipe1 = jsonHits.getJSONObject(0);
            JSONObject jsonRecipe1 = jsonHitRecipe1.getJSONObject("recipe");
            //System.out.println(jsonHitRecipe1);
            //System.out.println(jsonRecipe1);

            System.out.println("Starting recipethread2");
            JSONObject jsonHitRecipe2 = jsonHits.getJSONObject(1);
            JSONObject jsonRecipe2 = jsonHitRecipe2.getJSONObject("recipe");
            //System.out.println(jsonHitRecipe2);
            //System.out.println(jsonRecipe2);

            System.out.println("Starting recipethread3");
            JSONObject jsonHitRecipe3 = jsonHits.getJSONObject(2);
            JSONObject jsonRecipe3 = jsonHitRecipe3.getJSONObject("recipe");
            //System.out.println(jsonHitRecipe3);
            //System.out.println(jsonRecipe3);

            System.out.println("Starting recipethread4");
            JSONObject jsonHitRecipe4 = jsonHits.getJSONObject(3);
            JSONObject jsonRecipe4 = jsonHitRecipe4.getJSONObject("recipe");
            //System.out.println(jsonHitRecipe4);
            //System.out.println(jsonRecipe4);

            JSONRecipeThreader jsonRecipeTH1 = new JSONRecipeThreader(jsonRecipe1);
            Thread thread1 = new Thread(jsonRecipeTH1);
            thread1.start();

            JSONRecipeThreader jsonRecipeTH2 = new JSONRecipeThreader(jsonRecipe2);
            Thread thread2 = new Thread(jsonRecipeTH2);
            thread2.start();

            JSONRecipeThreader jsonRecipeTH3 = new JSONRecipeThreader(jsonRecipe3);
            Thread thread3 = new Thread(jsonRecipeTH3);
            thread3.start();

            JSONRecipeThreader jsonRecipeTH4 = new JSONRecipeThreader(jsonRecipe4);
            Thread thread4 = new Thread(jsonRecipeTH4);
            thread4.start();

            try {
                thread1.join();
                thread2.join();
                thread3.join();
                thread4.join();
                recipeBox.add(jsonRecipeTH1.getRecipeHolder());
                recipeBox.add(jsonRecipeTH2.getRecipeHolder());
                recipeBox.add(jsonRecipeTH3.getRecipeHolder());
                recipeBox.add(jsonRecipeTH4.getRecipeHolder());
            }
            catch (Exception e) {
                e.printStackTrace();
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<RecipeHolder> getRecipeBox() {
        return recipeBox;
    }
}
