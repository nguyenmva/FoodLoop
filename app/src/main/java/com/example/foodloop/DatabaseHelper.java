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
    public static final String NAME_FLD = "NAME";
    public static final String STREET_FLD = "STREET";
    public static final String CITY_FLD = "CITY";
    public static final String PROVINCE_FLD = "PROVINCE";
    public static final String COUNTRY_FLD = "COUNTRY";
    public static final String POSTAL_FLD = "POSTAL";
    public static final String PHONE_FLD = "PHONE";
    public static final String EMAIL_FLD = "EMAIL";
    public static final String PASSWORD_FLD = "PASSWORD";

    // ##################################################################################################################
    // DONATION HISTORY TABLE
    public static final String DONATION_HISTORY_TABLE = "DonationHistory";
    public static final String DONATION_HISTORY_ID_FLD = "DONATION_HISTORY_ID";
    public static final String STATUS_FLD = "STATUS";
    public static final String DONATION_ITEM_NAME_FLD = "DONATION_ITEM_NAME";
    public static final String RECIPIENT_FLD = "RECIPIENT";

    // ##################################################################################################################
    // REQUEST HISTORY TABLE
    public static final String REQUEST_HISTORY_TABLE = "DonationHistory";
    public static final String REQUEST_HISTORY_ID_FLD = "REQUEST_HISTORY_ID";
    public static final String REQUEST_DATE_FLD = "REQUEST_DATE";
    public static final String REQUEST_ITEM_NAME_FLD = "REQUEST_ITEM_NAME";
    public static final String REQUEST_LOCATION_FLD = "REQUEST_LOCATION";

    // ##################################################################################################################
    // CONSTRUCTOR
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    // ##################################################################################################################
    // CREATE THE TABLE ("SQL COMMAND " + TABLE_NAME + " (" + FIELD1_NAME DATATYPE," + "FIELD2_NAME DATATYPE)"
    @Override
    public void onCreate(SQLiteDatabase db){
        // #######################################################
        // FOR ACCOUNTS
        db.execSQL("CREATE TABLE " + ACCOUNTS_TABLE +
                " (USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, STREET TEXT, CITY TEXT, PROVINCE TEXT, COUNTRY TEXT," +
                "POSTAL TEXT, PHONE TEXT, EMAIL TEXT UNIQUE, PASSWORD TEXT)"
        );
        // #######################################################
        // FOR DONATION HISTORY
        db.execSQL("CREATE TABLE " + DONATION_HISTORY_TABLE +
                " (DONATION_HISTORY_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "STATUS TEXT, DONATION_ITEM_NAME TEXT, RECIPIENT TEXT)"
        );
        // #######################################################
        // FOR REQUEST HISTORY
        db.execSQL("CREATE TABLE " + REQUEST_HISTORY_TABLE +
                " (REQUEST_HISTORY_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "REQUEST_DATE TEXT, REQUEST_ITEM_NAME TEXT, REQUEST_LOCATION)"
        );
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

        contentValues.put(NAME_FLD, name);
        contentValues.put(STREET_FLD, street);
        contentValues.put(CITY_FLD, city);
        contentValues.put(PROVINCE_FLD, province);
        contentValues.put(COUNTRY_FLD, country);
        contentValues.put(PHONE_FLD, phone);
        contentValues.put(POSTAL_FLD, postal);
        contentValues.put(EMAIL_FLD, email);
        contentValues.put(PASSWORD_FLD, password);

        long result = db.insert(ACCOUNTS_TABLE, null, contentValues);

        return result != -1;
    }
    // ##################################################################################################################
    // FOR CREATING A DONATION HISTORY RECORD
    public boolean createDonationHistory(String status, String item, String recipient){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(STATUS_FLD, status);
        contentValues.put(DONATION_ITEM_NAME_FLD, item);
        contentValues.put(RECIPIENT_FLD, recipient);

        long result = db.insert(DONATION_HISTORY_TABLE, null, contentValues);
        db.close();
        return result != -1;
    }
    // ##################################################################################################################
    // FOR CREATING A REQUEST HISTORY RECORD
    public boolean createRequestHistory(String date, String item, String location){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(REQUEST_DATE_FLD, date);
        contentValues.put(REQUEST_ITEM_NAME_FLD, item);
        contentValues.put(REQUEST_LOCATION_FLD, location);

        long result = db.insert(REQUEST_HISTORY_TABLE, null, contentValues);
        db.close();
        return result != -1;
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