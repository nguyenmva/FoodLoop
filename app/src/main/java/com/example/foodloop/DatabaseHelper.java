package com.example.foodloop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "FoodLoop.DB";

    // ##################################################################################################################
    // USER ACCOUNT TABLE
    public static final String ACCOUNTS_TABLE = "Accounts";
    public static final String USER_ID_FLD = "UserID";
    public static final String USER_NAME_FLD = "Name";
    public static final String USER_STREET_FLD = "Street";
    public static final String USER_CITY_FLD = "City";
    public static final String USER_PROVINCE_FLD = "Province";
    public static final String USER_COUNTRY_FLD = "Country";
    public static final String USER_COUNTRY_SPINNER_FLD = "CountrySpinnerIndex";
    public static final String USER_POSTAL_FLD = "PostalCode";
    public static final String USER_PHONE_FLD = "ContactNumber";
    public static final String USER_EMAIL_FLD = "EmailAddress";
    public static final String USER_PASSWORD_FLD = "Password";

    // ##################################################################################################################
    // DONATIONS TABLE
    public static final String DONATION_TABLE = "Donations";
    public static final String DONATION_ID_FLD = "DonationID";
    public static final String DONATION_ITEM_NAME_FLD = "ItemName";
    public static final String DONATION_QUANTITY_FLD = "Quantity";
    public static final String DONATION_CATEGORY_FLD = "Category";
    public static final String DONATION_CATEGORY_SPINNER_FLD = "CategorySpinnerIndex";
    public static final String DONATION_EXPIRY_DATE_FLD = "ExpiryDate";
    public static final String DONATION_PICKUP_TIME_FLD = "PickupTime";
    public static final String DONATION_PICKUP_TIME_SPINNER_FLD = "PickupTimeSpinnerIndex";
    public static final String DONATION_OFFER_TYPE_FLD = "OfferType";
    public static final String DONATION_PRICE_FLD = "Price";
    public static final String DONATION_LOCATION_FLD = "Location";
    public static final String DONATION_STATUS_FLD = "Status";
    public static final String DONOR_ID_FLD = "Donor";
    public static final String RECIPIENT_ID_FLD = "Recipient";

    // ##################################################################################################################
    // HISTORY TABLE
    public static final String HISTORY_TABLE = "History";
    public static final String HISTORY_ID_FLD = "HistoryID";
    public static final String HISTORY_DATE_FLD = "Date";
    public static final String HISTORY_ITEM_NAME_FLD = "ItemName";
    public static final String HISTORY_LOCATION_FLD = "Location";

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
                USER_COUNTRY_SPINNER_FLD + " INTEGER," +
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
                DONATION_PRICE_FLD + " DOUBLE, " +
                DONATION_LOCATION_FLD + " TEXT, " +
                DONATION_STATUS_FLD + " TEXT, " +
                DONOR_ID_FLD + " INTEGER, " +
                RECIPIENT_ID_FLD + " INTEGER, " + // That could be null until requested. Hope that doesn't break anything.
                "FOREIGN KEY (" + DONOR_ID_FLD + ") REFERENCES " + ACCOUNTS_TABLE + "(" + USER_ID_FLD + "), " +
                "FOREIGN KEY (" + RECIPIENT_ID_FLD + ") REFERENCES " + ACCOUNTS_TABLE + "(" + USER_ID_FLD + ")" +
                ")"
        );
        // #######################################################
        // FOR HISTORY
//        db.execSQL("CREATE TABLE " + HISTORY_TABLE + " (" +
//                HISTORY_ID_FLD + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                HISTORY_ITEM_NAME_FLD + " TEXT, " +
//                HISTORY_DATE_FLD + " TEXT, " +
//                HISTORY_LOCATION_FLD + " TEXT)"
//        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNTS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DONATION_TABLE);
        onCreate(db);
    }

    // ##################################################################################################################
    // FOR CREATING A NEW ACCOUNT RECORD
    public boolean createAccount(String name, String street, String city, String province, String country,
                                 int countrySpinner, String postal, String phone, String email, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(USER_NAME_FLD, name);
        contentValues.put(USER_STREET_FLD, street);
        contentValues.put(USER_CITY_FLD, city);
        contentValues.put(USER_PROVINCE_FLD, province);
        contentValues.put(USER_COUNTRY_FLD, country);
        contentValues.put(USER_COUNTRY_SPINNER_FLD, countrySpinner);
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
                                  String expiryDate, String pickupTime, int pickupTimeSpinner, String offerType,
                                   double price, String location, String status, int donor){

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
        contentValues.put(DONOR_ID_FLD, donor);

        long result = db.insert(DONATION_TABLE, null, contentValues);
        return result != -1;
    }

    // ##################################################################################################################
    // FOR CREATING A DONATION DEMO RECORD, ADDS RECIPIENT TO DONATION CREATION FOR TESTING.
    public void createDonationDemo(String itemName, int quantity, String category, int categorySpinner,
                                  String expiryDate, String pickupTime, int pickupTimeSpinner, String offerType,
                                  double price, String location, String status, int donor, int recipient){

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
        contentValues.put(DONOR_ID_FLD, donor);
        contentValues.put(RECIPIENT_ID_FLD, recipient);

        long result = db.insert(DONATION_TABLE, null, contentValues);
    }

    // ##################################################################################################################
    // FOR CREATING A HISTORY RECORD
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
                                 int countrySpinner, String postal, String phone, String email, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(USER_NAME_FLD, name);
        contentValues.put(USER_STREET_FLD, street);
        contentValues.put(USER_CITY_FLD, city);
        contentValues.put(USER_PROVINCE_FLD, province);
        contentValues.put(USER_COUNTRY_FLD, country);
        contentValues.put(USER_COUNTRY_SPINNER_FLD, countrySpinner);
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
    public Cursor getUserDataByID(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + ACCOUNTS_TABLE + " WHERE " + USER_ID_FLD + " = ?", new String[]{id});
    }
    public Cursor getDonationByDonorID(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + DONATION_TABLE + " WHERE " + DONOR_ID_FLD + " = ?", new String[]{id});
    }
    public Cursor getDonationByItemSearch(String itemSearch) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + DONATION_TABLE + " WHERE " + DONATION_ITEM_NAME_FLD + " LIKE ?", new String[]{"%" + itemSearch + "%"});
    }
    public Cursor getDonationByLocationSearch(String locationSearch) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + DONATION_TABLE + " WHERE " + DONATION_LOCATION_FLD + " LIKE ?", new String[]{"%" + locationSearch + "%"});
    }


    public Cursor getAllAccounts(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + ACCOUNTS_TABLE, null);
    }
    public Cursor getAllDonations(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + DONATION_TABLE, null);
    }
}