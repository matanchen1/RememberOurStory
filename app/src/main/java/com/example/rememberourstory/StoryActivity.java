package com.example.rememberourstory;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;

public class StoryActivity extends AppCompatActivity {

    private final static String INSTRUCTIONS_STEP_2 = "הסיפור של מלכה רוזנטל - שלב 2";
    private final static String INSTRUCTIONS_STEP_3 = "הוראות לשלב 3";

    private final static String HEADLINE_2 = "הסיפור של מלכה רוזנטל - שלב 2";
    private final static String HEADLINE_3 = "כותרת שלב 3";

    private ArrayList<String> instructionsArr;
    private ArrayList<String> headLines;


    private int curVideoRes;
    private int StepIndex = 0;
    private ArrayList<Integer> videos;
    private ImageView shareToStoryBtn;
    private Dialog popUpDialog;
    private ImageView backBtn;
    private TextView headLine;
    private TextView instruction;
    private VideoView videoView;
    private Uri videoUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        initVars();


        shareToStoryBtn.setOnClickListener(v -> {
            openInstagram();
            manageScreenRefresh(v);
        });
        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(StoryActivity.this, MainActivity.class);
            startActivity(intent);
        });
        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.setVideoURI(videoUri);
                videoView.start();
            }
        });



    }


    private void initVars() {
        popUpDialog = new Dialog(this);
        shareToStoryBtn = findViewById(R.id.story_share);
        headLine = findViewById(R.id.headline);
        instruction = findViewById(R.id.location_text);
        backBtn = findViewById(R.id.back_button);
        videoView = findViewById(R.id.videoView);
        videos = new ArrayList<>();
        videos.add(R.raw.video1);
        videos.add(R.raw.video1);

        curVideoRes = R.raw.video1;
        videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + curVideoRes);


    }

    private void manageScreenRefresh(View v) {
        if (++StepIndex == 3) {
            Intent endIntent = new Intent(StoryActivity.this, EndActivity.class);
            startActivity(endIntent);
            return;
        } else {

            managePopUp(v);
        }
    }


    private void openInstagram() {
//        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");
//        if (launchIntent != null) {
//            startActivity(launchIntent);
//        } else {
//            Toast.makeText(this, "Oops! You don't have Instagram App.", Toast.LENGTH_LONG).show();
//            System.out.println("Oops! You don't have Instagram App.");
//            StepIndex = 0;
//        }
        ShareProcess.share(R.raw.video1,ShareProcess.VIDEO,this);

    }

    public void managePopUp(View v) {
        Button closeBtn;
        popUpDialog.setContentView(R.layout.pop_up);
        closeBtn = (Button) popUpDialog.findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpDialog.dismiss();
                switch (StepIndex) {
                    case 1:
                        headLine.setText(HEADLINE_2);
                        instruction.setText(INSTRUCTIONS_STEP_2);
                        videoUri = Uri.parse("android.resource://" + getPackageName()
                                + "/" + videos.get(StepIndex));
                        videoView.setVideoURI(videoUri);
                        videoView.setVideoURI(videoUri);
                        break;
                    case 2:
                    default:
                        headLine.setText(HEADLINE_3);
                        instruction.setText(INSTRUCTIONS_STEP_3);
                        break;
                }
            }
        });

        popUpDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLUE));
        popUpDialog.show();
    }


}