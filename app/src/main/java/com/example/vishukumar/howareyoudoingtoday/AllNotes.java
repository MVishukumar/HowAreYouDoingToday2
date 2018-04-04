package com.example.vishukumar.howareyoudoingtoday;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    ScrollView scrollView;

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
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_notes);

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
}
