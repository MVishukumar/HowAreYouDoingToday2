package com.example.vishukumar.howareyoudoingtoday;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BarCharResultPage extends AppCompatActivity implements OnChartValueSelectedListener,
    AdapterView.OnItemSelectedListener{

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
        setContentView(R.layout.activity_bar_char_result_page);

        //Init all the count variable values
        angryCount = 0;
        anxiousCount = 0;
        demotivatedCount = 0;
        happyCount = 0;
        sadCount = 0;
        worthlessCount = 0;

        //Construct spinner
        Calendar calendar = Calendar.getInstance();
        calendar.get(Calendar.MONTH);
        Log.d("tag", "Month index : " + calendar.get(Calendar.MONTH) + " ");
        switch (calendar.get(Calendar.MONTH)) {
            case 0:
                months = populateMonths(1);
                break;
            case 1:
                months = populateMonths(2);
                break;
            case 2:
                months = populateMonths(3);
                break;
            case 3:
                months = populateMonths(4);
                break;
            case 4:
                months = populateMonths(5);
                break;
            case 5:
                months = populateMonths(6);
                break;
            case 6:
                months = populateMonths(7);
                break;
            case 7:
                months = populateMonths(8);
                break;
            case 8:
                months = populateMonths(9);
                break;
            case 9:
                months = populateMonths(10);
                break;
            case 10:
                months = populateMonths(11);
                break;
            case 11:
                months = populateMonths(12);
                break;
        }

        String[] dropDownFilteredMonths = months.toArray(new String[months.size()]);

        spinner = (Spinner) findViewById(R.id.monthsSpinnerId);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(BarCharResultPage.this,
                android.R.layout.simple_spinner_item, dropDownFilteredMonths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //Read data from database
        statusDatabaseHelper = new StatusDatabaseHelper(this);

        Cursor cursor = statusDatabaseHelper.getAllEmotionsCount();
        if(cursor.getCount() == 0) {
            Log.d("tag", "No Entries in Database");
        } else {
            Log.d("tag", "Printing entries from Database");
            while (cursor.moveToNext()) {
                String moodFromDbQuery = "";
                moodFromDbQuery = cursor.getString(0);
                Log.d("tag", "Mood From DB Query : " + moodFromDbQuery);
                switch (moodFromDbQuery.toUpperCase()) {
                    case "ANGRY":
                        angryCount = Integer.parseInt(cursor.getString(1));
                        Log.d("tag", "Mood Count From DB Query : " + angryCount);
                        break;
                    case "ANXIOUS":
                        anxiousCount = Integer.parseInt(cursor.getString(1));
                        Log.d("tag", "Mood Count From DB Query : " + anxiousCount);
                        break;
                    case "DEMOTIVATED":
                        demotivatedCount = Integer.parseInt(cursor.getString(1));
                        Log.d("tag", "Mood Count From DB Query : " + demotivatedCount);
                        break;
                    case "HAPPY":
                        happyCount = Integer.parseInt(cursor.getString(1));
                        Log.d("tag", "Mood Count From DB Query : " + happyCount);
                        break;
                    case "SAD":
                        sadCount = Integer.parseInt(cursor.getString(1));
                        Log.d("tag", "Mood Count From DB Query : " + sadCount);
                        break;
                    case "WORTHLESS":
                        worthlessCount = Integer.parseInt(cursor.getString(1));
                        Log.d("tag", "Mood Count From DB Query : " + worthlessCount);
                        break;
                }
                Log.d("tag", "\n");
            }
        }

        scrollView = (ScrollView) findViewById(R.id.barChartMainScrollViewId);
        scrollView.fullScroll(ScrollView.FOCUS_UP);

        barChart = (BarChart) findViewById(R.id.id_bargraph);
        barChart.setOnChartValueSelectedListener(this);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(100);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(false);
        barChart.getDescription().setEnabled(false);


        barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, (float) happyCount));
        barEntries.add(new BarEntry(1, (float) anxiousCount));
        barEntries.add(new BarEntry(2, (float) angryCount));
        barEntries.add(new BarEntry(3, (float) worthlessCount));
        barEntries.add(new BarEntry(4, (float) sadCount));
        barEntries.add(new BarEntry(5, (float) demotivatedCount));

        BarDataSet barDataSet = new BarDataSet(barEntries, "");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        BarData barData = new BarData(barDataSet);
        //barData.setBarWidth(0.9f);

        barChart.setData(barData);


        String[] months = new String[] {
                "Happy", "Anxious", "Angry", "Worthless", "Sad", "Demotivated"
        };

        YAxis yAxis = barChart.getAxisRight();
        yAxis.setEnabled(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(6);
        xAxis.setValueFormatter(new MyXvalueFormatter(months));

        //xAxis.setAxisMinimum(1);

        Legend l = barChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);

        //For displaying all the status
        diaryStatusList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Read all status from database and create DiaryStatus objects
        cursor = statusDatabaseHelper.getAllStatus();
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

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.d("tag", "onValueSelected for chart");
        String onTouchMood = "";
        switch (barEntries.indexOf(e)) {
            case 0:
                onTouchMood = "Happy";
                Log.d("tag", "Happy");
                break;
            case 1:
                onTouchMood = "Anxious";
                Log.d("tag", "Anxious");
                break;
            case 2:
                onTouchMood = "Angry";
                Log.d("tag", "Angry");
                break;
            case 3:
                onTouchMood = "Worthless";
                Log.d("tag", "Worthless");
                break;
            case 4:
                onTouchMood = "Sad";
                Log.d("tag", "Sad");
                break;
            case 5:
                onTouchMood = "Demotivated";
                Log.d("tag", "Demotivated");
                break;
        }

        diaryStatusList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Read all status from database and create DiaryStatus objects
        Cursor cursor = statusDatabaseHelper.getAllStatus(onTouchMood);
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

    @Override
    public void onNothingSelected() {
        diaryStatusList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Read all status from database and create DiaryStatus objects
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

    private ArrayList<String> populateMonths(int index) {

        ArrayList<String> temp = new ArrayList<>();

        for(int i=0; i<= index; i++) {
            temp.add(allMonths[i]);
        }

        return temp;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        Log.d("tag", allMonths[position] + " ");
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
