package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.HttpUrl;

public class SearchActivity extends AppCompatActivity {

    public SearchModifiers mods;
    public ArrayList<RecipeHolder> recipeBox;
    public ArrayList<ImageView> ivBox = new ArrayList<ImageView>();
    public ArrayList<TextView> tvBox = new ArrayList<TextView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent intent = getIntent();

        mods = new SearchModifiers();
        mods.ingredients = intent.getStringExtra("ingred");
        mods.NiR = intent.getStringExtra("NiR");

        try {
            search();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void search() throws IOException {
        //delimit the spaces
        String ingred = mods.ingredients;
        String[] ingredList = ingred.split(" ");
        String prepIngred = "";
        for(int i = 0; i < ingredList.length; i++) {
            prepIngred += ingredList[i] + " ";
        }

        String ingredNiR = mods.NiR;
        String[] NiRList = ingredNiR.split(" ");
        String prepNiR = "";
        for(int i = 0; i < NiRList.length; i++) {
            prepNiR += "excluded=" + NiRList[i] + "&";
        }
        prepNiR = prepNiR.substring(0, prepNiR.length() - 1); //remove last & for better formatting in strURL

        //Create the url to get json from
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.EDMAM_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter("app_id", Constants.EDMAM_ID);
        urlBuilder.addQueryParameter("app_key", Constants.EDMAM_KEY);
        urlBuilder.addQueryParameter("to", "4");
        urlBuilder.addQueryParameter("q", prepIngred);

        String urlQueryStr = urlBuilder.build().toString();

        getJsonRequest(urlQueryStr); //read and get recipes back from json

    }

    public void getJsonRequest(String urlQueryStr) throws IOException{
        TextView text = this.findViewById(R.id.textView1);
        JSONSearchThread JSONSearchThread = new JSONSearchThread(urlQueryStr);
        Thread getJsonTh = new Thread(JSONSearchThread);
        try{
            getJsonTh.start(); //Starts mass threads processing the json string
            getJsonTh.join();
            recipeBox = JSONSearchThread.getRecipes();
            System.out.println(recipeBox);
            display();
        }
        catch (Exception InterruptedException) {
            System.out.println("InterruptedException");
        }
    }

    public void display() {
        ivBox.add((ImageView) this.findViewById(R.id.imageView1));
        ivBox.add((ImageView) this.findViewById(R.id.imageView2));
        ivBox.add((ImageView) this.findViewById(R.id.imageView3));
        ivBox.add((ImageView) this.findViewById(R.id.imageView4));

        tvBox.add((TextView) this.findViewById(R.id.textView1));
        tvBox.add((TextView) this.findViewById(R.id.textView2));
        tvBox.add((TextView) this.findViewById(R.id.textView3));
        tvBox.add((TextView) this.findViewById(R.id.textView4));

        for(int i = 0; i < ivBox.size(); i++)
        {
            Picasso.get().load(recipeBox.get(i).getImageurl()).into(ivBox.get(i));
            tvBox.get(i).setText(recipeBox.get(i).getName());
        }
    }
}