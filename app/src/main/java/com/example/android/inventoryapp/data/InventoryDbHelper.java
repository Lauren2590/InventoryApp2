package com.example.android.inventoryapp.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class InventoryDbHelper extends SQLiteOpenHelper {

    //get a classname to the og
    public static final String LOG_TAG = InventoryDbHelper.class.getSimpleName();

    //DB/Name
    private static final String DATABASE_NAME = "inventory.db";
    private static final int DATABASE_VERSION = 1;

    public InventoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create a Table
        String SQL_CREATE_BOOK_TABLE = "CREATE TABLE " + InventoryContract.InventoryEntry.TABLE_NAME + " ("
                + InventoryContract.InventoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + InventoryContract.InventoryEntry.COLUMN_BOOK_NAME + " TEXT NOT NULL, "
                + InventoryContract.InventoryEntry.COLUMN_BOOK_PRICE + " INTEGER NOT NULL, "
                + InventoryContract.InventoryEntry.COLUMN_BOOK_QUANTITY + " INTEGER NOT NULL, "
                + InventoryContract.InventoryEntry.COLUMN_BOOK_SUPPLIER_NAME + " INTEGER NOT NULL DEFAULT 0, "
                + InventoryContract.InventoryEntry.COLUMN_BOOK_SUPPLIER_ADRESSE + " TEXT NOT NULL, "
                + InventoryContract.InventoryEntry.COLUMN_BOOK_SUPPLIER_PHONE_NUMBER + " INTEGER );";

        db.execSQL(SQL_CREATE_BOOK_TABLE);

        Log.d("successfully message" , "created table of db");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }
}
