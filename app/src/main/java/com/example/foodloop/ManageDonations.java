//package com.example.foodloop;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.Button;
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
//public class ManageDonations extends AppCompatActivity {
//    RecyclerView rv;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_manage_donations);
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
//        rv = findViewById(R.id.rvActiveDonations);
//        rv.setLayoutManager(new LinearLayoutManager(this));
//        AdapterManageDonations adapter = new AdapterManageDonations(donationList);
//        rv.setAdapter(adapter);
//    }
//}