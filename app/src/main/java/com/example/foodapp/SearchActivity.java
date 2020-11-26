package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
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
    private TextView tvFromTo;
    private int currentFrom = 0;
    private int currentTo = 4;



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
        tvFromTo = findViewById(R.id.tvCurrent);
        tvFromTo.setText("1/4");
    }

    public String createUrl() {
        //delimit the spaces
        //String ingred = mods.ingredients;
        /*
        String[] ingredList = ingred.split(" ");
        String prepIngred = "";
        for(int i = 0; i < ingredList.length; i++) {
            prepIngred += ingredList[i] + " ";
        }*/
        //Create the url to get json from
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.EDMAM_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter("app_id", Constants.EDMAM_ID);
        urlBuilder.addQueryParameter("app_key", Constants.EDMAM_KEY);
        urlBuilder.addQueryParameter("from", String.valueOf(currentFrom));
        urlBuilder.addQueryParameter("to", String.valueOf(currentTo));
        urlBuilder.addQueryParameter("q", mods.ingredients);

        String ingredNiR = mods.NiR;
        String[] NiRList = ingredNiR.split(" ");
        for(int i = 0; i < NiRList.length; i++) {
            //prepNiR += "excluded=" + NiRList[i] + "&";
            urlBuilder.addQueryParameter("excluded", NiRList[i]);
        }

        return urlBuilder.build().toString();
    }

    public void search() throws IOException {
        String urlQueryStr = createUrl();
        getJsonRequest(urlQueryStr); //read and get recipes back from json
    }

    //need to update to get a more efficient way of getting json instead of spamming
    //the json server to get the next recipes
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

    public void onClickLeftRight(View view) throws IOException {
        switch (view.getId()) {
            case R.id.btnPrev:
                currentFrom -= 4;
                currentTo -= 4;
                break;
            case R.id.btnNext:
                currentFrom += 4;
                currentTo += 4;
                break;
        }
        int displayFrom = currentFrom + 1;
        tvFromTo.setText(Integer.toString(displayFrom) + "/" + Integer.toString(currentTo));
        search();
    }

    public void onClickRecipe(View view) {
        RecipeHolder selected = recipeBox.get(0);
        switch (view.getId()) {
            case R.id.imageView1:
                selected = recipeBox.get(0);
                break;
            case R.id.imageView2:
                selected = recipeBox.get(1);
                break;
            case R.id.imageView3:
                selected = recipeBox.get(2);
                break;
            case R.id.imageView4:
                selected = recipeBox.get(3);
                break;
        }

        Intent intent = new Intent(this, ViewRecipeActivity.class);
        intent.putExtra("recipe", (Parcelable) selected);
        this.startActivityForResult(intent, 0);

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