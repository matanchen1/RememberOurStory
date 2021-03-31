package com.example.rememberourstory;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ItemListActivity extends AppCompatActivity {
    private ImageView heroImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heros_item_list);
        heroImage = findViewById(R.id.image2    );
//        heroImage.bringToFront();
        heroImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("== My activity ===","OnClick is called");
                Toast.makeText(v.getContext(), // <- Line changed
                        "The favorite list would appear on clicking this icon",
                        Toast.LENGTH_LONG).show();
                System.out.println("121221");
            }

        });

    }


}
