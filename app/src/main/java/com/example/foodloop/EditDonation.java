package com.example.foodloop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
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

public class EditDonation extends AppCompatActivity {
    private EditText edtFoodNameEdit, edtQuantityEdit, edtExpiryDateEdit, edtPriceEdit;
    private Spinner spCategoryEdit;
    private RadioButton rdbFreeEdit, rdbDiscountedEdit;
    private DatabaseHelper foodLoopDB;

    private SharedPreferences savedDonationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_donation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // INITIALIZE INTERFACE VARIABLES
        edtFoodNameEdit = findViewById(R.id.edtFoodNameEdit);
        edtQuantityEdit = findViewById(R.id.edtQuantityEdit1);
        edtExpiryDateEdit = findViewById(R.id.edtExpiryDateEdit);
        edtPriceEdit = findViewById(R.id.edtPriceEdit);
        spCategoryEdit = findViewById(R.id.spCategoryEdit);
//        spAvailTime = findViewById(R.id.spAvailTime);
        rdbFreeEdit = findViewById(R.id.rdbFreeEdit);
        rdbDiscountedEdit = findViewById(R.id.rdbDiscountedEdit);

        // INITIALIZE DATABASE
        foodLoopDB = new DatabaseHelper(this);

        //INITIALIZE SHARED PREFERENCE
        savedDonationID = getSharedPreferences("savedDonationID", MODE_PRIVATE);

        int selectedID = savedDonationID.getInt("id_Donation", 0);

        Cursor cursor = foodLoopDB.getDonationByDonationID(selectedID); //selectedID is that data that i got from SharedPref

        if(cursor != null && cursor.moveToFirst()){
            String foodName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_ITEM_NAME_FLD));
            int quantityFood = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_QUANTITY_FLD));
//            String categoryName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_CATEGORY_FLD));
            int categorySpinner = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_CATEGORY_SPINNER_FLD)); //Index numbers for spinner
            String expiryDate = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_CATEGORY_SPINNER_FLD));
            String expireDate = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_EXPIRY_DATE_FLD));
            String offerType = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_OFFER_TYPE_FLD));
            String price =  cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_PRICE_FLD));
            String donationStatus = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_STATUS_FLD));
            int donorID = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.DONOR_ID_FLD));

            edtFoodNameEdit.setText(foodName);
            edtQuantityEdit.setText(quantityFood);
            edtExpiryDateEdit.setText(expiryDate);
            edtPriceEdit.setText(price);
            spCategoryEdit.setSelection(categorySpinner);
            if(offerType.equals("Discounted")){
                rdbDiscountedEdit.setChecked(true);
            } else if (offerType.equals("Free")){
                rdbFreeEdit.setChecked(true);
            }
            cursor.close();
        }

        //IF USER PICKS rdbFree, THE PRICE PART WILL BE HIDDEN
        rdbFreeEdit.setOnClickListener(view ->{
            if(rdbFreeEdit.isChecked()){
                edtPriceEdit.setVisibility(View.GONE);
                edtPriceEdit.setText("0");
            }
        });
        rdbDiscountedEdit.setOnClickListener(view ->{
            if(rdbDiscountedEdit.isChecked()){
                edtPriceEdit.setVisibility(View.VISIBLE);
                edtPriceEdit.setText("");
            }
        });

        //
    }

    public void editDonation(View view){
        String priceInput = edtPriceEdit.getText().toString();

        //CHECK IF FREE OR DISCOUNT PRICE
        boolean withPrice = rdbDiscountedEdit.isChecked();
        boolean emptyPrice = TextUtils.isEmpty(priceInput);

        // ERROR HANDLING, NO EMPTY FIELDS.
        if (TextUtils.isEmpty(edtFoodNameEdit.getText().toString())
                || TextUtils.isEmpty(edtQuantityEdit.getText().toString())
                || TextUtils.isEmpty(edtExpiryDateEdit.getText().toString())
                || (withPrice && emptyPrice) //Throws an error if Discounted is chosen and there is no price indicated
                || (!rdbDiscountedEdit.isChecked() && !rdbFreeEdit.isChecked()) //User must pick or it will throw an error
                || TextUtils.isEmpty(spCategoryEdit.getSelectedItem().toString())
                || (!rdbFreeEdit.isChecked() && !rdbDiscountedEdit.isChecked())
                || (spCategoryEdit.getSelectedItemPosition() == 0))
        {
            Toast.makeText(EditDonation.this, "All areas must be filled or selected.", Toast.LENGTH_LONG).show();
        }
        else {
            // INITIALIZE DONATION INFO VARIABLES
            String itemName = edtFoodNameEdit.getText().toString();
            int quantity = Integer.parseInt(edtQuantityEdit.getText().toString());
            String category = spCategoryEdit.getSelectedItem().toString();
            int categoryIndex = spCategoryEdit.getSelectedItemPosition();
            String expiryDate = edtExpiryDateEdit.getText().toString();


            double price;
            if (rdbFreeEdit.isChecked()) {
                price = 0.0;
            } else {
                price = Double.parseDouble(edtPriceEdit.getText().toString());
            }

            String offerType = rdbFreeEdit.isChecked() ? "Free" : "Discounted";
            String status = "Pending"; // DEFAULT STRING?
            int donor = 2; // WILL CHANGE IT TO GET FROM DB LATER.


//            boolean foodLoopDB.updateDonation()
//            // NEEDS MORE ERROR HANDLING??
//            boolean inserted = foodLoopDB.createDonation(
//                    itemName, quantity, category, categoryIndex,
//                    expiryDate, offerType, price, status, donor
//            );
            // PROVIDE CONFIRMATION TO THE USER
//            if (inserted) {
//                Toast.makeText(this, "Donation Updated", Toast.LENGTH_LONG).show();
//                startActivity(new Intent(EditDonation.this, DonationHomePage.class));
//            } else {
//                Toast.makeText(this, "Error!", Toast.LENGTH_LONG).show();
//            }


        }

    }

    public void backDonorHomePage(View view) {
        startActivity(new Intent(EditDonation.this, Donation_Home_Page.class));
    }
}


