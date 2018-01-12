package com.example.android.newapp;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.newapp.data.ItemContract;
import com.example.android.newapp.data.ItemContract.ItemEntry;
import com.example.android.newapp.data.ItemDbHelper;

import java.util.Calendar;

public class EditActivity extends AppCompatActivity {

    private EditText mName;
    private EditText mServing;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateListener;
    private String mDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        mDisplayDate = (TextView) findViewById(R.id.tvDate);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        EditActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }


        });

        mDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;
                String date = month + "/" + dayOfMonth + "/" + year;
                mDisplayDate.setText(date);
                mDate = date;
            }
        };


        mName = (EditText) findViewById(R.id.edit_name);
        mServing= (EditText) findViewById(R.id.edit_serving);
    }

    private void insertItem() {
        String nameString = mName.getText().toString().trim();
        String servingString = mServing.getText().toString().trim();
        int serving = Integer.parseInt(servingString);

        ItemDbHelper itemDbHelper = new ItemDbHelper(this);

        SQLiteDatabase db = itemDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ItemEntry.COL1, nameString);
        values.put(ItemEntry.COL2, serving);
        values.put(ItemEntry.COL3, mDate);

        long newRowId = db.insert(ItemEntry.TABLE_NAME, null, values);
        if (newRowId == -1) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Saved" + newRowId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }


    public void onButtonCLicked(View button) {
      insertItem();
      finish();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                insertItem();
                finish();
                return true;
            case R.id.action_delete:
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }








}