package com.example.android.newapp.data;

import android.provider.BaseColumns;

/**
 * Created by katarinazemplenyiova on 08/01/2018.
 */

public class ItemContract {

    private ItemContract() {}

    public static final class ItemEntry implements BaseColumns{

        public final static String TABLE_NAME = "list";

        public final static String _ID = BaseColumns._ID;
        public final static String COL1 = "item";
        public final static String COL2 = "serving";
        public final static String COL3 = "date";

    }
}