package com.example.rememberourstory;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class StoryActivity extends AppCompatActivity {
    private Button shareToStoryBtn;
    private Dialog popUpDialog;
    private ImageView backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        popUpDialog = new Dialog(this);
        shareToStoryBtn = findViewById(R.id.openInstagramBtn);
        shareToStoryBtn.setOnClickListener(v -> {
            openInstagram();
            managePopUp(v);
        });
        backBtn = findViewById(R.id.back_button);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoryActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void openInstagram() {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");
        if (launchIntent != null) {
            startActivity(launchIntent);
        } else {
            Toast.makeText(this, "Oops! You don't have Instagram App.", Toast.LENGTH_LONG).show();
            System.out.println( "Oops! You don't have Instagram App.");
        }

    }
    public void managePopUp(View v) {
        TextView txtClose;
        Button closeBtn;
        popUpDialog.setContentView(R.layout.pop_up);
        txtClose =(TextView) popUpDialog.findViewById(R.id.txtclose);
        txtClose.setText("X");
        closeBtn = (Button) popUpDialog.findViewById(R.id.closeBtn);
        txtClose.setOnClickListener(v1 -> popUpDialog.dismiss());
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpDialog.dismiss();

            }
        });

        popUpDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLUE));
        popUpDialog.show();
    }


}