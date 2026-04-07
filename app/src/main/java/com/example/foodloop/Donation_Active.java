package com.example.foodloop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
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

public class Donation_Active extends AppCompatActivity {

    Button btnHome, btnAllDonations, btnReqDonations;
    RecyclerView rv;
    static List<String[]> donationList = new ArrayList<>();
    private DatabaseHelper foodLoopDB;
    private SharedPreferences sharedPreference;
    private static final String SHARED_PREF_NAME = "LOG_IN_CREDENTIALS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_donations_active);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Set buttons and recycler view
        btnAllDonations = findViewById(R.id.btnMADAllDon);
        btnReqDonations = findViewById(R.id.btnMADReqDon);
        rv = findViewById(R.id.rvActiveDonations);
        btnHome = findViewById(R.id.btnMADtoHome);

        //Assign Home button function
        btnHome.setOnClickListener(v -> {
            startActivity(new Intent(Donation_Active.this, Donation_Home.class));
        });

        // INITIALIZE DATABASE AND SHARED PREFERENCES
        foodLoopDB = new DatabaseHelper(this);
        sharedPreference = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        //Show all donations upon loading page
        showAllDonations(rv);
    }

    public void showAllDonations(View v) {
        // POPULATE FIELDS WITH EXISTING ACCOUNT INFO
        String savedEmail = sharedPreference.getString("email", "");
        if (savedEmail.isEmpty()) {
            Toast.makeText(this, "Nothing in the sharedPreference", Toast.LENGTH_SHORT).show();
            return;
        }
        donationList.clear();

        Cursor donationCursor = foodLoopDB.getAllDonationsFromCurrentUser(savedEmail);
        if (donationCursor != null) {
            while (donationCursor.moveToNext()) { // WHILE LOOP TO GO THROUGH ALL THE DONATIONS
                String donationID = donationCursor.getString(
                        donationCursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_ID_FLD));

                String itemName = donationCursor.getString(
                        donationCursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_ITEM_NAME_FLD));

                String requestorName = donationCursor.getString(
                        donationCursor.getColumnIndexOrThrow("RequestorName"));

                String requestStatus = donationCursor.isNull(donationCursor.getColumnIndexOrThrow("RequestStatus"))
                        ? "" : donationCursor.getString(donationCursor.getColumnIndexOrThrow("RequestStatus"));

                String requestID = donationCursor.isNull(donationCursor.getColumnIndexOrThrow("RequestID"))
                        ? "" : donationCursor.getString(donationCursor.getColumnIndexOrThrow("RequestID"));

                donationList.add(new String[]{donationID, requestStatus, itemName, requestorName, requestID});
            }
            donationCursor.close();
        }

        //Remove any rejected items from list
        for (int i = donationList.size() - 1; i >= 0; i--) {
            if ("Rejected".equals(donationList.get(i)[1])) {
                donationList.remove(i);
            }
        }

        rv.setLayoutManager(new LinearLayoutManager(this));
        Adapter_Donate adapter = new Adapter_Donate(donationList, this);
        rv.setAdapter(adapter);
    }

    public void showRequestedDonations(View v) {
        // POPULATE FIELDS WITH EXISTING ACCOUNT INFO
        String savedEmail = sharedPreference.getString("email", "");
        if (savedEmail.isEmpty()) {
            Toast.makeText(this, "Nothing in the sharedPreference", Toast.LENGTH_SHORT).show();
            return;
        }
        donationList.clear();

        Cursor donationCursor = foodLoopDB.getActiveDonations(savedEmail);
        if (donationCursor != null) {
            while (donationCursor.moveToNext()) { // WHILE LOOP TO GO THROUGH ALL THE DONATIONS
                String donationID = donationCursor.getString(
                        donationCursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_ID_FLD));

                String itemName = donationCursor.getString(
                        donationCursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_ITEM_NAME_FLD));

                String requestorName = donationCursor.getString(
                        donationCursor.getColumnIndexOrThrow("RequestorName"));

                String requestStatus = donationCursor.isNull(donationCursor.getColumnIndexOrThrow("RequestStatus"))
                        ? "" : donationCursor.getString(donationCursor.getColumnIndexOrThrow("RequestStatus"));

                String requestID = donationCursor.isNull(donationCursor.getColumnIndexOrThrow("RequestID"))
                        ? "" : donationCursor.getString(donationCursor.getColumnIndexOrThrow("RequestID"));

                donationList.add(new String[]{donationID, requestStatus, itemName, requestorName, requestID});
            }
            donationCursor.close();
        }

        //Remove any rejected items from list
        for (int i = donationList.size() - 1; i >= 0; i--) {
            if ("Rejected".equals(donationList.get(i)[1])) {
                donationList.remove(i);
            }
        }

        rv.setLayoutManager(new LinearLayoutManager(this));
        Adapter_Donate adapter = new Adapter_Donate(donationList, this);
        rv.setAdapter(adapter);
    }
}