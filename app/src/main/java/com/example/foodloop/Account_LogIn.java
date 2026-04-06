package com.example.foodloop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Account_LogIn extends AppCompatActivity {

    private CheckBox cbRememberMe;
    private EditText email, password;
    private SharedPreferences sharedPreference, sharedPreferenceRememberMe;
    private static final String SHARED_PREF_NAME = "LOG_IN_CREDENTIALS"; // FOR CHECKING WHO IS SIGNED IN THE REST OF THE APP.
    private static final String SHARED_PREF_REMEMBER_ME = "REMEMBER_ME"; // FOR REMEMBER ME CHECKBOX.



    // DB STUFF
    private DatabaseHelper foodLoopDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        email = findViewById(R.id.userEmail);
        password = findViewById(R.id.userPass);
        cbRememberMe = findViewById(R.id.cbRememberMe);

        sharedPreference = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        sharedPreferenceRememberMe = getSharedPreferences(SHARED_PREF_REMEMBER_ME, MODE_PRIVATE);
        foodLoopDB = new DatabaseHelper(this);

        String savedEmail = sharedPreference.getString("email", "");
        String savedPassword = sharedPreference.getString("password", "");
        boolean isChecked = sharedPreferenceRememberMe.getBoolean("isChecked", false);

        if (isChecked) {
            email.setText(savedEmail);
            password.setText(savedPassword);
            cbRememberMe.setChecked(true);
        }

        // ##################################################################################################################
        // ADD ACCOUNT ONLY IF THERE ARE NO ACCOUNT RECORDS.
        Cursor accountResult = foodLoopDB.getAllAccounts();
        if (accountResult != null) {
            if (accountResult.getCount() == 0) {
                foodLoopDB.createAccount("Belle Mercado", "11 West Street", "Vancouver",
                        "BC", "Canada", 1, "1A1A1A",
                        "6045551111", "belle@mercado.ca", "belle123",
                        "Donor and Requestor", 3);

                foodLoopDB.createAccount("Gia Supetran", "22 South Street", "Surrey",
                        "BC", "Canada", 1, "2B2B2B",
                        "6045552222", "gia@supetran.ca", "gia123",
                        "Donor and Requestor", 3);

                foodLoopDB.createAccount("Nilesh Kurbet", "33 East Street", "Coquitlam",
                        "BC", "Canada", 1, "3C3C3C",
                        "6045553333", "nilesh@kurbet.ca", "nilesh123",
                        "Donor and Requestor", 3);

                foodLoopDB.createAccount("Michael Nguyen", "44 North Street", "Burnaby",
                        "BC", "Canada", 1, "4D4D4D",
                        "6045554444", "michael@nguyen.ca", "michael123",
                        "Donor and Requestor", 3);
            }
            accountResult.close();
        }

        // ADD DONATIONS ONLY IF THERE ARE NO DONATION RECORDS.
        Cursor donationResult = foodLoopDB.getAllDonations();
        if (donationResult != null) {
            if (donationResult.getCount() == 0) {

                foodLoopDB.createDonation("Gia's Yogourt", 4, "Dairy", 4,
                        "2026-06-04", "Discounted", 8.00, "Rejected", 2);

                foodLoopDB.createDonation("Gia's Juice Boxes", 5, "Beverages", 5,
                        "2026-06-05", "Discounted", 5.00, "Rejected", 2);

                foodLoopDB.createDonation("Gia's Soda Cans", 15, "Beverages", 5,
                        "2026-06-06", "Discounted", 15.00, "Rejected", 2);

                foodLoopDB.createDonation("Gia's Frozen Pizza", 6, "Frozen Goods", 6,
                        "2026-06-07", "Discounted", 600.00, "Approved", 2);

                foodLoopDB.createDonation("Gia's Canned Peaches", 7, "Canned Goods", 7,
                        "2026-06-08", "Discounted", 999.99, "Pending", 2);

                foodLoopDB.createDonation("Gia's Canned Tuna", 17, "Canned Goods", 7,
                        "2026-06-09", "Discounted", 1999.99, "Pending", 2);

                foodLoopDB.createDonation("Gia's Cookies", 8, "Bakery", 1,
                        "2026-06-10", "Discounted", 2.00, "Pending", 2);

                foodLoopDB.createDonation("Gia's Grapes", 9, "Produce", 2,
                        "2026-06-11", "Discounted", 0.99, "Pending", 2);

                foodLoopDB.createDonation("Gia's Blueberries", 19, "Produce", 2,
                        "2026-06-12", "Discounted", 1.99, "Pending", 2);

                foodLoopDB.createDonation("Belle's Donut", 1, "Bakery", 1,
                        "2026-06-01", "Free", 0, "Approved", 1);

                foodLoopDB.createDonation("Belle's Apples", 2, "Produce", 2,
                        "2026-06-02", "Free", 0, "Pending", 1);

                foodLoopDB.createDonation("Belle's Chicken Nuggets", 3, "Meat", 3,
                        "2026-06-03", "Free", 0, "Rejected", 1);

                foodLoopDB.createDonation("Michael's Bread", 1, "Bakery", 1,
                        "2026-06-01", "Free", 0, "Approved", 4);

                foodLoopDB.createDonation("Michael's Oranges", 2, "Produce", 2,
                        "2026-06-02", "Free", 0, "Pending", 4);

                foodLoopDB.createDonation("Michael's Bacon", 3, "Meat", 3,
                        "2026-06-03", "Free", 0, "Rejected", 4);

                foodLoopDB.createDonation("Nilesh's Naan", 1, "Bakery", 1,
                        "2026-06-01", "Free", 0, "Approved", 3);

                foodLoopDB.createDonation("Nilesh's Pears", 2, "Produce", 2,
                        "2026-06-02", "Free", 0, "Pending", 3);

                foodLoopDB.createDonation("Nilesh's Steak", 3, "Meat", 3,
                        "2026-06-03", "Free", 0, "Rejected", 3);
            }
            donationResult.close();
        }

        // ADD DONATIONS ONLY IF THERE ARE NO DONATION RECORDS.
        Cursor requestResult = foodLoopDB.getAllRequests();
        if (accountResult != null) {
            if (requestResult.getCount() == 0) {
                // Belle = 1, Gia = 2, Nilesh = 3, Michael = 4

                // Requests for Gia's donations.
                foodLoopDB.createRequest(1, 1, "Evening (5PM-7PM)", 3, "Delivery", "Burnaby, BC");
                foodLoopDB.createRequest(2, 3, "Afternoon (1PM-4PM)", 2, "Pick up", "Coquitlam, BC");
                foodLoopDB.createRequest(3, 4, "Morning (9AM-11AM)", 1, "Pick up", "Vancouver, BC");
                foodLoopDB.createRequest(4, 1, "Morning (9AM-11AM)", 1, "Delivery", "Surrey, BC");
                foodLoopDB.createRequest(5, 3, "Afternoon (1PM-4PM)", 2, "Pick up", "Coquitlam, BC");
                foodLoopDB.createRequest(6, 4, "Evening (5PM-7PM)", 3, "Delivery", "Burnaby, BC");
                foodLoopDB.createRequest(7, 4, "Morning (9AM-11AM)", 1, "Pick up", "Surrey, BC");
                foodLoopDB.createRequest(7, 1, "Afternoon (1PM-4PM)", 2, "Delivery", "Coquitlam, BC");
                foodLoopDB.createRequest(7, 3, "Evening (5PM-7PM)", 3, "Pick up", "Burnaby, BC");
                // No requests for items 8 and 9.

                // Requests for Belle's donations.
                foodLoopDB.createRequest(10, 2, "Morning (9AM-11AM)", 1, "Delivery", "Surrey, BC");
                foodLoopDB.createRequest(12, 3, "Afternoon (1PM-4PM)", 2, "Pick up", "Coquitlam, BC");
                // No requests for item 11.

                // Requests for Michael's donations.
                foodLoopDB.createRequest(13, 1, "Evening (5PM-7PM)", 3, "Delivery", "Burnaby, BC");
                foodLoopDB.createRequest(15, 2, "Morning (9AM-11AM)", 1, "Pick up", "Surrey, BC");
                // No requests for item 14.

                // Requests for Nilesh's donations.
                foodLoopDB.createRequest(16, 4, "Afternoon (1PM-4PM)", 2, "Delivery", "Coquitlam, BC");
                foodLoopDB.createRequest(18, 1, "Evening (5PM-7PM)", 3, "Pick up", "Burnaby, BC");
                // No requests for item 17.
            }
            requestResult.close();
        }
    }

    // ##################################################################################################################
    // Changed setOnClickListeners to methods for onClick attributes, to clean up onCreate before I mess it up more. - Michael
    public void login(View view) {
        String userEmail = email.getText().toString();
        String userPass = password.getText().toString();

        if (TextUtils.isEmpty(userEmail)) {
            email.setError("Username cannot be empty");
            Toast.makeText(Account_LogIn.this, "Please enter your username", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(userPass)) {
            password.setError("Password cannot be empty");
            Toast.makeText(Account_LogIn.this, "Please enter your password", Toast.LENGTH_LONG).show();
        } else {
            boolean emailExists = foodLoopDB.checkEmailExists(userEmail);

            if (!emailExists) { // CHECK FOR EXISTING ACCOUNT THEN FOR MATCHING PASSWORD
                email.setError("This email isn't in the database.");
                Toast.makeText(this, "Account not found.", Toast.LENGTH_LONG).show();
            } else if (!foodLoopDB.checkLoginCredentials(userEmail, userPass)) {
                password.setError("Wrong!");
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences.Editor editorCredentials = sharedPreference.edit();
                SharedPreferences.Editor editorRememberMe = sharedPreferenceRememberMe.edit();

                editorCredentials.putString("email", userEmail);
                editorCredentials.putString("password", userPass);
                editorCredentials.apply();

                if (cbRememberMe.isChecked()) {
                    editorRememberMe.putBoolean("isChecked", true);
                } else {
                    editorRememberMe.clear();
                }
                editorRememberMe.apply();

                Toast.makeText(Account_LogIn.this, "Log in Successful!!!", Toast.LENGTH_LONG).show();
                Cursor userTypeCursor = foodLoopDB.getUserDataByEmail(userEmail);
//                if (userTypeCursor != null) {
//                    if (userTypeCursor.moveToFirst()) {
//                        int type = userType.getInt(userTypeCursor.getColumnIndexOrThrow // TODO: Add a user entry in the CreateAccount page.
//                                (DatabaseHelper.USER_TYPE_FLD)); // TODO: Add a user type column to the User table in the DatabaseHelper.
//                        switch (type) {
//                            case 1:
//                                startActivity(new Intent(LogInScreen.this, DonationHomePage.class));
//                                break;
//                            case 2:
//                                startActivity(new Intent(LogInScreen.this, Request_Home_Page.class));
//                                break;
//                            case 3:
//                                startActivity(new Intent(LogInScreen.this, Both_Pages.class)); // TODO: Make this page.
//                                break;
//                            default:
//                                break;
//                        }
//                        finish();
//                    }
//                    userTypeCursor.close();
//                }
                startActivity(new Intent(Account_LogIn.this, App_Home.class));
                // As of 3/30, updated to Donation Home Page for now -Gia
                // Change this to the Home page when that's up.
            }
        }
    }

    public void toCreateAccount(View view) {
        startActivity(new Intent(Account_LogIn.this, Account_Create.class));
    }
}