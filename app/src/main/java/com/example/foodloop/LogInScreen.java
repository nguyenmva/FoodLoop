package com.example.foodloop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LogInScreen extends AppCompatActivity {

    private CheckBox cbRememberMe;
    private EditText email, password;
    private SharedPreferences sharedPreference;
    private static final String SHARED_PREF_NAME = "LOG_IN_CREDENTIALS";


    // DB STUFF
    private DatabaseHelper foodLoopDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        email = findViewById(R.id.userEmail);
        password = findViewById(R.id.userPass);
        cbRememberMe = findViewById(R.id.cbRememberMe);

        sharedPreference = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        foodLoopDB = new DatabaseHelper(this);

        String savedEmail  = sharedPreference.getString("email", "");
        String savedPassword = sharedPreference.getString("password", "");

        boolean isChecked = sharedPreference.getBoolean("isChecked", false);

        if (isChecked){
            email.setText(savedEmail);
            password.setText(savedPassword);
            cbRememberMe.setChecked(true);
        }

        // ##################################################################################################################
        // ADD ACCOUNT ONLY IF THERE ARE NO ACCOUNT RECORDS.
        Cursor accountResult = foodLoopDB.getAllAccounts();
        if(accountResult.getCount() == 0) {
            foodLoopDB.createAccount("Belle Mercado", "11 West Street", "Vancouver",
                    "BC", "Canada", 1, "1A1A1A",
                    "6045551111", "belle@mercado.ca", "belle123");

            foodLoopDB.createAccount("Gia Supetran", "22 South Street", "Surrey",
                    "BC", "Philippines", 2, "2B2B2B",
                    "6045552222", "gia@supetran.ca", "gia123");

            foodLoopDB.createAccount("Nilesh Kurbet", "33 East Street", "Coquitlam",
                    "BC", "Pakistan", 3, "3C3C3C",
                    "6045553333", "nilesh@kurbet.ca", "nilesh123");

            foodLoopDB.createAccount("Michael Nguyen", "44 North Street", "Burnaby",
                    "BC", "India", 4, "4D4D4D",
                    "6045554444", "michael@nguyen.ca", "michael123");
        }
        accountResult.close();

        // ONLY DONATIONS ONLY IF THERE ARE NO DONATION RECORDS.
        Cursor donationResult = foodLoopDB.getAllDonations();
        if(donationResult.getCount() == 0) {
            foodLoopDB.createDonationDemo("Muffin", 1, "Bakery", 1,
                    "2026-04-01", "Morning (9AM-11AM)", 1, "Free", 0,
                    "Vancouver", "Pending", 1, 2); // Belle to Gia

            foodLoopDB.createDonationDemo("Apples", 2, "Produce", 2,
                    "2026-04-02", "Afternoon (1PM-4PM)", 2, "Free", 0,
                    "Vancouver", "Pending", 1, 3); // Belle to Nilesh

            foodLoopDB.createDonationDemo("Chicken Nuggets", 3, "Meat", 3,
                    "2026-04-03", "Evening (5PM-7PM)", 3, "Free", 0,
                    "Vancouver", "Pending", 1, 3); // Belle to Michael

            foodLoopDB.createDonationDemo("Yogourt", 4, "Dairy", 4,
                    "2026-04-04", "Morning (9AM-11AM)", 1, "Discounted", 8.00,
                    "Surrey", "Pending", 2, 1); // Gia to Belle

            foodLoopDB.createDonationDemo("Juice Boxes", 5, "Beverages", 5,
                    "2026-04-05", "Afternoon (1PM-4PM)", 2, "Discounted", 5.00,
                    "Surrey", "Pending", 2, 1); // Gia to Belle

            foodLoopDB.createDonationDemo("Soda Cans", 15, "Beverages", 5,
                    "2026-04-06", "Evening (5PM-7PM)", 3, "Discounted", 15.00,
                    "Surrey", "Pending", 2, 1); // Gia to Belle

            foodLoopDB.createDonationDemo("Frozen Pizza", 6, "Frozen Goods", 6,
                    "2026-04-07", "Morning (9AM-11AM)", 1, "Discounted", 600.00,
                    "Surrey", "Approved", 2, 3); // Gia to Nilesh

            foodLoopDB.createDonationDemo("Canned Peaches", 7, "Canned Goods", 7,
                    "2026-04-08", "Afternoon (1PM-4PM)", 2, "Discounted", 999.99,
                    "Surrey", "Rejected", 2, 3); // Gia to Nilesh

            foodLoopDB.createDonationDemo("Canned Tuna", 17, "Canned Goods", 7,
                    "2026-04-09", "Evening (5PM-7PM)", 3, "Discounted", 1999.99,
                    "Surrey", "Rejected", 2, 3); // Gia to Nilesh

            foodLoopDB.createDonationDemo("Cookies", 8, "Bakery", 1,
                    "2026-04-10", "Morning (9AM-11AM)", 1, "Discounted", 2.00,
                    "Surrey", "Approved", 2, 4); // Gia to Michael

            foodLoopDB.createDonationDemo("Grapes", 9, "Produce", 2,
                    "2026-04-11", "Afternoon (1PM-4PM)", 2, "Discounted", 0.99,
                    "Surrey", "Rejected", 2, 4); // Gia to Michael

            foodLoopDB.createDonationDemo("Blueberries", 19, "Produce", 2,
                    "2026-04-12", "Evening (5PM-7PM)", 3, "Discounted", 1.99,
                    "Surrey", "Rejected", 2, 4); // Gia to Michael
        }
        donationResult.close();
    }

    // ##################################################################################################################
    // Changed setOnClickListeners to methods for onClick attributes, to clean up onCreate before I mess it up more. - Michael
    public void login(View view) {
        String userEmail = email.getText().toString();
        String userPass = password.getText().toString();

        if(TextUtils.isEmpty(userEmail)){
            email.setError("Username cannot be empty");
            Toast.makeText(LogInScreen.this, "Please enter your username", Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(userPass)){
            password.setError("Password cannot be empty");
            Toast.makeText(LogInScreen.this, "Please enter your password", Toast.LENGTH_LONG).show();
        }
            else{
                boolean emailExists = foodLoopDB.checkEmailExists(userEmail);

                if (!emailExists) { // CHECK FOR EXISTING ACCOUNT THEN FOR MATCHING PASSWORD
                    email.setError("This email isn't in the database.");
                    Toast.makeText(this, "Account not found.", Toast.LENGTH_LONG).show();
                }
                else if (!foodLoopDB.checkLoginCredentials(userEmail, userPass)) {
                    password.setError("Wrong!");
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
        else {
            SharedPreferences.Editor editor = sharedPreference.edit();

            if (cbRememberMe.isChecked()){
                editor.putString("email", userEmail);
                editor.putString("password", userPass);
                editor.putBoolean("isChecked", true);
            }else{
                editor.clear();
            }
            editor.apply();

            Toast.makeText(LogInScreen.this, "Log in Successful!!!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(LogInScreen.this, DonationHomePage.class));
            // As of 3/30, updated to Donation Home Page for now -Gia
            // Change this to the Home page when that's up.
            }
        }
    }

    public void toCreateAccount(View view) {
        startActivity(new Intent(LogInScreen.this, CreateAccount.class));
    }
}