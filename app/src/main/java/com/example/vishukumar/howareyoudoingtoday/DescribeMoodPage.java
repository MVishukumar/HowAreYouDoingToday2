package com.example.vishukumar.howareyoudoingtoday;

import android.graphics.drawable.Icon;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DescribeMoodPage extends AppCompatActivity {

    ImageView imageView; //singleMoodId
    TextView title, desc; //moodTitleId moodDescriptionId
    TextView whatHappenedQuestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_describe_mood_page);

        Log.d("tag", "inside onCreate of DescribeMoodPage");

        Bundle bundle = getIntent().getExtras();
        String moodState = bundle.getString("MOOD_STATUS");
        String moodConsolingMessage = bundle.getString("MOOD_CONSOLING_MESSAGE");

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
    }
}
