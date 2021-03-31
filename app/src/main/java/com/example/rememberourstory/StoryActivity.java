package com.example.rememberourstory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class StoryActivity extends AppCompatActivity {
    Button shareToStoryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        shareToStoryBtn = findViewById(R.id.letsVolunteer);
        shareToStoryBtn.setOnClickListener(v -> {
            openInstagram();
        });
    }
    private void openInstagram() {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");
        if (launchIntent != null) {
            startActivity(launchIntent);
        } else {
            Toast.makeText(this, "Oops! You don't have Instagram App.", Toast.LENGTH_LONG).show();
        }
    }


}