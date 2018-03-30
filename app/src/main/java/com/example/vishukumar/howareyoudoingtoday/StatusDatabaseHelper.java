package com.example.vishukumar.howareyoudoingtoday;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by vishukum on 26-03-2018 0026.
 */

public class StatusDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Emo_diary.db";
    private static final String TABLE_NAME = "dairy_table";

    private static final String COL_1 = "ID";
    private static final String COL_2 = "DATE";
    private static final String COL_3 = "DESCRIPTION";
    private static final String COL_4 = "MOOD";


    public StatusDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.d("tag", "StatusDatabaseHelper");
        //context.deleteDatabase(DATABASE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("tag", "onCreate");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE DATE, DESCRIPTION VARCHAR2(2000), MOOD VARCHAR2(16))");
        Log.d("tag", "Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d("tag", "onUpgrade");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public int addEntryInMyDiary(String date, String desc, String mood) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, date);
        contentValues.put(COL_3, desc);
        contentValues.put(COL_4, mood);

        return (int) sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public Cursor getAllStatus() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }

}