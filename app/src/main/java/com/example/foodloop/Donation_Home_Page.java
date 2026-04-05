package com.example.foodloop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Donation_Home_Page extends AppCompatActivity {

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
    }

    // #######################################################################################
    // MICHAEL'S PAGES
    public void toProfilePage(View view) {
        startActivity(new Intent(Donation_Home_Page.this, ProfilePage.class));
    }
    public void toDonationHistoryPage(View view) {
        startActivity(new Intent(Donation_Home_Page.this, HistoryDonation.class));
    }
    public void toRequestHistoryPage(View view) {
        startActivity(new Intent(Donation_Home_Page.this, HistoryRequest.class));
    }

    // #######################################################################################
    // GIA'S PAGES
    public void toActiveDonationsPage(View view) {
        startActivity(new Intent(Donation_Home_Page.this, ActiveDonations.class));
    }
    public void toActiveRequestsPage(View view) {
        startActivity(new Intent(Donation_Home_Page.this, ActiveRequests.class));
    }
    public void toRequestItemPage(View view) {
        startActivity(new Intent(Donation_Home_Page.this, RequestAddItem.class));
    }

    // #######################################################################################
    // NILESH'S PAGES
    public void toLogoutPage(View view) {
        startActivity(new Intent(Donation_Home_Page.this, LogOutScreen.class));
    }
    // #######################################################################################
    // BELLE'S PAGES
    public void toDonorHomePage(View view) {
        startActivity(new Intent(Donation_Home_Page.this, Donation_Home_Page.class));
    }
    public void toDonationAddPage(View view) {
        startActivity(new Intent(Donation_Home_Page.this, Donation_Add_Page.class));
    }
    public void toEditDonationPage(View view) {
        startActivity(new Intent(Donation_Home_Page.this, EditDonation.class));
    }
}