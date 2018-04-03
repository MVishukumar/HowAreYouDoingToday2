package com.example.vishukumar.howareyoudoingtoday;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ScrollView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class BarCharResultPage extends AppCompatActivity {

    BarChart barChart, barChart2;

    RecyclerView recyclerView;
    DiaryStatusAdapter diaryStatusAdapter;

    List<DiaryStatus> diaryStatusList;

    ScrollView scrollView;

    StatusDatabaseHelper statusDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_char_result_page);

        //Read data from database
        statusDatabaseHelper = new StatusDatabaseHelper(this);

        Cursor cursor = statusDatabaseHelper.getAllStatus();
        if(cursor.getCount() == 0) {
            Log.d("tag", "No Entries in Database");
        } else {
            Log.d("tag", "Printing entries from Database");
            while (cursor.moveToNext()) {
                Log.d("tag", cursor.getString(0));
                Log.d("tag", cursor.getString(1));
                Log.d("tag", cursor.getString(2));
                Log.d("tag", cursor.getString(3));
                Log.d("tag", "\n");
            }
        }

        scrollView = (ScrollView) findViewById(R.id.barChartMainScrollViewId);
        scrollView.fullScroll(ScrollView.FOCUS_UP);

        barChart = (BarChart) findViewById(R.id.id_bargraph);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(100);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(false);
        barChart.getDescription().setEnabled(false);


        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, 22.0f));
        barEntries.add(new BarEntry(1, 42.0f));
        barEntries.add(new BarEntry(2, 12.0f));
        barEntries.add(new BarEntry(3, 32.0f));
        barEntries.add(new BarEntry(4, 82.0f));
        barEntries.add(new BarEntry(5, 34.0f));

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

        diaryStatusList.add(new DiaryStatus("2018-03-03", "Happy", "1 I am happy because..."));
        diaryStatusList.add(new DiaryStatus("2018-03-03", "Anxious", "2 I am happy because..."));
        diaryStatusList.add(new DiaryStatus("2018-03-03", "Demotivated", "3 I am happy because..."));
        diaryStatusList.add(new DiaryStatus("2018-03-03", "Happy", "4 I am happy because..."));
        diaryStatusList.add(new DiaryStatus("2018-03-03", "Happy", "5 I am happy because..."));
        diaryStatusList.add(new DiaryStatus("2018-03-03", "Worthless", "6 I am happy because..."));
        diaryStatusList.add(new DiaryStatus("2018-03-03", "Happy", "7 I am happy because..."));
        diaryStatusList.add(new DiaryStatus("2018-03-03", "Happy", "8 I am happy because..."));
        diaryStatusList.add(new DiaryStatus("2018-03-03", "Happy", "9 I am happy because..."));
        diaryStatusList.add(new DiaryStatus("2018-03-03", "Angry", "10 I am happy because..."));
        diaryStatusList.add(new DiaryStatus("2018-03-03", "Sad", "11 I am happy because..."));
        diaryStatusList.add(new DiaryStatus("2018-03-03", "Happy", "12 I am happy because..."));
        diaryStatusList.add(new DiaryStatus("2018-03-03", "Happy", "13 I am happy because..."));
        diaryStatusList.add(new DiaryStatus("2018-03-03", "Demotivated", "14 I am happy because..."));
        diaryStatusList.add(new DiaryStatus("2018-03-03", "Happy", "15 I am happy because..."));
        diaryStatusList.add(new DiaryStatus("2018-03-03", "Sad", "16 I am happy because..."));

        diaryStatusAdapter = new DiaryStatusAdapter(this, diaryStatusList);


        recyclerView.setAdapter(diaryStatusAdapter);


    }
}
