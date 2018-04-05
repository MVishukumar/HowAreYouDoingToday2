package com.example.vishukumar.howareyoudoingtoday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class IndividualStatusDisplayPage extends AppCompatActivity {

    String date, status, desc;

    ImageButton backButton;

    TextView iheading, idate, idescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_status_display_page);

        Bundle bundle = getIntent().getExtras();
        date = bundle.getString("_DATE");
        status = bundle.getString("_MOOD");
        desc = bundle.getString("_DESC");

        Log.d("tag", date);
        Log.d("tag", status);
        Log.d("tag", desc);

        // Reference all elements
        backButton = (ImageButton) findViewById(R.id.backButtonId);
        iheading = (TextView) findViewById(R.id.statusTvId);
        idate = (TextView) findViewById(R.id.dateTvId);
        idescription = (TextView) findViewById(R.id.descTvId);

        // Set all the values
        iheading.setText(status);
        idate.setText(date);
        idescription.setText(desc);

    }

    public void goToPreviousActivity(View view) {
        finish();
    }
}
