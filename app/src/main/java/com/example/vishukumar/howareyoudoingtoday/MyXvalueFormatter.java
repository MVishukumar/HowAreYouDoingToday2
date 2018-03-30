package com.example.vishukumar.howareyoudoingtoday;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by vishukum on 26-03-2018 0026.
 */

public class MyXvalueFormatter implements IAxisValueFormatter {
    private String[] iValues;

    public MyXvalueFormatter() {
    }

    public MyXvalueFormatter(String[] iValues) {
        this.iValues = iValues;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return iValues[(int) value];
    }
}