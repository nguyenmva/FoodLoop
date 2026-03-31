package com.example.foodloop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditDonation extends AppCompatActivity {
    private EditText edtFoodNameEdit, edtQuantityEdit, edtExpiryDateEdit, edtPriceEdit;
    private Spinner spCategoryEdit, spAvailTimeEdit;
    private RadioButton rdbFreeEdit, rdbDiscountedEdit;
    private DatabaseHelper foodLoopDB;

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
    }

    public void toDonorHomePage(View view) {
        startActivity(new Intent(EditDonation.this, DonationHomePage.class));
    }
}