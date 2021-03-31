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
import java.util.Timer;
import java.util.TimerTask;

public class StoryActivity extends AppCompatActivity {

    private final static String INSTRUCTIONS_STEP_2 = "הסיפור של מלכה רוזנטל - שלב 2";
    private final static String INSTRUCTIONS_STEP_3 = "הוראות לשלב 3";
    private final static String INSTRUCTIONS_STEP_4 = "הוראות לשלב 4";

    private final static String HEADLINE_2 = "הסיפור של מלכה רוזנטל - שלב 2";
    private final static String HEADLINE_3 = "כותרת שלב 3";
    private final static String HEADLINE_4 = "כותרת שלב 43";
    private static final int FINAL_SCREEN = 4;

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
        initArr();


        curVideoRes = R.raw.video1;
        videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + videos.get(0));


    }

    private void initArr() {
        videos = new ArrayList<>();
        videos.add(R.raw.malka4);
        videos.add(R.raw.malka5);
        videos.add(R.raw.malka6);
        videos.add(R.raw.malka7);


        instructionsArr = new ArrayList<>();
        instructionsArr.add(INSTRUCTIONS_STEP_2);
        instructionsArr.add(INSTRUCTIONS_STEP_3);
        instructionsArr.add(INSTRUCTIONS_STEP_4);

        headLines = new ArrayList<>();
        headLines.add(HEADLINE_2);
        headLines.add(HEADLINE_3);
        headLines.add(HEADLINE_4);
    }


    private void manageScreenRefresh(View v) {
        if (++StepIndex == FINAL_SCREEN) {
            openInstagram();
            Intent endIntent = new Intent(StoryActivity.this, EndActivity.class);
            startActivity(endIntent);
        } else {
            TimerTask timerTaskObj = new TimerTask() {
                public void run() {
                }
            };

            Timer t = new Timer();
            t.schedule(timerTaskObj, 2000L);
            managePopUp(v);
        }

    }


    private void openInstagram() {

        ShareProcess.share(videos.get(StepIndex), ShareProcess.VIDEO, this);

    }

    public void managePopUp(View v) {

        Button closeBtn;

        popUpDialog.setContentView(R.layout.pop_up);
        closeBtn = (Button) popUpDialog.findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpDialog.dismiss();
                updateScreen();
            }
        });

        popUpDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLUE));
        popUpDialog.show();
    }

    private void updateScreen() {
        headLine.setText(headLines.get(StepIndex-1));
        instruction.setText(headLines.get(StepIndex-1));
        //update video
        if (StepIndex<4) {
            videoUri = Uri.parse("android.resource://" + getPackageName()
                    + "/" + videos.get(StepIndex));
            videoView.setVideoURI(videoUri);
            videoView.start();
        }
    }


}