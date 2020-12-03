package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewRecipeActivity extends AppCompatActivity {

    ImageView ivReciImage;
    TextView tvReciName;
    TextView tvCookTime;
    TextView tvSourceName;

    String sourceURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        ivReciImage = this.findViewById(R.id.ivRecipImage);
        tvReciName = this.findViewById(R.id.tvRecipName);
        tvCookTime = this.findViewById(R.id.tvCookTime);
        tvSourceName = this.findViewById(R.id.tvSourceName);

        Intent intent = getIntent();
        RecipeHolder recipe = intent.getParcelableExtra("recipe");
        String strCookTime = "Cooking Time: " + Double.toString(recipe.getCookTime());
        String strSourceName = "Source: " + recipe.getRecipeSource();

        Picasso.get().load(recipe.getImageurl()).into(ivReciImage);
        tvReciName.setText(recipe.getName());
        tvCookTime.setText(strCookTime);
        tvSourceName.setText(strSourceName);
        sourceURL = recipe.getUrl();
    }

    public void viewSource(View view) {
        System.out.println("stuff");
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(sourceURL));
        startActivity(browserIntent);
    }


}