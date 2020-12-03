package com.example.foodapp;

import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.HttpUrl;

public class PresenterSearch {
    SearchActivity view;
    ModelSearch model;

    public SearchModifiers mods;
    public ArrayList<RecipeHolder> recipeBox;
    public ArrayList<ImageView> ivBox = new ArrayList<>();
    public ArrayList<TextView> tvBox = new ArrayList<>();

    int currentFrom;
    int currentTo;

    public PresenterSearch(ModelSearch model) {
        this.model = model;
    }

    public void bind(SearchActivity view) {
        this.view = view;
    }

    public void ready(SearchModifiers mods) {
        ivBox.add((ImageView) view.findViewById(R.id.imageView2));
        ivBox.add((ImageView) view.findViewById(R.id.imageView3));
        ivBox.add((ImageView) view.findViewById(R.id.imageView4));
        ivBox.add((ImageView) view.findViewById(R.id.imageView1));

        tvBox.add((TextView) view.findViewById(R.id.textView1));
        tvBox.add((TextView) view.findViewById(R.id.textView2));
        tvBox.add((TextView) view.findViewById(R.id.textView3));
        tvBox.add((TextView) view.findViewById(R.id.textView4));

        this.mods = mods;
    }

    public void setFromTo(int from, int to) {
        currentFrom = from;
        currentTo = to;
    }

    public String createUrl() {
        //Create the url to get json from
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.EDMAM_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter("app_id", Constants.EDMAM_ID);
        urlBuilder.addQueryParameter("app_key", Constants.EDMAM_KEY);
        urlBuilder.addQueryParameter("from", String.valueOf(currentFrom));
        urlBuilder.addQueryParameter("to", String.valueOf(currentTo));
        urlBuilder.addQueryParameter("q", mods.ingredients);

        if(mods.treenutfree == true)
            urlBuilder.addQueryParameter("health", "tree-nut-free");
        if(mods.peanutfree == true)
            urlBuilder.addQueryParameter("health", "peanut-free");
        if(mods.vegetarian == true)
            urlBuilder.addQueryParameter("health", "vegetarian");
        if(mods.vegan == true)
            urlBuilder.addQueryParameter("health", "vegan");
        if(mods.alcoholfree == true)
            urlBuilder.addQueryParameter("health", "alcohol-free");


        String ingredNiR = mods.NiR;
        String[] NiRList = ingredNiR.split(" ");
        for (String s : NiRList) {
            //prepNiR += "excluded=" + NiRList[i] + "&";
            urlBuilder.addQueryParameter("excluded", s);
        }

        return urlBuilder.build().toString();
    }

    public void display() {
        for(int i = 0; i < ivBox.size(); i++)
        {
            Picasso.get().load(recipeBox.get(i).getImageurl()).into(ivBox.get(i));
            tvBox.get(i).setText(recipeBox.get(i).getName());
        }
    }

    public void search() throws IOException {
        String urlQueryStr = createUrl();
        recipeBox = model.getJsonRequest(urlQueryStr); //read and get recipes back from json
        display(); //update the pictures
    }

    public RecipeHolder getRecipe(int recipNum) {
        return recipeBox.get(recipNum);
    }


}
