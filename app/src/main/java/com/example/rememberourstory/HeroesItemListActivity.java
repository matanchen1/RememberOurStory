package com.example.rememberourstory;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.ImageView;

public class HeroesItemListActivity extends AppCompatActivity {
    private Button returnBtn;
    private ImageView malkaHeroImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heros_item_list);
        initVars();
        setOnClick();
    }

    private void setOnClick() {
        returnBtn.setOnClickListener(v -> {
            Intent intent = new Intent(HeroesItemListActivity.this, MainActivity.class);
            startActivity(intent);
        });
        malkaHeroImage.setOnClickListener(v -> {
            Intent intent = new Intent(HeroesItemListActivity.this, StoryActivity.class);
            startActivity(intent);
        });
    }

    private void initVars() {
        returnBtn = findViewById(R.id.returBtn);
        malkaHeroImage = findViewById(R.id.heroImage);
        malkaHeroImage.bringToFront();
    }
}