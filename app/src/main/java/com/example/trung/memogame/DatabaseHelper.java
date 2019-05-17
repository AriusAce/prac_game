package com.example.trung.memogame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Leaderboard.db";
    public static final String TABLE_NAME = "Highscore";
    public static final String COL1 = "NAME";
    public static final String COL2 = "SCORE";
    public static final String COL3 = "ROUND";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (NAME TEXT, SCORE INTEGER, ROUND INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, int score, int round ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues entry = new ContentValues();
        entry.put(COL1, name);
        entry.put(COL2, score);
        entry.put(COL3, round);
        long result = db.insert(TABLE_NAME, null, entry);
        return result != -1;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME +
                " ORDER BY " + COL2 + " DESC",null);
        return res;
    }
}
