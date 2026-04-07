package com.example.foodloop;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Request_View extends AppCompatActivity {

    TextView tvVRReqItem, tvVRDonor, tvVRCat, tvVRLoc, tvVRPrice, tvVRQty, tvVRStat;

    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_request_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //VIEWS
        tvVRReqItem = findViewById(R.id.tvVRReqItem);
        tvVRDonor = findViewById(R.id.tvVRDonor);
        tvVRCat = findViewById(R.id.tvVRCat);
        tvVRLoc = findViewById(R.id.tvVRLoc);
        tvVRPrice = findViewById(R.id.tvVRPrice);
        tvVRQty = findViewById(R.id.tvVRQty);
        tvVRStat = findViewById(R.id.tvVRStat);

        db = new DatabaseHelper(this);

        String requestID = getIntent().getStringExtra("REQUEST_ID");

        if (requestID != null) {
            loadRequestDetails(requestID);
        }
    }

    private void loadRequestDetails(String requestID) {
        Cursor cursor = db.getRequestDetailsById(requestID);

        if (cursor != null && cursor.moveToFirst()) {
            tvVRReqItem.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_ITEM_NAME_FLD)));
            tvVRDonor.setText(cursor.getString(cursor.getColumnIndexOrThrow("DonorName")));
            tvVRCat.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_CATEGORY_FLD)));
            tvVRLoc.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.REQUEST_LOCATION_FLD)));
            tvVRPrice.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_PRICE_FLD)));
            tvVRQty.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_QUANTITY_FLD)));
            tvVRStat.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_STATUS_FLD)));
        }

        if (cursor != null) {
            cursor.close();
        }


    }

    public void confirmReceipt(View view) {
        Toast.makeText(this, "Confirmed Receipt of Item!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(Request_View.this, Request_Home.class));
    }

    public void toRequestHomePage(View view) {
        startActivity(new Intent(Request_View.this, Request_Home.class));
    }
}