package com.example.android.newapp.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

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

//    public Cursor getWordMatches(String query, String[] columns) {
//        String selection = ItemEntry.COL1 + " MATCH ?";
//        String[] selectionArgs = new String[] {query+"*"};
//
//        return query(selection, selectionArgs, columns);
//    }
//
//    private Cursor query(String selection, String[] selectionArgs, String[] columns) {
//        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
//        builder.setTables(FTS_VIRTUAL_TABLE);
//
//        Cursor cursor = builder.query(mDatabaseOpenHelper.getReadableDatabase(),
//                columns, selection, selectionArgs, null, null, null);
//
//        if (cursor == null) {
//            return null;
//        } else if (!cursor.moveToFirst()) {
//            cursor.close();
//            return null;
//        }
//        return cursor;
//    }


}