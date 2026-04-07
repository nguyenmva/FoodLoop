package com.example.foodloop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Request_Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_request_home_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void toActiveRequestsPage(View view) {
        startActivity(new Intent(Request_Home.this, Request_Active.class));
    }

    public void toRequestItemPage(View view) {
        startActivity(new Intent(Request_Home.this, Request_Select.class));
    }

    public void toLogoutPage(View view) {
        startActivity(new Intent(Request_Home.this, Account_LogOut.class));
    }

    public void toProfilePage(View view) {
        startActivity(new Intent(Request_Home.this, Account_Profile.class));
    }

    public void toRequestHistoryPage(View view) {
        startActivity(new Intent(Request_Home.this, Request_History.class));
    }

}