package com.example.foodloop;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
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

    // Page Links
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
    public void toManageDonationsPage(View view) {
        startActivity(new Intent(MainActivity.this, ActiveDonations.class));
    }
    public void toLoginPage(View view) {
        startActivity(new Intent(MainActivity.this, LogInScreen.class));
    }
}