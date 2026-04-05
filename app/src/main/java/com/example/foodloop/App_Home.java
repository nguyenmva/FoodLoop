package com.example.foodloop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class App_Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_app_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // #######################################################################################
    // MICHAEL'S PAGES
    public void toProfilePage(View view) {
        startActivity(new Intent(App_Home.this, Account_Profile.class));
    }
    public void toDonationHistoryPage(View view) {
        startActivity(new Intent(App_Home.this, Donation_History.class));
    }
    public void toRequestHistoryPage(View view) {
        startActivity(new Intent(App_Home.this, Request_History.class));
    }

    // #######################################################################################
    // GIA'S PAGES
    public void toActiveDonationsPage(View view) {
        startActivity(new Intent(App_Home.this, Donation_Active.class));
    }
    public void toActiveRequestsPage(View view) {
        startActivity(new Intent(App_Home.this, Request_Active.class));
    }
    public void toRequestItemPage(View view) {
        startActivity(new Intent(App_Home.this, Request_Select.class));
    }

    // #######################################################################################
    // NILESH'S PAGES
    public void toLogoutPage(View view) {
        startActivity(new Intent(App_Home.this, Account_LogOut.class));
    }
    // #######################################################################################
    // BELLE'S PAGES
    public void toDonorHomePage(View view) {
        startActivity(new Intent(App_Home.this, App_Home.class));
    }
    public void toDonationAddPage(View view) {
        startActivity(new Intent(App_Home.this, Donation_Add.class));
    }
    public void toEditDonationPage(View view) {
        startActivity(new Intent(App_Home.this, Donation_Edit.class));
    }
}