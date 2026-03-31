package com.example.foodloop;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Donation_Add_Page extends AppCompatActivity {
    private EditText edtFoodName, edtQuantity, edtExpiryDate, edtPrice;
    private Spinner spCategory, spAvailTime;
    private RadioButton rdbFree, rdbDiscounted;
    private DatabaseHelper foodLoopDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_donation_add_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // INITIALIZE INTERFACE VARIABLES
        edtFoodName = findViewById(R.id.edtFoodName);
        edtQuantity = findViewById(R.id.edtQuantity);
        edtExpiryDate = findViewById(R.id.edtExpiryDate);
        edtPrice = findViewById(R.id.edtPrice);
        spCategory = findViewById(R.id.spCategory);
        spAvailTime = findViewById(R.id.spAvailTime);
        rdbFree = findViewById(R.id.rdbFree);
        rdbDiscounted = findViewById(R.id.rdbDiscounted);

        // INITIALIZE DATABASE
        foodLoopDB = new DatabaseHelper(this);
    }

    public void createDonation(View view) {
        // ERROR HANDLING, NO EMPTY FIELDS.
        if (TextUtils.isEmpty(edtFoodName.getText().toString())
                || TextUtils.isEmpty(edtQuantity.getText().toString())
                || TextUtils.isEmpty(edtExpiryDate.getText().toString())
                || TextUtils.isEmpty(edtPrice.getText().toString())
                || TextUtils.isEmpty(spCategory.getSelectedItem().toString())
                || TextUtils.isEmpty(spAvailTime.getSelectedItem().toString())
                || (!rdbFree.isChecked() && !rdbDiscounted.isChecked())
                || (spCategory.getSelectedItemPosition() == 0)
                || (spAvailTime.getSelectedItemPosition() == 0)) {
            Toast.makeText(Donation_Add_Page.this, "All areas must be filled or selected.", Toast.LENGTH_LONG).show();
        }
        else {
            // INITIALIZE DONATION INFO VARIABLES
            String itemName = edtFoodName.getText().toString();
            int quantity = Integer.parseInt(edtQuantity.getText().toString());
            String category = spCategory.getSelectedItem().toString();
            int categoryIndex = spCategory.getSelectedItemPosition();
            String expiryDate = edtExpiryDate.getText().toString();
            String pickupTime = spAvailTime.getSelectedItem().toString();
            int pickupIndex = spAvailTime.getSelectedItemPosition();
            float price = Float.parseFloat(edtPrice.getText().toString());
            String offerType = rdbFree.isChecked() ? "Free" : "Discounted";
            String location = "Donor's Address?"; // WILL CHANGE IT TO GET FROM DB LATER.
            String status = "I Can Has Cheezburger?"; // DEFAULT STRING?
            String recipient = ""; // IS "" OR NULL BETTER?

            // NEEDS MORE ERROR HANDLING??
            boolean inserted = foodLoopDB.createDonation(
                    itemName, quantity, category, categoryIndex,
                    expiryDate, pickupTime, pickupIndex,
                    offerType, price, location, status, recipient
            );
                // PROVIDE CONFIRMATION TO THE USER
            if(inserted)
                Toast.makeText(this, "Donation Listed!", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this, "Error: Kill the Person who Wrote This Code", Toast.LENGTH_LONG).show();
        }
        // DO WE FORCE THE USER TO LEAVE THE PAGE AFTER LISTING A DONATION?
        // startActivity(new Intent(Donation_Add_Page.this, MainActivity.class));
    }
}