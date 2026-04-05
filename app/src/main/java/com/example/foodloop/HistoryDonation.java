package com.example.foodloop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HistoryDonation extends AppCompatActivity {

    // 1. Declare variables once at the top
    private List<RecyclerRowObject> historyList;
    private RecyclerDonationsAdapter adapter;
    private RecyclerView rvHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history_donation);

        // Handle Window Insets (Edge-to-Edge)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 2. Initialize the View
        rvHistory = findViewById(R.id.rvDonHistTrack);

        // 3. Initialize the Data List
        historyList = new ArrayList<>();

        // 4. Set up the Adapter and Layout Manager
        // Note: Make sure your Adapter constructor matches this
//        adapter = new RecyclerDonationsAdapter((ArrayList<RecyclerRowObject>) historyList);
//        rvHistory.setLayoutManager(new LinearLayoutManager(this));
//        rvHistory.setAdapter(adapter);
//
//        // 5. Load the data
//        loadHistoryData();
    }

//    private void loadHistoryData() {
//        // Add data to the existing list
//        historyList.add(new RecyclerRowObject("Completed", "Item A", "Person A"));
//        historyList.add(new RecyclerRowObject("Completed", "Canned Soup", "Red Cross"));
//        historyList.add(new RecyclerRowObject("Pending", "Bread", "Local Shelter"));
//        historyList.add(new RecyclerRowObject("Completed", "Bottled Water", "Community Center"));
//        historyList.add(new RecyclerRowObject("Cancelled", "Milk", "Food Bank"));
//
//        // Notify the adapter that data has changed to refresh the UI
//        adapter.notifyDataSetChanged();
//    }

    public void toDonorHomePage(View view) {
        startActivity(new Intent(HistoryDonation.this, Donation_Home_Page.class));
    }
}