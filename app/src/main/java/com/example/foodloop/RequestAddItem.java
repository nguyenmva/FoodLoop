package com.example.foodloop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RequestAddItem extends AppCompatActivity {

    EditText inputReqItem;
    Button imgMagGlass;
    Button btnHome;
    static List<String[]> donationList = new ArrayList<>();
    private DatabaseHelper foodLoopDB;
    RecyclerView recyclerView;
    GiaRequestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_request_add_item);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        foodLoopDB = new DatabaseHelper(this);

        inputReqItem = findViewById(R.id.inputReqItem);
        imgMagGlass = findViewById(R.id.imgMagGlass);
        btnHome = findViewById(R.id.btnReqItemToHome);
        recyclerView = findViewById(R.id.tvRecylerView);

        imgMagGlass.setOnClickListener(v -> {
            String query = inputReqItem.getText().toString().trim();
            loadItems(query);
        });

        btnHome.setOnClickListener(v -> {
            startActivity(new Intent(RequestAddItem.this, DonationHomePage.class));
        });

        recyclerView.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(this));
    }

    private void loadItems(String search) {
        List<String[]> donationList = new ArrayList<>();
        Cursor cursor = foodLoopDB.getDonationsWithDonorInfo(search); // Uses the new JOIN query


        if (cursor != null) {
            android.util.Log.d("SEARCH_DEBUG", "Items found: " + cursor.getCount());
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_ITEM_NAME_FLD));
                String expiry = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_EXPIRY_DATE_FLD));
                String qty = String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_QUANTITY_FLD)));
                String location = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_CITY_FLD));
                String type = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_OFFER_TYPE_FLD));;

                // Array order: ID, Name, Expiry, Qty, Donor, Location
                donationList.add(new String[]{name, expiry, qty, location, type});
            }
            cursor.close();
        } else {
            android.util.Log.d("SEARCH_DEBUG", "Cursor is NULL");
        }
        adapter = new GiaRequestAdapter(donationList);
        recyclerView.setAdapter(adapter);
    }

}