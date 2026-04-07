package com.example.foodloop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Donation_History extends AppCompatActivity {

    RecyclerView rv;
    public static ArrayList<String[]> donationHistory = new ArrayList<>();
    private DatabaseHelper foodLoopDB;
    private SharedPreferences sharedPreference;
    private static final String SHARED_PREF_NAME = "LOG_IN_CREDENTIALS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_donation_history);

        // Handle Window Insets (Edge-to-Edge)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // INITIALIZE DATABASE AND SHARED PREFERENCES
        foodLoopDB = new DatabaseHelper(this);
        sharedPreference = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        loadHistoryData();
    }

    private void loadHistoryData() {
        String savedEmail = sharedPreference.getString("email", "");
        if (savedEmail.isEmpty()) {
            Toast.makeText(this, "Nothing in the sharedPreference", Toast.LENGTH_SHORT).show();
            return;
        }
        donationHistory.clear();

        Cursor requestCursor = foodLoopDB.getDonationHistory(savedEmail);
        if (requestCursor != null) {
            while (requestCursor.moveToNext()) {
                String status = requestCursor.getString(requestCursor.getColumnIndexOrThrow("RequestStatus"));
                String itemName = requestCursor.getString(requestCursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_ITEM_NAME_FLD));
                String requestor = requestCursor.getString(requestCursor.getColumnIndexOrThrow("RequestorName"));

                donationHistory.add(new String[]{status, itemName, requestor});
            }
            requestCursor.close();
        }

        rv = findViewById(R.id.rvDonHistTrack);
        rv.setLayoutManager(new LinearLayoutManager(this));
        Adapter_Historical adapter = new Adapter_Historical(donationHistory, this, "DonationHistory");
        rv.setAdapter(adapter);
    }

    public void toDonorHomePage(View view) {
        startActivity(new Intent(Donation_History.this, App_Home.class));
    }
}