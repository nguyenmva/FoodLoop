package com.example.foodloop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Donation_Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_donation_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void toProfilePage(View view) {
        startActivity(new Intent(Donation_Home.this, Account_Profile.class));
    }

    public void toLogoutPage(View view) {
        startActivity(new Intent(Donation_Home.this, Account_LogOut.class));
    }

    public void toDonationAddPage(View view) {
        startActivity(new Intent(Donation_Home.this, Donation_Add.class));
    }

    public void toActiveDonationsPage(View view) {
        startActivity(new Intent(Donation_Home.this, Donation_Active.class));
    }

    public void toEditDonationPage(View view) {
        startActivity(new Intent(Donation_Home.this, Donation_Edit.class));
    }

    public void toDonationHistoryPage(View view) {
        startActivity(new Intent(Donation_Home.this, Donation_History.class));
    }

}