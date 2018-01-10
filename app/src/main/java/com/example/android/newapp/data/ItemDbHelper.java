package com.example.android.newapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.newapp.data.ItemContract.ItemEntry;


/**
 * Created by katarinazemplenyiova on 08/01/2018.
 */

public class ItemDbHelper extends SQLiteOpenHelper{

    public static final String LOG_TAG = ItemDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "list.db";

    private static final int DATABASE_VERSION =1;


    public ItemDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_FOOD_TABLE =  "CREATE TABLE " + ItemEntry.TABLE_NAME + " ("
                + ItemEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ItemEntry.COL1 + " TEXT NOT NULL, "
                + ItemEntry.COL2 + " INTEGER NOT NULL DEFAULT 0,"
                + ItemEntry.COL3 + " TEXT NOT NULL)";


        db.execSQL(SQL_CREATE_FOOD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}