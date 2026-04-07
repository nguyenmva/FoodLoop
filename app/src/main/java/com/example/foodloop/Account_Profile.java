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

public class Account_Profile extends AppCompatActivity {
    private DatabaseHelper foodLoopDB;
    private SharedPreferences sharedPreference;
    private static final String SHARED_PREF_NAME = "LOG_IN_CREDENTIALS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // INITIALIZE DATABASE AND SHARED PREFERENCES
        foodLoopDB = new DatabaseHelper(this);
        sharedPreference = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        // INITIALIZE INTERFACE VARIABLES
        TextView tvName = findViewById(R.id.tvName);
        TextView tvAddress = findViewById(R.id.tvAddress);
        TextView tvPhone = findViewById(R.id.tvPhone);
        TextView tvEmail = findViewById(R.id.tvEmail);

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
        String savedEmail = sharedPreference.getString("email", "");
        Cursor userTypeCursor = foodLoopDB.getUserDataByEmail(savedEmail);
        if (userTypeCursor != null && userTypeCursor.moveToFirst()) {
            int type = userTypeCursor.getInt(userTypeCursor.getColumnIndexOrThrow(DatabaseHelper.USER_ACCOUNT_TYPE_SPINNER_FLD));

            switch (type) {
                case 1:
                    startActivity(new Intent(Account_Profile.this, Donation_Home.class));
                    break;
                case 2:
                    startActivity(new Intent(Account_Profile.this, Request_Home.class));
                    break;
                case 3:
                    startActivity(new Intent(Account_Profile.this, MainActivity.class));
                    break;
            }
            userTypeCursor.close();
        }
    }

    public void toEditAccount(View view) {
        startActivity(new Intent(Account_Profile.this, Account_Edit.class));
    }
}