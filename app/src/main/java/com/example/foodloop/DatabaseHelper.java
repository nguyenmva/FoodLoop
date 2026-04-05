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
    public static final String USERS_TABLE = "Users";
    public static final String USER_ID_FLD = "UserID";
    public static final String USER_ACCOUNT_TYPE_FLD = "AccountType";
    public static final String USER_ACCOUNT_TYPE_SPINNER_FLD = "AccountTypeSpinnerIndex";
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
    public static final String DONATION_OFFER_TYPE_FLD = "OfferType";
    public static final String DONATION_PRICE_FLD = "Price";
    public static final String DONATION_STATUS_FLD = "Status";
    public static final String DONOR_ID_FLD = "Donor";
    // ##################################################################################################################
    // REQUESTS TABLE
    public static final String REQUEST_TABLE = "Requests";
    public static final String REQUEST_ID_FLD = "RequestID";
    public static final String REQUESTOR_ID_FLD = "Requestor";
    public static final String REQUEST_PICKUP_TIME_FLD = "PickupTime";
    public static final String REQUEST_PICKUP_TIME_SPINNER_FLD = "PickupTimeSpinnerIndex";
    public static final String REQUEST_PICKUP_DATE_FLD = "PickupDate";
    public static final String REQUEST_COLLECTION_TYPE_FLD = "CollectionType";
    public static final String REQUEST_LOCATION_FLD = "Location";
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
        db.execSQL("CREATE TABLE " + USERS_TABLE + " (" +
                USER_ID_FLD + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_ACCOUNT_TYPE_FLD + " TEXT, " +
                USER_ACCOUNT_TYPE_SPINNER_FLD + " INTEGER, " +
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
                DONATION_OFFER_TYPE_FLD + " TEXT, " +
                DONATION_PRICE_FLD + " DOUBLE, " +
                DONATION_STATUS_FLD + " TEXT, " +
                DONOR_ID_FLD + " INTEGER, " +
                "FOREIGN KEY (" + DONOR_ID_FLD + ") REFERENCES " + USERS_TABLE + "(" + USER_ID_FLD + ") " +
                ")"
        );
        // #######################################################
        // FOR REQUESTS
        db.execSQL("CREATE TABLE " + REQUEST_TABLE + " (" +
                REQUEST_ID_FLD + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DONATION_ID_FLD + " INTEGER, " +
                REQUESTOR_ID_FLD + " INTEGER, " +
                REQUEST_PICKUP_TIME_FLD + " TEXT, " +
                REQUEST_PICKUP_TIME_SPINNER_FLD + " INTEGER, " +
                REQUEST_PICKUP_DATE_FLD + " DATE, " +
                REQUEST_COLLECTION_TYPE_FLD + " TEXT, " +
                REQUEST_LOCATION_FLD + " TEXT, " +
                "FOREIGN KEY (" + DONATION_ID_FLD + ") REFERENCES " + DONATION_TABLE + "(" + DONATION_ID_FLD + "), " +
                "FOREIGN KEY (" + REQUESTOR_ID_FLD + ") REFERENCES " + USERS_TABLE + "(" + USER_ID_FLD + ")" +
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
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DONATION_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + REQUEST_TABLE);
        onCreate(db);
    }

    // ##################################################################################################################
    // FOR CREATING A NEW ACCOUNT RECORD
    public boolean createAccount(String name, String street, String city, String province,
                                 String country, int countrySpinner, String postal, String phone,
                                 String email, String password, String accountType, int accountTypeSpinner ){

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
        contentValues.put(USER_ACCOUNT_TYPE_FLD, accountType);
        contentValues.put(USER_ACCOUNT_TYPE_SPINNER_FLD, accountTypeSpinner);

        long result = db.insert(USERS_TABLE, null, contentValues);
        return result != -1;
    }

    // ##################################################################################################################
    // FOR CREATING A DONATION RECORD
    public boolean createDonation(String itemName, int quantity, String category, int categorySpinner,
                                  String expiryDate, String offerType, double price, String status, int donor){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DONATION_ITEM_NAME_FLD, itemName);
        contentValues.put(DONATION_QUANTITY_FLD, quantity);
        contentValues.put(DONATION_CATEGORY_FLD, category);
        contentValues.put(DONATION_CATEGORY_SPINNER_FLD, categorySpinner);
        contentValues.put(DONATION_EXPIRY_DATE_FLD, expiryDate);
        contentValues.put(DONATION_OFFER_TYPE_FLD, offerType);
        contentValues.put(DONATION_PRICE_FLD, price);
        contentValues.put(DONATION_STATUS_FLD, status);
        contentValues.put(DONOR_ID_FLD, donor);

        long result = db.insert(DONATION_TABLE, null, contentValues);
        return result != -1;
    }

    // ##################################################################################################################
    // FOR CREATING A REQUEST RECORD
    public boolean createRequest(int donationID, int requestorID, String pickupTime, int pickupTimeSpinner, String collectionType,String location){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DONATION_ID_FLD, donationID);
        contentValues.put(REQUESTOR_ID_FLD, requestorID);
        contentValues.put(REQUEST_PICKUP_TIME_FLD, pickupTime);
        contentValues.put(REQUEST_PICKUP_TIME_SPINNER_FLD, pickupTimeSpinner);
        contentValues.put(REQUEST_PICKUP_DATE_FLD, "2026-06-01");
        contentValues.put(REQUEST_COLLECTION_TYPE_FLD, collectionType);
        contentValues.put(REQUEST_LOCATION_FLD, location);
        // PICKUP = DONOR'S CITY
        // DELIVERY = RECIPIENT'S CITY

        long result = db.insert(REQUEST_TABLE, null, contentValues);
        return result != -1;
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

    // GET REQUEST HISTORY - Gia
    public Cursor getRequestHistory(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT Donations." + DONATION_EXPIRY_DATE_FLD + ", " +
                        "Donations." + DONATION_ITEM_NAME_FLD + ", " +
                        "Requests." + REQUEST_LOCATION_FLD +
                        " FROM " + REQUEST_TABLE +
                        " JOIN " + DONATION_TABLE + " ON Requests." + DONATION_ID_FLD + " = Donations." + DONATION_ID_FLD +
                        " JOIN " + USERS_TABLE + " ON Requests." + REQUESTOR_ID_FLD + " = Users." + USER_ID_FLD +
                        " WHERE Users." + USER_EMAIL_FLD + " = ?", new String[]{email});
    }

    // GET DONATION HISTORY - Gia
    public Cursor getDonationHistory(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT Donations." + DONATION_STATUS_FLD + ", " +
                        "Donations." + DONATION_ITEM_NAME_FLD + ", " +
                        "Requestor." + USER_NAME_FLD + " AS RequestorName" +
                        " FROM " + DONATION_TABLE +
                        " JOIN " + REQUEST_TABLE + " ON Donations." + DONATION_ID_FLD + " = Requests." + DONATION_ID_FLD +
                        " JOIN " + USERS_TABLE + " Requestor ON Requests." + REQUESTOR_ID_FLD + " = Requestor." + USER_ID_FLD +
                        " JOIN " + USERS_TABLE + " Donor ON Donations." + DONOR_ID_FLD + " = Donor." + USER_ID_FLD +
                        " WHERE Donor." + USER_EMAIL_FLD + " = ?", new String[]{email});
    }

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

        int result = db.update(USERS_TABLE, contentValues, USER_EMAIL_FLD + " = ?", new String[]{email});
        return result != -1;
    }
    // ##################################################################################################################
    // FOR EDITING A DONATION THAT IS ALREADY LISTED
    public boolean editDonation(int donationID, String itemName, int quantity, String category, int categorySpinner,
                                String expiryDate, String offerType, double price){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DONATION_ITEM_NAME_FLD, itemName);
        contentValues.put(DONATION_QUANTITY_FLD, quantity);
        contentValues.put(DONATION_CATEGORY_FLD, category);
        contentValues.put(DONATION_CATEGORY_SPINNER_FLD, categorySpinner);
        contentValues.put(DONATION_EXPIRY_DATE_FLD, expiryDate);
        contentValues.put(DONATION_OFFER_TYPE_FLD, offerType);
        contentValues.put(DONATION_PRICE_FLD, price);

        long result = db.update(DONATION_TABLE, contentValues, DONATION_ID_FLD + " = ? ", new String[]{String.valueOf(donationID)});

        return result > 0; //This will return true if at least 1 row was edited
    }

    // ##################################################################################################################
    // FOR UPDATING A DONATION'S STATUS
    public boolean updateDonationStatus(String donationID, String status) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DONATION_STATUS_FLD, status);

        int result = db.update(DONATION_TABLE, values, DONATION_ID_FLD + " = ?", new String[]{donationID});
        return result > 0;
    }

    // ##################################################################################################################
    // FOR CHECKING STUFF
     public boolean checkEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
        "SELECT *" +
                " FROM " + USERS_TABLE +
                " WHERE " + USER_EMAIL_FLD + " = ?", new String[]{email});

        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    public boolean checkLoginCredentials(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
        "SELECT *" +
                " FROM " + USERS_TABLE +
                " WHERE " + USER_EMAIL_FLD + "=? AND " + USER_PASSWORD_FLD + "=?", new String[]{email, password});
        boolean success = cursor.getCount() > 0;
        cursor.close();
        return success;
    }

    // ##################################################################################################################
    // FOR FINDING STUFF
    public Cursor getUserDataByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
        "SELECT *" +
                " FROM " + USERS_TABLE +
                " WHERE " + USER_EMAIL_FLD + " = ?", new String[]{email});
    }
    public Cursor getUserDataByUserID(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
        "SELECT *" +
                " FROM " + USERS_TABLE +
                " WHERE " + USER_ID_FLD + " = ?", new String[]{id});
    }
    public Cursor getDonationByDonorID(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
        "SELECT *" +
                " FROM " + DONATION_TABLE +
                " WHERE " + DONOR_ID_FLD + " = ?", new String[]{id});
    }
    public Cursor getDonationByRequestorID(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
        "SELECT *" +
                " FROM " + REQUEST_TABLE +
                " WHERE " + REQUESTOR_ID_FLD + " = ?", new String[]{id});
    }
    public Cursor getDonationByDonationID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
        "SELECT *" +
                " FROM " + DONATION_TABLE +
                " WHERE " + DONATION_ID_FLD + " = ?", new String[]{String.valueOf(id)});
    }
    public Cursor getActiveDonations(String userEmail) { // Shows only donations that have a request.
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
        "SELECT Donations." + DONATION_ID_FLD + ", " + DONATION_STATUS_FLD + ", Donations." + DONATION_ITEM_NAME_FLD +
                ", Requestor." + USER_NAME_FLD + " AS RequestorName " + ", Donor." + USER_CITY_FLD + " AS DonorLocation" +
                " FROM " + DONATION_TABLE +
                " JOIN " + REQUEST_TABLE + " ON Donations." + DONATION_ID_FLD + " = Requests." + DONATION_ID_FLD +      // What got requested?
                " JOIN " + USERS_TABLE + " Requestor ON Requests." + REQUESTOR_ID_FLD + " = Requestor." + USER_ID_FLD + // Who requested it?
                " JOIN " + USERS_TABLE + " Donor ON Donations." + DONOR_ID_FLD + " = Donor." + USER_ID_FLD +            // Who donated it (the one who's logged in)?
                " WHERE Donor." + USER_EMAIL_FLD + " = ?", new String[]{userEmail});
        /*
        SELECT Donations.Status, Donations.ItemName, Requestor.Name (Using ALIAS "RequestorName")
        FROM Donations
        JOIN Requests ON Donations.DonationID = Requests.DonationID
        JOIN Users Requestor ON Requests.RequestorID = Requestor.UserID
        JOIN Users Donor ON Donations.DonorID = Donor.UserID
        WHERE Donor.EmailAddress = ?
         */
    }
    public Cursor getActiveRequests(String userEmail) { //
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
        "SELECT Donations." + DONATION_STATUS_FLD + ", Donations." + DONATION_ITEM_NAME_FLD + ", " + REQUEST_LOCATION_FLD +
                " FROM " + REQUEST_TABLE +
                " JOIN " + DONATION_TABLE + " ON Requests." + DONATION_ID_FLD + " = Donations." + DONATION_ID_FLD +
                " JOIN " + USERS_TABLE + " ON Requests." + REQUESTOR_ID_FLD + " = Users." + USER_ID_FLD +
                " WHERE Users." + USER_EMAIL_FLD + " = ?", new String[]{userEmail});
        /*
        SELECT Donations.Status, Donations.ItemName, Donations.Location
        FROM Requests
        JOIN Donations ON Requests.DonationID = Donations.DonationID
        JOIN Users ON Requests.RequestorID = Users.UserID
        WHERE Users.EmailAddress = ?

        WHERE the email in the Users table matches the input string (from sharedPreferences),
        Get the selected columns from selected tables (SELECT table1.columnC, table2.columnA)
        Where UserIDs match on the Requests and Donation tables.
        And where the UserIDs match on the Requests and the Users Table.
         */
    }
    public Cursor getDonationByItemSearch(String itemSearch) {
        SQLiteDatabase db = this.getReadableDatabase();
        // JOIN on EmailAddress because the 'Donor' field stores the user's email
        String query = "SELECT D.*, U." + USER_NAME_FLD + ", U." + USER_CITY_FLD +
                " FROM " + DONATION_TABLE + " D" +
                " JOIN " + USERS_TABLE + " U ON D." + DONATION_ID_FLD + " = U." + USER_EMAIL_FLD +
                " WHERE D." + DONATION_ITEM_NAME_FLD + " LIKE ?";

        return db.rawQuery(query, new String[]{"%" + itemSearch + "%"});
    }
    public Cursor getDonationByLocationSearch(String locationSearch) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
        "SELECT *" +
                " FROM " + DONATION_TABLE +
                " WHERE " + REQUEST_LOCATION_FLD + " LIKE ?", new String[]{"%" + locationSearch + "%"});
    }

    //Nilesh -Works
    public Cursor getDonationsWithDonorInfo(String itemSearch) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT D.*, U." + USER_NAME_FLD + ", U." + USER_CITY_FLD +
                " FROM " + DONATION_TABLE + " D" +
                " JOIN " + USERS_TABLE + " U ON D." + DONOR_ID_FLD + " = U." + USER_ID_FLD +
                " WHERE D." + DONATION_ITEM_NAME_FLD + " LIKE ?";
        return db.rawQuery(query, new String[]{"%" + itemSearch + "%"});
    }

    public Cursor getAllAccounts(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
        "SELECT *" +
                " FROM " + USERS_TABLE, null);
    }
    public Cursor getAllDonations(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
        "SELECT *" +
                " FROM " + DONATION_TABLE, null);
    }
    public Cursor getAllRequests(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
        "SELECT *" +
                " FROM " + REQUEST_TABLE, null);
    }

    public Cursor getAllDonationsFromCurrentUser(String userEmail) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT D." + DONATION_ID_FLD + ", " +
                        "D." + DONATION_STATUS_FLD + ", " +
                        "D." + DONATION_ITEM_NAME_FLD + ", " +
                        "U." + USER_NAME_FLD + " AS RequestorName " +
                        "FROM " + DONATION_TABLE + " D " +
                        "LEFT JOIN " + REQUEST_TABLE + " R ON D." + DONATION_ID_FLD + " = R." + DONATION_ID_FLD + " " +
                        "LEFT JOIN " + USERS_TABLE + " U ON R." + REQUESTOR_ID_FLD + " = U." + USER_ID_FLD + " " +
                        "LEFT JOIN " + USERS_TABLE + " Donor ON D." + DONOR_ID_FLD + " = Donor." + USER_ID_FLD + " " +
                        "WHERE Donor." + USER_EMAIL_FLD + " = ?", new String[]{userEmail});
    }

    //NIlesh Test
    // Method to search by name OR category
    public Cursor searchDonations(String searchText) {
        SQLiteDatabase db = this.getReadableDatabase();
        // JOIN with Users table to get Donor name and location
        String query = "SELECT D.*, U." + USER_NAME_FLD + ", U." + USER_CITY_FLD +
                " FROM " + DONATION_TABLE + " D" +
                " JOIN " + USERS_TABLE + " U ON D." + DONOR_ID_FLD + " = U." + USER_ID_FLD +
                " WHERE (D." + DONATION_ITEM_NAME_FLD + " LIKE ? OR D." + DONATION_CATEGORY_FLD + " LIKE ?) " +
                " AND D." + DONATION_STATUS_FLD + " = 'Available'";

        String wildCard = "%" + searchText + "%";
        return db.rawQuery(query, new String[]{wildCard, wildCard});
    }

    // Method to fetch confirmed requests for a specific user
    public Cursor getConfirmedRequests(String userEmail) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT D." + DONATION_ITEM_NAME_FLD + ", R." + REQUEST_PICKUP_DATE_FLD + ", R." + REQUEST_LOCATION_FLD +
                        " FROM " + REQUEST_TABLE + " R" +
                        " JOIN " + DONATION_TABLE + " D ON R." + DONATION_ID_FLD + " = D." + DONATION_ID_FLD +
                        " JOIN " + USERS_TABLE + " U ON R." + REQUESTOR_ID_FLD + " = U." + USER_ID_FLD +
                        " WHERE U." + USER_EMAIL_FLD + " = ?", new String[]{userEmail});
    }
}