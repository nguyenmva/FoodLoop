package com.example.foodloop;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Request_Select extends AppCompatActivity {

    EditText inputReqItem;
    Button imgMagGlass;
    Button btnHome;
    static List<String[]> donationList = new ArrayList<>();
    private DatabaseHelper foodLoopDB;
    RecyclerView recyclerView;
    Adapter_Request adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_request_select);
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

        btnHome.setOnClickListener(v -> startActivity(new Intent(Request_Select.this, Request_Home.class)));

        recyclerView.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(this));

        loadAllDonations(recyclerView);
    }

    public void loadAllDonations(View v) {
        donationList.clear();
        Cursor getAllCursor = foodLoopDB.getAllAvailableDonations();

        if (getAllCursor != null) {
            android.util.Log.d("SEARCH_DEBUG", "Items found: " + getAllCursor.getCount());
            while (getAllCursor.moveToNext()) {
                String name = getAllCursor.getString(getAllCursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_ITEM_NAME_FLD));
                String category = getAllCursor.getString(getAllCursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_CATEGORY_FLD));
                String expiry = getAllCursor.getString(getAllCursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_EXPIRY_DATE_FLD));
                String qty = String.valueOf(getAllCursor.getInt(getAllCursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_QUANTITY_FLD)));
                String location = getAllCursor.getString(getAllCursor.getColumnIndexOrThrow(DatabaseHelper.USER_CITY_FLD));
                String type = getAllCursor.getString(getAllCursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_OFFER_TYPE_FLD));

                donationList.add(new String[]{name,category, expiry, qty, location, type});
            }
            getAllCursor.close();
        }
        else {
            Toast.makeText(this, "No items found", Toast.LENGTH_SHORT).show();
            android.util.Log.d("SEARCH_DEBUG", "Cursor is NULL");
        }
        adapter = new Adapter_Request(donationList);
        recyclerView.setAdapter(adapter);
    }

    private void loadItems(String search) {
        List<String[]> donationList = new ArrayList<>();
        Cursor cursor = foodLoopDB.getDonationsWithDonorInfo(search);


        if (cursor != null) {
            android.util.Log.d("SEARCH_DEBUG", "Items found: " + cursor.getCount());
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_ITEM_NAME_FLD));
                String expiry = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_EXPIRY_DATE_FLD));
                String qty = String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_QUANTITY_FLD)));
                String location = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_CITY_FLD));
                String type = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_OFFER_TYPE_FLD));
                String id = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_ID_FLD));


                donationList.add(new String[]{name, expiry, qty, location, type, id});
            }
            cursor.close();
        }
        else {
            android.util.Log.d("SEARCH_DEBUG", "Cursor is NULL");
            Toast.makeText(this, "No items found", Toast.LENGTH_SHORT).show();
        }
        adapter = new Adapter_Request(donationList);
        recyclerView.setAdapter(adapter);
    }

}