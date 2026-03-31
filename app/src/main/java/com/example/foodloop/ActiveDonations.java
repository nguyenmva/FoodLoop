package com.example.foodloop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

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
            startActivity(new Intent(ActiveDonations.this, MainActivity.class));
        });
    }
}