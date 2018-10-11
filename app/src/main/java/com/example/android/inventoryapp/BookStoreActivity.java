package com.example.android.inventoryapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.android.inventoryapp.data.InventoryContract;
import com.example.android.inventoryapp.data.InventoryContract.InventoryEntry;
import com.example.android.inventoryapp.data.InventoryDbHelper;
import com.example.android.inventoryapp.PlusActivity;
import com.example.android.inventoryapp.R;

public class BookStoreActivity extends AppCompatActivity {

    // Helps access to db
    private InventoryDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookstore);

        FloatingActionButton plus = findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookStoreActivity.this, PlusActivity.class);
                startActivity(intent);
            }
        });

        mDbHelper = new InventoryDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                InventoryContract.InventoryEntry.ID,
                InventoryContract.InventoryEntry.COLUMN_BOOK_NAME,
                InventoryContract.InventoryEntry.COLUMN_BOOK_PRICE,
                InventoryContract.InventoryEntry.COLUMN_BOOK_QUANTITY,
                InventoryContract.InventoryEntry.COLUMN_BOOK_SUPPLIER_NAME,
                InventoryContract.InventoryEntry.COLUMN_BOOK_SUPPLIER_ADRESSE,
                InventoryContract.InventoryEntry.COLUMN_BOOK_SUPPLIER_PHONE_NUMBER
        };
        Cursor cursor = db.query(
                InventoryContract.InventoryEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        TextView displayView = (TextView) findViewById(R.id.text_view_inventory);

        try {
            displayView.setText( "Inventory contains : " + cursor.getCount() + " books.\n\n" );

            displayView.append(
                    InventoryContract.InventoryEntry._ID + " | " +
                            InventoryEntry.COLUMN_BOOK_NAME + " | " +
                            InventoryEntry.COLUMN_BOOK_PRICE + " | " +
                            InventoryEntry.COLUMN_BOOK_QUANTITY + " | " +
                            InventoryEntry.COLUMN_BOOK_SUPPLIER_NAME + " | " +
                            InventoryEntry.COLUMN_BOOK_SUPPLIER_ADRESSE + " | " +
                            InventoryEntry.COLUMN_BOOK_SUPPLIER_PHONE_NUMBER + "\n");

            int idColumnIndex = cursor.getColumnIndex(InventoryEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_BOOK_NAME);
            int priceColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_BOOK_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_BOOK_QUANTITY);
            int supplierNameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_BOOK_SUPPLIER_NAME);
            int supplierAdresseColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_BOOK_SUPPLIER_ADRESSE);
            int supplierPhoneColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_BOOK_SUPPLIER_PHONE_NUMBER);
            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName =cursor.getString(nameColumnIndex);
                int currentPrice =cursor.getInt(priceColumnIndex);
                int currentQuantity =cursor.getInt(quantityColumnIndex);
                int currentSupplierName =cursor.getInt(supplierNameColumnIndex);
                String currentSupplierAdresse=cursor.getString(supplierAdresseColumnIndex);
                int currentSupplierPhone =cursor.getInt(supplierPhoneColumnIndex);

                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentPrice + " - " +
                        currentQuantity + " - " +
                        currentSupplierName + " - " +
                        currentSupplierAdresse + " - " +
                        currentSupplierPhone ));
            }

        } finally {
            cursor.close();
        }
    }
}
