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

public class SearchActivity extends AppCompatActivity implements ViewSearch{

    public SearchModifiers mods;
    private TextView tvFromTo;
    private int currentFrom = 0;
    private int currentTo = 4;

    PresenterSearch presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent intent = getIntent();

        mods = new SearchModifiers();
        mods.ingredients = intent.getStringExtra("ingred");
        mods.NiR = intent.getStringExtra("NiR");

        //MVP
        ModelSearch model = new ModelSearch();
        presenter = new PresenterSearch(model);
        presenter.bind(this);
        presenter.setFromTo(currentFrom, currentTo);
        presenter.ready(mods);


        try {
            //search();
            presenter.search();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tvFromTo = findViewById(R.id.tvCurrent);
        tvFromTo.setText("1/4");
    }

    public void onClickLeftRight(View view) throws IOException {
        boolean dontMove = false;
        int id = view.getId();
        if (id == R.id.btnPrev) {
            currentFrom -= 4;
            currentTo -= 4;
        } else if (id == R.id.btnNext) {
            currentFrom += 4;
            currentTo += 4;
        }

        if(currentFrom < 0) {
            currentFrom += 4;
            currentTo += 4;
            dontMove = true;
        }

        if(!dontMove) {
            int displayFrom = currentFrom + 1;
            String newtext = Integer.toString(displayFrom) + "/" + Integer.toString(currentTo);
            tvFromTo.setText(newtext);

            presenter.setFromTo(currentFrom, currentTo);
            presenter.search();
        }
    }

    public void onClickRecipe(View view) {
        //RecipeHolder selected = recipeBox.get(0);
        RecipeHolder selected = presenter.getRecipe(0);
        int id = view.getId();
        if (id == R.id.imageView1) {
            selected = presenter.getRecipe(0);
        } else if (id == R.id.imageView2) {
            selected = presenter.getRecipe(1);
        } else if (id == R.id.imageView3) {
            selected = presenter.getRecipe(2);
        } else if (id == R.id.imageView4) {
            selected = presenter.getRecipe(3);
        }

        Intent intent = new Intent(this, ViewRecipeActivity.class);
        intent.putExtra("recipe", (Parcelable) selected);
        this.startActivityForResult(intent, 0);
    }
}