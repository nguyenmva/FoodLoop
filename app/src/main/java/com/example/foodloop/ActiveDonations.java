package com.example.foodloop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ActiveDonations extends AppCompatActivity {

    Button btnHome;
    RecyclerView rv;
    static List<String[]> donationList = new ArrayList<>();
    private DatabaseHelper foodLoopDB;
    private SharedPreferences sharedPreference;
    private static final String SHARED_PREF_NAME = "LOG_IN_CREDENTIALS";
    String userID, requestorName;
    Cursor emailCursor, donationCursor, requestorCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_active_donations);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnHome = findViewById(R.id.btnMADtoHome);
        btnHome.setOnClickListener(v -> {
            startActivity(new Intent(ActiveDonations.this, DonationHomePage.class));
        });

        // INITIALIZE DATABASE AND SHARED PREFERENCES
        foodLoopDB = new DatabaseHelper(this);
        sharedPreference = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        populateDonationList();
    }

    public void populateDonationList() {
        // POPULATE FIELDS WITH EXISTING ACCOUNT INFO
        String savedEmail = sharedPreference.getString("email", "");

        if (!savedEmail.isEmpty()) {
            donationList.clear();

            // USE EMAIL TO FIND DONOR ID
            emailCursor = foodLoopDB.getUserDataByEmail(savedEmail);
            if (emailCursor != null && emailCursor.moveToFirst()) {
                userID = emailCursor.getString(emailCursor.getColumnIndexOrThrow(DatabaseHelper.USER_ID_FLD));
            }

            // USE DONOR ID TO FIND ALL DONATIONS THAT THE DONOR HAS MADE
            donationCursor = foodLoopDB.getDonationByDonorID(userID);
            if (donationCursor != null) {
                while (donationCursor.moveToNext()) { // WHILE LOOP TO GO THROUGH ALL THE DONATIONS
                    String status = donationCursor.getString(donationCursor.getColumnIndexOrThrow
                            (DatabaseHelper.DONATION_STATUS_FLD));
                    String itemName = donationCursor.getString(donationCursor.getColumnIndexOrThrow
                            (DatabaseHelper.DONATION_ITEM_NAME_FLD));
                    String location = donationCursor.getString(donationCursor.getColumnIndexOrThrow
                            (DatabaseHelper.DONATION_LOCATION_FLD));
                    // PICKUP = DONOR'S ADDRESS (CITY, PROVINCE)
                    // DELIVERY = REQUESTOR'S ADDRESS (CITY, PROVINCE)

                    donationList.add(new String[]{status, itemName, location});
                }
            }
            if (donationCursor != null) {
                donationCursor.close();
            }
        }
        if (emailCursor != null) {
            emailCursor.close();
        }
        else {
            Toast.makeText(this, "No user logged in.", Toast.LENGTH_SHORT).show();
        }
        // ##################################################################################################################
        // SCARY RECYCLER STUFF
//        donationList.add(new String[]{"Pending", "Canned Soup", "Surrey Food Bank"});
//        donationList.add(new String[]{"Pending", "Fresh Bread", "Bob's Bakery"});
//        donationList.add(new String[]{"Pending", "Bottled Water", "City Shelter"});
//        donationList.add(new String[]{"Pending", "Apple Bag", "Apple Jack's"});
        // {Status, Item Name, Location}

        rv = findViewById(R.id.rvActiveDonations);
        rv.setLayoutManager(new LinearLayoutManager(this));
        GiaDonateAdapter adapter = new GiaDonateAdapter(donationList);
        rv.setAdapter(adapter);
    }
}