package com.example.foodloop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
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

public class ActiveRequests extends AppCompatActivity {
    Button btnHome;
    RecyclerView rv;
    //    public static List<String[]> donationList = new ArrayList<>();
    public static List<String[]> requestList = new ArrayList<>();
    private DatabaseHelper foodLoopDB;
    private SharedPreferences sharedPreference;
    private static final String SHARED_PREF_NAME = "LOG_IN_CREDENTIALS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_active_requests);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnHome = findViewById(R.id.btnRTtoHome);
        btnHome.setOnClickListener(v -> {
            startActivity(new Intent(ActiveRequests.this, DonationHomePage.class));
        });

        // INITIALIZE DATABASE AND SHARED PREFERENCES
        foodLoopDB = new DatabaseHelper(this);
        sharedPreference = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

//        rv = findViewById(R.id.rvActiveRequest);
//        rv.setLayoutManager(new LinearLayoutManager(this));
//        GiaRequestAdapter adapter = new GiaRequestAdapter(ActiveDonations.donationList);
//        rv.setAdapter(adapter);
//
//        btnHome = findViewById(R.id.btnRTtoHome);
//        btnHome.setOnClickListener(v -> {
//            startActivity(new Intent(ActiveRequests.this, DonationHomePage.class));
//        });

        populateRequestList();
    }

    public void populateRequestList() {
        String savedEmail = sharedPreference.getString("email", "");
        if (savedEmail.isEmpty()) {
            Toast.makeText(this, "Nothing in the sharedPreference", Toast.LENGTH_SHORT).show();
            return;
        }
        requestList.clear();

        Cursor requestCursor = foodLoopDB.getActiveRequests(savedEmail);
        if (requestCursor != null) {
            while (requestCursor.moveToNext()) {
                String status = requestCursor.getString(requestCursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_STATUS_FLD));
                String itemName = requestCursor.getString(requestCursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_ITEM_NAME_FLD));
                String location = requestCursor.getString(requestCursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_LOCATION_FLD));

                requestList.add(new String[]{status, itemName, location});

            }
            requestCursor.close();
        }

        rv = findViewById(R.id.rvActiveRequest);
        rv.setLayoutManager(new LinearLayoutManager(this));
        GiaRequestAdapter adapter = new GiaRequestAdapter(requestList);
        rv.setAdapter(adapter);
    }
}
