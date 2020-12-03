package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void search(View view) {

        SearchModifiers mods = new SearchModifiers();
        mods.ingredients = ((TextView)this.findViewById(R.id.editTextIngred)).getText().toString();
        mods.NiR = ((TextView)this.findViewById(R.id.editTextNiR)).getText().toString();

        mods.treenutfree = ((CheckBox)this.findViewById(R.id.cbTreeNut)).isChecked();
        mods.peanutfree = ((CheckBox)this.findViewById(R.id.cbPeanutFree)).isChecked();
        mods.vegetarian = ((CheckBox)this.findViewById(R.id.cbVegetarian)).isChecked();
        mods.vegan = ((CheckBox)this.findViewById(R.id.cbVegan)).isChecked();
        mods.alcoholfree = ((CheckBox)this.findViewById(R.id.cbAlcoholFree)).isChecked();



        if(TextUtils.isEmpty(mods.ingredients)) {
            Toast.makeText(this, "Need to enter an ingredient", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this, SearchActivity.class);
            //intent.putExtra("ingred", mods.ingredients);
            //intent.putExtra("NiR", mods.NiR);
            intent.putExtra("modifiers", (Parcelable) mods);
            this.startActivityForResult(intent, 0);
        }


    }
}