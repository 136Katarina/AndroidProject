package com.example.android.newapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.newapp.data.ItemContract;
import com.example.android.newapp.data.ItemContract.ItemEntry;

/**
 * Created by katarinazemplenyiova on 09/01/2018.
 */

public class ItemCursorAdapter extends CursorAdapter {


    public ItemCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameTextView = (TextView) view.findViewById(R.id.name);

        int nameColumnIndex = cursor.getColumnIndex(ItemEntry.COL1);

        String itemName = cursor.getString(nameColumnIndex);

        nameTextView.setText(itemName);
        view.setTag(R.string.item, itemName);

    }
}

