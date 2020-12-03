package com.example.foodapp;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

//"Main" thread for doing everything
//Thread used to use the url to get the search results and get back the entire json object
public class JSONSearchThread extends Thread{
    private String urlQueryStr;
    private String jsonStr;
    private ArrayList<RecipeHolder> recipeBox;

    public JSONSearchThread(String urlQueryStr) {
        this.urlQueryStr = urlQueryStr;
        jsonStr = "";
    }

    @Override
    public void run() {
        //Get json from over the internet
        try {
            URL url = new URL(urlQueryStr);
            System.out.println("URL=" + url);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int responsecode = con.getResponseCode();
            System.out.println(responsecode);

            if(responsecode != 200)
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            else {
                //String jsonStr = "";
                Scanner scan = new Scanner(url.openStream());
                while(scan.hasNext()) {
                    jsonStr += scan.nextLine();
                }
                scan.close();
            }
            con.disconnect();

            //Start the subthreads which will process the json into recipe json
            System.out.println("Starting Subthreads");
            subthreads();//launch sub threads

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void subthreads() {
        try{
            JSONThreader jsonThreader = new JSONThreader(jsonStr);
            Thread jsonThread = new Thread(jsonThreader);
            System.out.println("starting subthread1");
            jsonThread.start();
            jsonThread.join();

            recipeBox = jsonThreader.getRecipeBox();
            System.out.println("Recipes saved to box");
        }
        catch(Exception e) {
            System.out.println("Error at subthreads");
        }

    }

    public ArrayList<RecipeHolder> getRecipes() {
        return recipeBox;
    }
}

