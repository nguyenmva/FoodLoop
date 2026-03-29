package com.example.foodloop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "FOODLOOP.DB";

    // ##################################################################################################################
    // USER ACCOUNT TABLE
    public static final String ACCOUNTS_TABLE = "Accounts";
    public static final String USER_ID_FLD = "USER_ID";
    public static final String USER_NAME_FLD = "NAME";
    public static final String USER_STREET_FLD = "STREET";
    public static final String USER_CITY_FLD = "CITY";
    public static final String USER_PROVINCE_FLD = "PROVINCE";
    public static final String USER_COUNTRY_FLD = "COUNTRY";
    public static final String USER_POSTAL_FLD = "POSTAL";
    public static final String USER_PHONE_FLD = "PHONE";
    public static final String USER_EMAIL_FLD = "EMAIL";
    public static final String USER_PASSWORD_FLD = "PASSWORD";

    // ##################################################################################################################
    // DONATIONS TABLE
    public static final String DONATION_TABLE = "Donations";
    public static final String DONATION_ID_FLD = "DONATION_HISTORY_ID";
    public static final String DONATION_ITEM_NAME_FLD = "DONATION_ITEM_NAME";
    public static final String DONATION_QUANTITY_FLD = "DONATION_QUANTITY";
    public static final String DONATION_CATEGORY_FLD = "DONATION_CATEGORY";
    public static final String DONATION_CATEGORY_SPINNER_FLD = "DONATION_CATEGORY_SPINNER";
    public static final String DONATION_EXPIRY_DATE_FLD = "DONATION_EXPIRY_DATE";
    public static final String DONATION_PICKUP_TIME_FLD = "DONATION_PICKUP_TIME";
    public static final String DONATION_PICKUP_TIME_SPINNER_FLD = "DONATION_PICKUP_TIME_SPINNER";
    public static final String DONATION_OFFER_TYPE_FLD = "DONATION_OFFER_TYPE";
    public static final String DONATION_PRICE_FLD = "DONATION_PRICE";
    public static final String DONATION_LOCATION_FLD = "DONATION_LOCATION";
    public static final String DONATION_STATUS_FLD = "DONATION_STATUS";
    public static final String RECIPIENT_FLD = "RECIPIENT";

    // ##################################################################################################################
    // REQUESTS TABLE
    public static final String REQUEST_HISTORY_TABLE = "RequestHistory";
    public static final String REQUEST_HISTORY_ID_FLD = "REQUEST_HISTORY_ID";
    public static final String REQUEST_DATE_FLD = "REQUEST_DATE";
    public static final String REQUEST_ITEM_NAME_FLD = "REQUEST_ITEM_NAME";
    public static final String REQUEST_LOCATION_FLD = "REQUEST_LOCATION";

    // ##################################################################################################################
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    // ##################################################################################################################
    // CREATE THE TABLE ("SQL COMMAND " + TABLE_NAME + " (" + FIELD1_NAME DATATYPE," + "FIELD2_NAME DATATYPE)"
    @Override
    public void onCreate(SQLiteDatabase db){
        // #######################################################
        // FOR ACCOUNTS
        db.execSQL("CREATE TABLE " + ACCOUNTS_TABLE + " (" +
                USER_ID_FLD + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_NAME_FLD + " TEXT, " +
                USER_STREET_FLD + " TEXT, " +
                USER_CITY_FLD + " TEXT, " +
                USER_PROVINCE_FLD + " TEXT, " +
                USER_COUNTRY_FLD + " TEXT," +
                USER_POSTAL_FLD + " TEXT," +
                USER_PHONE_FLD + " TEXT, " +
                USER_EMAIL_FLD + " TEXT UNIQUE, " +
                USER_PASSWORD_FLD + " TEXT)"
        );
        // #######################################################
        // FOR DONATIONS
        db.execSQL("CREATE TABLE " + DONATION_TABLE + " (" +
                DONATION_ID_FLD + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DONATION_ITEM_NAME_FLD + " TEXT, " +
                DONATION_QUANTITY_FLD + " INTEGER, " +
                DONATION_CATEGORY_FLD + " TEXT, " +
                DONATION_CATEGORY_SPINNER_FLD + " INTEGER, " +
                DONATION_EXPIRY_DATE_FLD + " DATE, " +
                DONATION_PICKUP_TIME_FLD + " TEXT, " +
                DONATION_PICKUP_TIME_SPINNER_FLD + " INTEGER, " +
                DONATION_OFFER_TYPE_FLD + " TEXT, " +
                DONATION_PRICE_FLD + " FLOAT(10, 2), " +
                DONATION_LOCATION_FLD + " TEXT, " +
                DONATION_STATUS_FLD + " TEXT, " +
                RECIPIENT_FLD + " TEXT)" // That could be null until requested. Hope that doesn't break anything.
        );
        // #######################################################
        // FOR REQUESTS
//        db.execSQL("CREATE TABLE " + REQUEST_HISTORY_TABLE + " (" +
//                REQUEST_HISTORY_ID_FLD + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                REQUEST_ITEM_NAME_FLD + " TEXT, " +
//                REQUEST_DATE_FLD + " TEXT, " +
//                REQUEST_LOCATION_FLD + " TEXT)"
//        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNTS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DONATION_HISTORY_TABLE);
        onCreate(db);
    }

    // ##################################################################################################################
    // FOR CREATING A NEW ACCOUNT RECORD
    public boolean createAccount(String name, String street, String city, String province, String country,
                                 String postal, String phone, String email, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(USER_NAME_FLD, name);
        contentValues.put(USER_STREET_FLD, street);
        contentValues.put(USER_CITY_FLD, city);
        contentValues.put(USER_PROVINCE_FLD, province);
        contentValues.put(USER_COUNTRY_FLD, country);
        contentValues.put(USER_PHONE_FLD, phone);
        contentValues.put(USER_POSTAL_FLD, postal);
        contentValues.put(USER_EMAIL_FLD, email);
        contentValues.put(USER_PASSWORD_FLD, password);

        long result = db.insert(ACCOUNTS_TABLE, null, contentValues);
        return result != -1;
    }

    // ##################################################################################################################
    // FOR CREATING A DONATION RECORD
    public boolean createDonation(String itemName, int quantity, String category, int categorySpinner,
                                  String expiryDate, String pickupTime, int pickupTimeSpinner,
                                  String offerType, float price, String location, String status, String recipient){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DONATION_ITEM_NAME_FLD, itemName);
        contentValues.put(DONATION_QUANTITY_FLD, quantity);
        contentValues.put(DONATION_CATEGORY_FLD, category);
        contentValues.put(DONATION_CATEGORY_SPINNER_FLD, categorySpinner);
        contentValues.put(DONATION_EXPIRY_DATE_FLD, expiryDate);
        contentValues.put(DONATION_PICKUP_TIME_FLD, pickupTime);
        contentValues.put(DONATION_PICKUP_TIME_SPINNER_FLD, pickupTimeSpinner);
        contentValues.put(DONATION_OFFER_TYPE_FLD, offerType);
        contentValues.put(DONATION_PRICE_FLD, price);
        contentValues.put(DONATION_LOCATION_FLD, location);
        contentValues.put(DONATION_STATUS_FLD, status);
        contentValues.put(RECIPIENT_FLD, recipient);

        long result = db.insert(DONATION_HISTORY_TABLE, null, contentValues);
        return result != -1;
    }

    // ##################################################################################################################
    // FOR CREATING A REQUEST RECORD
//    public boolean createRequestHistory(String date, String item, String location){
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//
//        contentValues.put(REQUEST_DATE_FLD, date);
//        contentValues.put(REQUEST_ITEM_NAME_FLD, item);
//        contentValues.put(REQUEST_LOCATION_FLD, location);
//
//        long result = db.insert(REQUEST_HISTORY_TABLE, null, contentValues);
//        return result != -1;
//    }

    // ##################################################################################################################
    // FOR UPDATING AN ACCOUNT RECORD
    public boolean updateAccount(String name, String street, String city, String province, String country,
                                 String postal, String phone, String email, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(USER_NAME_FLD, name);
        contentValues.put(USER_STREET_FLD, street);
        contentValues.put(USER_CITY_FLD, city);
        contentValues.put(USER_PROVINCE_FLD, province);
        contentValues.put(USER_COUNTRY_FLD, country);
        contentValues.put(USER_PHONE_FLD, phone);
        contentValues.put(USER_POSTAL_FLD, postal);
        contentValues.put(USER_EMAIL_FLD, email);
        contentValues.put(USER_PASSWORD_FLD, password);

        int result = db.update(ACCOUNTS_TABLE, contentValues, USER_EMAIL_FLD + " = ?", new String[]{email});
        return result != -1;
    }

    // ##################################################################################################################
    // CHECKING STUFF
     public boolean checkEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + ACCOUNTS_TABLE + " WHERE " + USER_EMAIL_FLD + " = ?", new String[]{email});

        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    public boolean checkLoginCredentials(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + ACCOUNTS_TABLE +
                " WHERE " + USER_EMAIL_FLD + "=? AND " + USER_PASSWORD_FLD + "=?", new String[]{email, password});
        boolean success = cursor.getCount() > 0;
        cursor.close();
        return success;
    }

    // ##################################################################################################################
    // FINDING STUFF
    public Cursor getUserDataByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + ACCOUNTS_TABLE + " WHERE " + USER_EMAIL_FLD + " = ?", new String[]{email});
    }

    public Cursor getAllAccounts(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + ACCOUNTS_TABLE, null);
    }
    public Cursor getAllDonationHistory(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + DONATION_HISTORY_TABLE, null);
    }
}