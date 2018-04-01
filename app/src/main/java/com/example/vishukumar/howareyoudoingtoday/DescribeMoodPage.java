package com.example.vishukumar.howareyoudoingtoday;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DescribeMoodPage extends AppCompatActivity {

    ImageView imageView, imageViewSave; //singleMoodId
    TextView title, desc; //moodTitleId moodDescriptionId
    TextView whatHappenedQuestion;

    EditText whatHappenedEditText;

    StatusDatabaseHelper statusDatabaseHelper;

    String moodState;
    String moodConsolingMessage;

    Typeface vegur_light;
    Typeface vegur_bold;
    Typeface vegur_regular;
    Typeface janda;

    Animation fadeIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_describe_mood_page);

        Log.d("tag", "inside onCreate of DescribeMoodPage");

        Bundle bundle = getIntent().getExtras();
        moodState = bundle.getString("MOOD_STATUS");
        moodConsolingMessage = bundle.getString("MOOD_CONSOLING_MESSAGE");

        Log.d("tag", "Mood Sent : to DescribeMoodPage : " + moodState);
        Log.d("tag", "Mood Desc : Sent to DescribeMoodPage : " + moodConsolingMessage);

        imageView = (ImageView) findViewById(R.id.singleMoodId);
        title = (TextView) findViewById(R.id.moodTitleId);
        //desc = (TextView) findViewById(R.id.moodDescriptionId);
        whatHappenedQuestion = (TextView) findViewById(R.id.whatHappenedTextViewId);

        imageView.setImageResource(R.drawable.happy);
        title.setText(moodState);
        //desc.setText(moodConsolingMessage);
        whatHappenedQuestion.setText(whatHappenedQuestion.getText() + " " + moodState + " today?");

        vegur_light = Typeface.createFromAsset(getAssets(), "Vegur-Light.otf");
        vegur_bold = Typeface.createFromAsset(getAssets(), "Vegur-Bold.otf");
        vegur_regular = Typeface.createFromAsset(getAssets(), "Vegur-Regular.otf");
        janda = Typeface.createFromAsset(getAssets(), "janda-manatee.bubble.ttf");

        title.setTypeface(vegur_bold);
        whatHappenedQuestion.setTypeface(vegur_regular);

        whatHappenedEditText = (EditText) findViewById(R.id.whatHappenedEditTextId);
        whatHappenedEditText.setTypeface(janda);



    }

    public void saveButtonClicked(View v) {
        Log.d("tag", "Save button was clicked");

        whatHappenedEditText = (EditText) findViewById(R.id.whatHappenedEditTextId);
        String todayStatus = "";
        todayStatus = whatHappenedEditText.getText().toString();

        if(todayStatus.length() == 0) {
            Snackbar snackbar = Snackbar
                    .make(v, "Tell yourself what made you " + moodState + " today, and write it in the box.", Snackbar.LENGTH_LONG);
            snackbar.show();
        } else {
            statusDatabaseHelper = new StatusDatabaseHelper(v.getContext());

            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = simpleDateFormat.format(date);
            Log.d("tag","Date formatted : " + formattedDate);

            int result = statusDatabaseHelper.addEntryInMyDiary(formattedDate, moodState, todayStatus);


            //Show custom dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(DescribeMoodPage.this);
            View view = getLayoutInflater().inflate(R.layout.popup, null);

            builder.setView(view);
            AlertDialog alertDialog = builder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.setCancelable(true);
            alertDialog.show();

            Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    Intent i = new Intent(DescribeMoodPage.this, AfterSaveGyaan.class);
                    i.putExtra("MOOD_CONSOLING_MESSAGE", moodConsolingMessage);
                    startActivity(i);
                }

            }, 3000L);

            Log.d("tag", "Database Insertion Id : " + result);
        }


        Log.d("tag", "End of onclick");

    }

}
