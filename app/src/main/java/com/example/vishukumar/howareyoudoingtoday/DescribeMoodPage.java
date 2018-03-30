package com.example.vishukumar.howareyoudoingtoday;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Icon;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DescribeMoodPage extends AppCompatActivity {

    ImageView imageView; //singleMoodId
    TextView title, desc; //moodTitleId moodDescriptionId
    TextView whatHappenedQuestion;

    EditText whatHappenedEditText;

    StatusDatabaseHelper statusDatabaseHelper;

    String moodState;
    String moodConsolingMessage;

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
            View view = getLayoutInflater().inflate(R.layout.after_saving_status_popup_page, null);
            TextView textView = (TextView) findViewById(R.id.consoleMessageId);
            //Button quitAppButton = (Button) findViewById(R.id.quitAppButtonId);
            //Button showResultButton = (Button) findViewById(R.id.showResultButtonId);


            /*okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("tag", "Ok Button Clicked!");
                }
            });

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("tag", "Cancel Button Clicked!");
                }
            });*/

            builder.setView(view);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

            Log.d("tag", "Database Insertion Id : " + result);
        }


        Log.d("tag", "End of onclick");

    }

    public void quitOrResultsButtonClicked(View v) {

        switch (v.getId()) {
            case R.id.quitAppButtonId:
                Log.d("tag", "Quit App Button Clicked");
                break;
            case R.id.showResultButtonId:
                Log.d("tag", "Show Result Button Clicked");
                break;
        }

        Log.d("tag", "Ok or Cancel Button Clicked");
    }
}
