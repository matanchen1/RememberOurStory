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
    private final static String INSTRUCTIONS_STEP_2 ="הוראות לשלב 2";
    private final static String INSTRUCTIONS_STEP_3 ="הוראות לשלב 3";

    private final static String HEADLINE_2 ="כותרת שלב 2";
    private final static String HEADLINE_3 ="כותרת שלב 3";





    private Button shareToStoryBtn;
    private Dialog popUpDialog;
    private int i = 0;
    private ImageView backBtn;
    private TextView headLine;
    private TextView instructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        initVars();

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

    private void initVars() {
        popUpDialog = new Dialog(this);
        shareToStoryBtn = findViewById(R.id.openInstagramBtn);
        headLine = findViewById(R.id.headline);
        instructions = findViewById(R.id.location_text);

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
        closeBtn = (Button) popUpDialog.findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpDialog.dismiss();
               switch (++i){
                   case 3:
                        Intent intent = new Intent(StoryActivity.this,EndActivity.class);
                        startActivity(intent);
                       break;
                   case 1:
                    headLine.setText(HEADLINE_2);
                    instructions.setText(INSTRUCTIONS_STEP_2);
                    break;
                   case 2:
                   default:
                       headLine.setText(HEADLINE_3);
                       instructions.setText(INSTRUCTIONS_STEP_3);
                       break;
               }
            }
        });

        popUpDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLUE));
        popUpDialog.show();
    }


}