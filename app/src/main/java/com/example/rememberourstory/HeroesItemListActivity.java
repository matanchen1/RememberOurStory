package com.example.rememberourstory;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class HeroesItemListActivity extends AppCompatActivity {
    private Button returnBtn;
    private ImageView heroImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heros_item_list);

        returnBtn = findViewById(R.id.returBtn);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HeroesItemListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        heroImage = findViewById(R.id.heroImage);
        heroImage.bringToFront();
        heroImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HeroesItemListActivity.this, StoryActivity.class);
                startActivity(intent);
            }

        });

    }
}