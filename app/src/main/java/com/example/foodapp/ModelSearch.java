package com.example.foodapp;

import java.io.IOException;
import java.util.ArrayList;

public class ModelSearch {
    public ArrayList<RecipeHolder> getJsonRequest(String urlQueryStr) {
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
