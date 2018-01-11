package com.example.android.newapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toolbar;


import com.example.android.newapp.data.ItemContract.ItemEntry;
import com.example.android.newapp.data.ItemDbHelper;

public class MainActivity extends AppCompatActivity {

    private ItemDbHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ItemDbHelper itemDbHelper = new ItemDbHelper(this);

        db = itemDbHelper.getWritableDatabase();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });

        dbHelper = new ItemDbHelper(this);


    }

    public void deleteItem(View listItem) {

        String name = (String) listItem.getTag(R.string.item);

        db.delete(ItemEntry.TABLE_NAME, ItemEntry.COL1 + "= '" + name + "'", null);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                ItemEntry._ID,
                ItemEntry.COL1,
                ItemEntry.COL2,
                ItemEntry.COL3};

        Cursor cursor = db.query(
                ItemEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
        ListView itemListView = (ListView) findViewById(R.id.list);


        View emptyView = findViewById(R.id.empty_view);
        itemListView.setEmptyView(emptyView);

        ItemCursorAdapter adapter = new ItemCursorAdapter(this, cursor);
        itemListView.setAdapter(adapter);
    }


//        displayView.setText("Your food list: " + cursor.getCount() + " items.\n\n");
//        displayView.append(ItemEntry._ID + " - " +
//                ItemEntry.COL1 + " - " +
//                ItemEntry.COL2 + " - " +
//                ItemEntry.COL3 + "\n");
//        int idColumnIndex = cursor.getColumnIndex(ItemEntry._ID);
//        int nameColumnIndex = cursor.getColumnIndex(ItemEntry.COL1);
//        int servingColumnIndex = cursor.getColumnIndex(ItemEntry.COL2);
//        int dateColumnIndex = cursor.getColumnIndex(ItemEntry.COL3);
//
//
//
//        while (cursor.moveToNext()) {
//            int currentID = cursor.getInt(idColumnIndex);
//            String currentName = cursor.getString(nameColumnIndex);
//            int currentServing = cursor.getInt(servingColumnIndex);
//            String currentDate = cursor.getString(dateColumnIndex);
//            displayView.append(("\n" + currentID + " - " +
//                    currentName + " - " +
//                    currentServing + " - " +
//                    currentDate));
//        }
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        return true;
    }


}