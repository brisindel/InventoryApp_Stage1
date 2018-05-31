package com.example.android.inventoryapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.inventoryapp.data.DatabaseContract.BooksEntry;
import com.example.android.inventoryapp.data.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = "Cursor Log";

    /**
     * Database helper that will provide us access to the database
     */
    private DatabaseHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new DatabaseHelper(this);

        insertRecord();
        readFromDatabase();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     */

    private void readFromDatabase() {
// Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        //read all column from database, in 2nd Stage can be rewrite for
        String[] projection = {
                BooksEntry._ID,
                BooksEntry.COLUMN_NAME,
                BooksEntry.COLUMN_CATEGORY,
                BooksEntry.COLUMN_QUANTITY,
                BooksEntry.COLUMN_PRICE,
                BooksEntry.COLUMN_SUPPLIER,
                BooksEntry.COLUMN_SUPPLIER_PHONE
        };

        //Define "where" part of query
        String selection = BooksEntry._ID + "=?";

        //Specify arguments in placeholder order, for now is line No. 1.
        //This part of code will be rewrite in 2nd Stage

        String[] selectionArg = {"1"};

        // Perform a query on the books table
        Cursor cursor = db.query(
                BooksEntry.TABLE_NAME,
                projection,
                selection,
                selectionArg,
                null,
                null,
                null);

        //here will be code for displaying information from cursor (2nd Stage)

        //control if cursor works properly, this part will be remove in final app
        cursor.moveToNext();
        int nameColumnIndex = cursor.getColumnIndex(BooksEntry.COLUMN_NAME);
        String cursorString = cursor.getString(nameColumnIndex);
        Log.i(LOG_TAG, cursorString);

        cursor.close();
    }

    // Insert a record
    private void insertRecord() {

        //Get a writable database
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String nameString = "Android for Beginners";
        String categoryString = "Mobile development";
        int quantityValue = 1;
        int priceValue = 10;
        String supplierString = "Google";
        String supplierPhoneString = "+1 123 456 789";

        //Create a ContentValues object where column names are the keys and Android for Beginners attributes are the values as example

        //In 2nd Stage will be replace with real inputs from EditLayout (2nd Stage)

        ContentValues values = new ContentValues();
        values.put(BooksEntry.COLUMN_NAME, nameString);
        values.put(BooksEntry.COLUMN_CATEGORY, categoryString);
        values.put(BooksEntry.COLUMN_QUANTITY, quantityValue);
        values.put(BooksEntry.COLUMN_PRICE, priceValue);
        values.put(BooksEntry.COLUMN_SUPPLIER, supplierString);
        values.put(BooksEntry.COLUMN_SUPPLIER_PHONE, supplierPhoneString);

        // Insert a new row for book in the database, returning the ID of that new row.
        // The first argument for db.insert() is the books table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for book.
        long newRowId = db.insert(BooksEntry.TABLE_NAME, null, values);
    }
}
