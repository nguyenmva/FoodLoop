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
    String userID, recipientName;
    Cursor emailCursor, donationCursor, recipientCursor;

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

        // INITIALIZE DATABASE AND SHARED PREFERENCES
        foodLoopDB = new DatabaseHelper(this);
        sharedPreference = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        // POPULATE FIELDS WITH EXISTING ACCOUNT INFO
        String savedEmail = sharedPreference.getString("email", "");

        if (!savedEmail.isEmpty()) {
            donationList.clear();

            // USE EMAIL TO FIND DONOR ID
            emailCursor = foodLoopDB.getUserDataByEmail(savedEmail);
            if (emailCursor != null && emailCursor.moveToFirst()) {
                userID = emailCursor.getString(emailCursor.getColumnIndexOrThrow(DatabaseHelper.USER_ID_FLD));
            }

            // USE DONOR ID TO FIND ALL DONATIONS THE DONOR HAS MADE
            donationCursor = foodLoopDB.getDonationByDonorID(userID);
            if (donationCursor != null) {
                while (donationCursor.moveToNext()) { // WHILE LOOP TO GO THROUGH ALL THE DONATIONS
                    String status = donationCursor.getString(donationCursor.getColumnIndexOrThrow
                            (DatabaseHelper.DONATION_STATUS_FLD));
                    String itemName = donationCursor.getString(donationCursor.getColumnIndexOrThrow
                            (DatabaseHelper.DONATION_ITEM_NAME_FLD));
                    String recipientID = donationCursor.getString(donationCursor.getColumnIndexOrThrow
                            (DatabaseHelper.RECIPIENT_ID_FLD)); // WE NEED THE NAME, NOT THE ID

                    recipientName = "No Takers"; // DEFAULT FOR WHEN NO ONE HAS REQUESTED THE ITEM
                    if (recipientID != null) {
                        recipientCursor = foodLoopDB.getUserDataByID(recipientID); // USE RECIPIENT ID TO FIND RECIPIENT NAME
                        if (recipientCursor != null && recipientCursor.moveToFirst()) {
                            recipientName = recipientCursor.getString(recipientCursor.getColumnIndexOrThrow
                                    (DatabaseHelper.USER_NAME_FLD));
                        }
                        if (recipientCursor != null) {
                            recipientCursor.close();
                        }
                    }
                    donationList.add(new String[]{status, itemName, recipientName});
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
        donationList.add(new String[]{"Pending", "Canned Soup", "Surrey Food Bank"});
        donationList.add(new String[]{"Pending", "Fresh Bread", "John Doe"});
        donationList.add(new String[]{"Pending", "Bottled Water", "City Shelter"});
        donationList.add(new String[]{"Pending", "Apple Bag", "Jane Smith"});

        rv = findViewById(R.id.rvActiveDonations);
        rv.setLayoutManager(new LinearLayoutManager(this));
        GiaDonateAdapter adapter = new GiaDonateAdapter(donationList);
        rv.setAdapter(adapter);

        btnHome = findViewById(R.id.btnMADtoHome);
        btnHome.setOnClickListener(v -> {
            startActivity(new Intent(ActiveDonations.this, DonationHomePage.class));
        });
    }
}