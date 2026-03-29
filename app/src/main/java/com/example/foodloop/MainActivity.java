package com.example.foodloop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnActDonations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnActDonations = findViewById(R.id.btnToManageDonations);
    }

    // #######################################################################################
    // MICHAEL'S PAGES
    public void toCreatePage(View view) {
        startActivity(new Intent(MainActivity.this, CreateAccount.class));
    }
    public void toProfilePage(View view) {
        startActivity(new Intent(MainActivity.this, Profile.class));
    }
    public void toEditPage(View view) {
        startActivity(new Intent(MainActivity.this, EditAccount.class));
    }
    public void toDonationHistoryPage(View view) {
        startActivity(new Intent(MainActivity.this, HistoryDonation.class));
    }
    public void toRequestHistoryPage(View view) {
        startActivity(new Intent(MainActivity.this, HistoryRequest.class));
    }
    public void toDatabaseTesting(View view) {
        startActivity(new Intent(MainActivity.this, TestingPage.class));
    }
    // #######################################################################################
    // GIA'S PAGES
    public void toActiveDonationsPage(View view) {
        startActivity(new Intent(MainActivity.this, ActiveDonations.class));
    }
    public void toActiveRequestsPage(View view) {
        startActivity(new Intent(MainActivity.this, ActiveRequests.class));
    }
    public void toDonorPage(View view) {
        startActivity(new Intent(MainActivity.this, DonorPage.class));
    }

    // #######################################################################################
    // NILESH'S PAGES
    public void toLoginPage(View view) {
        startActivity(new Intent(MainActivity.this, LogInScreen.class));
    }
    public void toLogoutPage(View view) {
        startActivity(new Intent(MainActivity.this, LogOutScreen.class));
    }
    // #######################################################################################
    // BELLE'S PAGES
    public void toDonorHomePage(View view) {
        startActivity(new Intent(MainActivity.this, DonationHomePage.class));
    }
    public void toDonationAddPage(View view) {
        startActivity(new Intent(MainActivity.this, Donation_Add_Page.class));
    }
    public void toRequestHomePage(View view) {
        startActivity(new Intent(MainActivity.this, Request_Home_Page.class));
    }
}