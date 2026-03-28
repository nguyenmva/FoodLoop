//package com.example.foodloop;
//
//import android.os.Bundle;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class HistoryDonation extends AppCompatActivity {
////
////    private List<RecyclerRowObject> historyList;
////    private RecyclerDonationsAdapter adapter;
////    RecyclerView rvHistory;
//
//    RecyclerView rv;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_history_donation);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        List<String[]> donationList = new ArrayList<>();
//        donationList.add(new String[]{"Pending", "Canned Soup", "Surrey Food Bank"});
//        donationList.add(new String[]{"Approved", "Fresh Bread", "John Doe"});
//        donationList.add(new String[]{"Pending", "Bottled Water", "City Shelter"});
//        donationList.add(new String[]{"Picked Up", "Apple Bag", "Jane Smith"});
//
//        rv = findViewById(R.id.rvDonHistTrack);
//        rv.setLayoutManager(new LinearLayoutManager(this));
//        AdapterDonationHistory adapter = new AdapterDonationHistory(donationList);
//        rv.setAdapter(adapter);
//    }
//}
//
//
//
//        //
////        // MANUAL STRING INPUTS
//////        List<String[]> donationStringList = new ArrayList<>();
//////        donationStringList.add(new String[]{"Completed", "Item A", "Person A"});
//////        donationStringList.add(new String[]{"Completed", "Canned Soup", "Red Cross"});
//////        donationStringList.add(new String[]{"Pending", "Bread", "Local Shelter"});
//////        donationStringList.add(new String[]{"Completed", "Bottled Water", "Community Center"});
//////        donationStringList.add(new String[]{"Cancelled", "Milk", "Food Bank"});
//////
//////        rvHistory = findViewById(R.id.rvDonHistTrack);
//////        rvHistory.setLayoutManager(new LinearLayoutManager(this));
//////        adapter = new RecyclerDonationsAdapter(donationStringList);
//////        rvHistory.setAdapter(adapter);
////
////
////        // Set up RecyclerView
////        rvHistory.setAdapter(adapter);
////        rvHistory.setLayoutManager(new LinearLayoutManager(this));
////        rvHistory = findViewById(R.id.rvDonHistTrack);
////
////        // Initialize data
////        historyList = new ArrayList<>();
////
////        // Link the adapter
////        adapter = new RecyclerDonationsAdapter((ArrayList<RecyclerRowObject>) historyList);
////
////        // Example: Adding a completed donation
//////        loadHistoryData();
////        }
//////
//////    private void loadHistoryData() {
//////        // You can add data manually or from a database here
//////        historyList.add(new RecyclerRowObject("Completed", "Item A", "Person A"));
//////        historyList.add(new RecyclerRowObject("Completed", "Canned Soup", "Red Cross"));
//////        historyList.add(new RecyclerRowObject("Pending", "Bread", "Local Shelter"));
//////        historyList.add(new RecyclerRowObject("Completed", "Bottled Water", "Community Center"));
//////        historyList.add(new RecyclerRowObject("Cancelled", "Milk", "Food Bank"));
//////    }
////}