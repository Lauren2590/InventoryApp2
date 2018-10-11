package com.example.android.inventoryapp.data;

import android.provider.BaseColumns;

public class InventoryContract {

    public InventoryContract() {
    }

    public final static class InventoryEntry implements BaseColumns {

        public final static String TABLE_NAME = "product";

        //Column/Table
        public final static String ID = BaseColumns._ID;
        public final static String COLUMN_BOOK_NAME = "book_name";
        public final static String COLUMN_BOOK_PRICE = "price";
        public final static String COLUMN_BOOK_QUANTITY = "quantity";
        public final static String COLUMN_BOOK_SUPPLIER_NAME = "supplier_name";
        public final static String COLUMN_BOOK_SUPPLIER_ADRESSE = "supplier_adresse";
        public final static String COLUMN_BOOK_SUPPLIER_PHONE_NUMBER = "supplier_phone_number";

        // Suppliers
        public final static int SUPPLIER_UNKNOWN = 0;
        public final static int SUPPLIER_AMAZON  = 1;
        public final static int SUPPLIER_THALIA = 2;
        public final static int SUPPLIER_EXLIBRIS = 3;
        public final static int SUPPLIER_NATIONAL_GEOGRAPHIC = 4;


    }
}
