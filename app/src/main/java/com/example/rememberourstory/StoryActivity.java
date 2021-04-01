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
import java.util.concurrent.TimeUnit;

public class StoryActivity extends AppCompatActivity {

    private final static String INSTRUCTIONS_STEP_1 =
            " לחצו על כפתור השיתוף, שתפו את הסרטון וחזרו לאחר מכן לאפליקציה.\n מוזמנים להוסיף תיבת טקסט עם כיתוב \"סיפורה של מלכה רוזנטל ז\"ל\" \n";

    private final static String INSTRUCTIONS_STEP_2 = "עכשיו בואו נאתגר את העוקבים שלכם! " + "\n" +
            "שתפו את התמונה והוסיפו חידון ומקמו אותו מעל החידון המופיע בתמונה";
    private final static String INSTRUCTIONS_STEP_3 = " כעת נגלה מה הייתה תגובתה המיוחדת של אמה של מלכה." + "\n" +
            " שתפו את הסרטון הבא";
    private final static String INSTRUCTIONS_STEP_4 = "נמשיך בהעלאת סרטון נוסף";
    private final static String INSTRUCTIONS_STEP_5 = "הגענו לסרטון הסיום, שתפו אותו ואז תוכלו להיכנס לעמוד האינסטגרם שלכם ולצפות בסטורי המלא שבניתם. לאחר מכן חזרו למסך האפליקציה";

    private final static String HEADLINE_1 = "הסיפור של מלכה רוזנטל ז\"ל - 1";
    private final static String HEADLINE_2 = "הסיפור של מלכה רוזנטל ז\"ל - 2";
    private final static String HEADLINE_3 = "הסיפור של מלכה רוזנטל ז\"ל - 3";
    private final static String HEADLINE_4 = "הסיפור של מלכה רוזנטל ז\"ל - 4";
    private final static String HEADLINE_5 = "הסיפור של מלכה רוזנטל ז\"ל - 5";

    private static final int FINAL_SCREEN = 5;

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
    ImageView heroView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        initVars();

        shareToStoryBtn.setOnClickListener(v -> {
            if (StepIndex == 1) {
                openInstagram(ShareProcess.IMAGE);
            } else {
                openInstagram(ShareProcess.VIDEO);

            }
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
        initArr();

        popUpDialog = new Dialog(this);
        shareToStoryBtn = findViewById(R.id.story_share);
        headLine = findViewById(R.id.headline);
        instruction = findViewById(R.id.location_text);
        backBtn = findViewById(R.id.back_button);
        videoView = findViewById(R.id.videoView);
        heroView = ((ImageView) findViewById(R.id.imageViewHero));

        //first values
        headLine.setText(HEADLINE_1);
        instruction.setText(INSTRUCTIONS_STEP_1);
        videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.malka4);

        curVideoRes = R.raw.video1;
        videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + videos.get(0));


    }

    private void initArr() {
        videos = new ArrayList<>();
        videos.add(R.raw.malka4);
        videos.add(R.drawable.quiz_no_bg);
        videos.add(R.raw.malka5);
        videos.add(R.raw.malka6);
        videos.add(R.raw.malka7);


        instructionsArr = new ArrayList<>();
        instructionsArr.add(INSTRUCTIONS_STEP_2);
        instructionsArr.add(INSTRUCTIONS_STEP_3);
        instructionsArr.add(INSTRUCTIONS_STEP_4);
        instructionsArr.add(INSTRUCTIONS_STEP_5);

        headLines = new ArrayList<>();
        headLines.add(HEADLINE_2);
        headLines.add(HEADLINE_3);
        headLines.add(HEADLINE_4);headLines.add(HEADLINE_5);
    }


    private void manageScreenRefresh(View v) {
        StepIndex++;
        if (StepIndex == FINAL_SCREEN) {
            StepIndex--;
            openInstagram(ShareProcess.VIDEO);
            Intent endIntent = new Intent(StoryActivity.this, EndActivity.class);
            startActivity(endIntent);
        } else {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            managePopUp(v);
        }

    }


    private void openInstagram(int mode) {
        ShareProcess.share(videos.get(StepIndex), mode, this);
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
        headLine.setText(headLines.get(StepIndex - 1));
        instruction.setText(instructionsArr.get(StepIndex - 1));
        //update video
        if (StepIndex == 1) {
            videoView.stopPlayback();
            videoView.setVisibility(View.INVISIBLE);
            heroView.setVisibility(View.VISIBLE);
            heroView.bringToFront();
        } else if (StepIndex < 4) {
            if (StepIndex == 2) {
                videoView.setVisibility(View.VISIBLE);
                heroView.setVisibility(View.GONE);
            }
            videoUri = Uri.parse("android.resource://" + getPackageName()
                    + "/" + videos.get(StepIndex));
            videoView.setVideoURI(videoUri);
            videoView.start();
        }
    }


}
