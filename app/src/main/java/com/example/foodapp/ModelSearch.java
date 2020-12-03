package com.example.foodapp;

import java.io.IOException;
import java.util.ArrayList;

public class ModelSearch {
    //need to update to get a more efficient way of getting json instead of spamming
    //the json server to get the next recipes
    public ArrayList<RecipeHolder> getJsonRequest(String urlQueryStr) throws IOException {
        JSONSearchThread JSONSearchThread = new JSONSearchThread(urlQueryStr);
        Thread getJsonTh = new Thread(JSONSearchThread);
        try{
            getJsonTh.start(); //Starts mass threads processing the json string
            getJsonTh.join();
            return JSONSearchThread.getRecipes();

        }
        catch (Exception InterruptedException) {
            System.out.println("InterruptedException");
        }
        return null;
    }
}
