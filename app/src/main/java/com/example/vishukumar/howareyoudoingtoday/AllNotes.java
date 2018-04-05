package com.example.vishukumar.howareyoudoingtoday;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class AllNotes extends AppCompatActivity {

    BarChart barChart, barChart2;

    RecyclerView recyclerView;
    DiaryStatusAdapter diaryStatusAdapter;

    List<DiaryStatus> diaryStatusList;


    StatusDatabaseHelper statusDatabaseHelper;
    ArrayList<BarEntry> barEntries;

    int angryCount,
            anxiousCount,
            demotivatedCount,
            happyCount,
            sadCount,
            worthlessCount;

    //For months dropdown
    Spinner spinner;
    String[] allMonths =  {
            "...",
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
    };

    ArrayList<String> months = new ArrayList<>();

    //4 buttons on top
    ImageButton quitButton, graphButton, newStatusButton, quotesButton;

    //Main Scroll View
    ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_notes);

        //Reference scroll view and scroll it to top
        scrollView = (ScrollView) findViewById(R.id.mainScrollViewId);
        scrollView.smoothScrollTo(0,0);

        //Create all 4 buttons
        quitButton = (ImageButton) findViewById(R.id.imageButtonQuit);
        graphButton  = (ImageButton) findViewById(R.id.imageButtonGraph);
        newStatusButton = (ImageButton) findViewById(R.id.imageButtonNew);
        quotesButton = (ImageButton) findViewById(R.id.imageButtonQuote);

        diaryStatusList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Read all status from database and create DiaryStatus objects
        //Read data from database
        statusDatabaseHelper = new StatusDatabaseHelper(this);

        Cursor cursor = statusDatabaseHelper.getAllStatus();
        if(cursor.getCount() == 0) {
            Log.d("tag", "No Entries in Database");
        } else {
            Log.d("tag", "***********Printing entries from Database");
            String dbDate, dbStatus, dbMood;
            while (cursor.moveToNext()) {
                dbDate = cursor.getString(1);
                dbStatus = cursor.getString(2);
                dbMood = cursor.getString(3);

                diaryStatusList.add(new DiaryStatus(dbDate, dbMood, dbStatus));

                Log.d("tag", dbDate);
                Log.d("tag", dbStatus);
                Log.d("tag", dbMood);
                Log.d("tag", "\n");
            }
        }

        diaryStatusAdapter = new DiaryStatusAdapter(this, diaryStatusList);


        recyclerView.setAdapter(diaryStatusAdapter);

    }

    // On click listener method for ImageButton
    public void imageButtonClicked(View view) {
        Log.d("tag", "imageButtonClicked");
        switch (view.getId()) {
            case R.id.imageButtonQuit:
                Log.d("tag", "Quit Button");
                quitApp();
                break;
            case R.id.imageButtonGraph:
                Log.d("tag", "Graph Button");

                Intent toResultPage = new Intent(AllNotes.this, BarCharResultPage.class);
                startActivity(toResultPage);

                break;
            case R.id.imageButtonNew:
                Log.d("tag", "New Button");

                Intent toNewstatusPage = new Intent(AllNotes.this, MoodsPage.class);
                startActivity(toNewstatusPage);

                break;
            case R.id.imageButtonQuote:
                Log.d("tag", "Quote Button");
                break;
        }
    }

    //This method will be called when user clicks on Quit button
    public void quitApp() {

        Log.d("tag", "App will be closed");
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);

    }
}
