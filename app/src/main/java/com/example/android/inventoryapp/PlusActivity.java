package com.example.android.inventoryapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.android.Inventory_app_stage_1.data.InventoryContract;
import com.example.android.Inventory_app_stage_1.data.InventoryContract.InventoryEntry;
import com.example.android.Inventory_app_stage_1.data.InventoryDbHelper;
import com.example.android.inventoryapp.R;
import com.example.android.inventoryapp.data.InventoryContract;

public class PlusActivity extends AppCompatActivity {

    private EditText BookNameEditText;
    private EditText BookPriceEditText;
    private EditText BookQuantityEditText;
    private Spinner  BookSupplierNameSpinner;
    private EditText BookSupplierAdresseEditText;
    private EditText BookSupplierPhoneNumberEditText;

    private int SupplierName = InventoryEntry.SUPPLIER_UNKNOWN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus);

        BookNameEditText = findViewById(R.id.book_name_edit_text);
        BookPriceEditText = findViewById(R.id.book_price_edit_text);
        BookQuantityEditText = findViewById(R.id.book_quantity_edit_text);
        BookSupplierNameSpinner = findViewById(R.id.book_supplier_name_spinner);
        BookSupplierAdresseEditText = findViewById( R.id.book_supplier_address_edit_text);
        BookSupplierPhoneNumberEditText = findViewById(R.id.book_supplier_phone_number_edit_text);
        setupSpinner();
    }

    private void setupSpinner() {

        ArrayAdapter bookSupplierNameSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_supplier_options, android.R.layout.simple_spinner_item);

        bookSupplierNameSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        BookSupplierNameSpinner.setAdapter(bookSupplierNameSpinnerAdapter);

        BookSupplierNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.supplier_amazon))) {
                        SupplierName = InventoryContract.InventoryEntry;
                    } else if (selection.equals(getString(R.string.supplier_thalia))) {
                        SupplierName = InventoryContract.InventoryEntry;
                    } else if (selection.equals(getString(R.string.supplier_exlibris))) {
                        SupplierName = InventoryContract.InventoryEntry;
                    } else if (selection.equals(getString(R.string.supplier_national_geographic))) {
                        SupplierName = InventoryContract.InventoryEntry
                    } else {
                        SupplierName = InventoryContract.SUPPLIER_UNKNOWN;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                SupplierName = InventoryContract.InventoryEntry.SUPPLIER_UNKNOWN;
            }
        });
    }


    private void insertProduct() {
        String bookNameString = BookNameEditText.getText().toString().trim();

        String bookPriceString = BookPriceEditText.getText().toString().trim();
        int bookPriceInteger = Integer.parseInt(bookPriceString);

        String bookQuantityString = BookQuantityEditText.getText().toString().trim();
        int bookQuantityInteger = Integer.parseInt(bookQuantityString);

        String bookSupplierAdresseString = BookSupplierAdresseEditText.getText().toString().trim();

        String bookSupplierPhoneNumberString = BookSupplierPhoneNumberEditText.getText().toString().trim();
        int bookSupplierPhoneNumberInteger = Integer.parseInt(bookSupplierPhoneNumberString);

        InventoryDbHelper mDbHelper = new InventoryDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put( InventoryContract.InventoryEntry.COLUMN_BOOK_NAME, bookNameString);
        values.put( InventoryContract.COLUMN_BOOK_PRICE, bookPriceInteger);
        values.put( InventoryContract.COLUMN_BOOK_QUANTITY, bookQuantityInteger);
        values.put( InventoryContract.COLUMN_BOOK_SUPPLIER_NAME, SupplierName);
        values.put( InventoryContract.COLUMN_BOOK_SUPPLIER_ADRESSE,bookSupplierAdresseString );
        values.put( InventoryContract.COLUMN_BOOK_SUPPLIER_PHONE_NUMBER, bookSupplierPhoneNumberInteger);

        long newRowId = db.insert( InventoryContract.TABLE_NAME, null, values);

        if (newRowId == -1) {
            Toast.makeText(this, "Error save book", Toast.LENGTH_SHORT).show();
            Log.d("Error message", "Can't insert a row to table");

        } else {
            Toast.makeText(this, "Book saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
            Log.d("successfully message", "insert row on table");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        Log.d("message", "open Add Activity");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save:
                insertProduct();
                finish();
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

