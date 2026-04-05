package com.example.foodloop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfilePage extends AppCompatActivity {
    private TextView tvName, tvAddress, tvPhone, tvEmail;
    private DatabaseHelper foodLoopDB;
    private SharedPreferences sharedPreference;
    private static final String SHARED_PREF_NAME = "LOG_IN_CREDENTIALS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // INITIALIZE DATABASE AND SHARED PREFERENCES
        foodLoopDB = new DatabaseHelper(this);
        sharedPreference = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        // INITIALIZE INTERFACE VARIABLES
        tvName = findViewById(R.id.tvName);
        tvAddress = findViewById(R.id.tvAddress);
        tvPhone = findViewById(R.id.tvPhone);
        tvEmail = findViewById(R.id.tvEmail);

        // POPULATE FIELDS WITH EXISTING ACCOUNT INFO
        String savedEmail = sharedPreference.getString("email", "");

        if (!savedEmail.isEmpty()) {
            Cursor cursor = foodLoopDB.getUserDataByEmail(savedEmail);

            if (cursor != null && cursor.moveToFirst()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_NAME_FLD));
                String city = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_CITY_FLD));
                String province = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_PROVINCE_FLD));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_PHONE_FLD));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_EMAIL_FLD));

                tvName.setText(name);
                tvAddress.setText(city + ", " + province);
                tvPhone.setText(phone);
                tvEmail.setText(email);

                cursor.close();
            }
        } else {
            Toast.makeText(this, "No user logged in.", Toast.LENGTH_SHORT).show();
        }



    }
    public void toHomePage(View view) {
        startActivity(new Intent(ProfilePage.this, Donation_Home_Page.class)); // Change this to the Home page when that's up.
    }
    public void toEditAccount(View view) {
        startActivity(new Intent(ProfilePage.this, EditAccount.class));
    }
}