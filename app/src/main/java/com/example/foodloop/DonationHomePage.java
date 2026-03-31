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

public class DonationHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_donation_home_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Donate Food button
        Button btnDonateFood = findViewById(R.id.btnDonate);
        btnDonateFood.setOnClickListener(view->{
            startActivity(new Intent(DonationHomePage.this, DonorPage.class));
        });
    }

    // #######################################################################################
    // MICHAEL'S PAGES
    public void toProfilePage(View view) {
        startActivity(new Intent(DonationHomePage.this, ProfilePage.class));
    }
    public void toDonationHistoryPage(View view) {
        startActivity(new Intent(DonationHomePage.this, HistoryDonation.class));
    }
    public void toRequestHistoryPage(View view) {
        startActivity(new Intent(DonationHomePage.this, HistoryRequest.class));
    }

    // #######################################################################################
    // GIA'S PAGES
    public void toActiveDonationsPage(View view) {
        startActivity(new Intent(DonationHomePage.this, ActiveDonations.class));
    }
    public void toActiveRequestsPage(View view) {
        startActivity(new Intent(DonationHomePage.this, ActiveRequests.class));
    }
    public void toRequestItemPage(View view) {
        startActivity(new Intent(DonationHomePage.this, RequestAddItem.class));
    }

    // #######################################################################################
    // NILESH'S PAGES
    public void toLogoutPage(View view) {
        startActivity(new Intent(DonationHomePage.this, LogOutScreen.class));
    }
    // #######################################################################################
    // BELLE'S PAGES
    public void toDonorHomePage(View view) {
        startActivity(new Intent(DonationHomePage.this, DonationHomePage.class));
    }
    public void toDonationAddPage(View view) {
        startActivity(new Intent(DonationHomePage.this, Donation_Add_Page.class));
    }
    public void toDonorPage(View view) {
        startActivity(new Intent(DonationHomePage.this, DonorPage.class));
    }
    public void toEditDonationPage(View view) {
        startActivity(new Intent(DonationHomePage.this, EditDonation.class));
    }
}