package com.example.android.inventoryapp.data;

import android.provider.BaseColumns;

public class DatabaseContract {

    private DatabaseContract() {
    }

    public static final class BooksEntry implements BaseColumns {

        /**
         * Inner class that defines constant values for the books database table.
         * Each entry in the table represents a single book.
         */
        public final static String TABLE_NAME = "Books";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "name";
        public final static String COLUMN_CATEGORY = "category";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_PRICE = "price";

        public final static String COLUMN_SUPPLIER = "supplier";
        public final static String COLUMN_SUPPLIER_PHONE = "phone";
    }
}
