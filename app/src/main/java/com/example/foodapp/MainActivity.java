package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
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

        if(TextUtils.isEmpty(mods.ingredients)) {
            Toast.makeText(this, "Need to enter an ingredient", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this, SearchActivity.class);
            intent.putExtra("ingred", mods.ingredients);
            intent.putExtra("NiR", mods.NiR);
            this.startActivityForResult(intent, 0);
        }


    }
}