package com.example.foodloop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class Account_Edit extends AppCompatActivity {
    private EditText inputName, inputStreet, inputCity, inputProvince, inputPostal,
            inputPhone, inputEmail, inputNewPass, inputConfirmNewPass;
    private Spinner inputCountry, inputAccountType;
    private DatabaseHelper foodLoopDB;
    private SharedPreferences sharedPreference;
    private static final String SHARED_PREF_NAME = "LOG_IN_CREDENTIALS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account_edit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // INITIALIZE INTERFACE VARIABLES
        inputName = findViewById(R.id.inputName);
        inputStreet = findViewById(R.id.inputStreet);
        inputCity = findViewById(R.id.inputCity);
        inputProvince = findViewById(R.id.inputProvince2);
        inputCountry = findViewById(R.id.inputCountry);
        inputPostal = findViewById(R.id.inputPostal);
        inputPhone = findViewById(R.id.inputPhone);
        inputEmail = findViewById(R.id.inputEmail);
        inputNewPass = findViewById(R.id.inputNewPass);
        inputConfirmNewPass = findViewById(R.id.inputConfirmNewPass);
        inputAccountType = findViewById(R.id.inputAccountType2);

        // INITIALIZE DATABASE AND SHARED PREFERENCES
        foodLoopDB = new DatabaseHelper(this);
        sharedPreference = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        // POPULATE FIELDS WITH EXISTING ACCOUNT INFO
        String savedEmail = sharedPreference.getString("email", "");

        if (!savedEmail.isEmpty()) {
            Cursor cursor = foodLoopDB.getUserDataByEmail(savedEmail);

            if (cursor != null && cursor.moveToFirst()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_NAME_FLD));
                String street = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_STREET_FLD));
                String city = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_CITY_FLD));
                String province = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_PROVINCE_FLD));
                int countrySpinnerSelection = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_COUNTRY_SPINNER_FLD));
                String postal = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_POSTAL_FLD));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_PHONE_FLD));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_EMAIL_FLD));
                int accountTypeSpinner = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_ACCOUNT_TYPE_SPINNER_FLD));

                inputName.setText(name);
                inputStreet.setText(street);
                inputCity.setText(city);
                inputProvince.setText(province);
                inputCountry.setSelection(countrySpinnerSelection);
                inputPostal.setText(postal);
                inputPhone.setText(phone);
                inputEmail.setText(email);
                inputAccountType.setSelection(accountTypeSpinner);

                cursor.close();
            }
        }
        else {
            Toast.makeText(this, "No user logged in.", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateAccount(View view) {
        // ERROR HANDLING, NO EMPTY FIELDS.
        if (TextUtils.isEmpty(inputName.getText().toString())
                || TextUtils.isEmpty(inputStreet.getText().toString())
                || TextUtils.isEmpty(inputCity.getText().toString())
                || TextUtils.isEmpty(inputProvince.getText().toString())
                || TextUtils.isEmpty(inputPostal.getText().toString())
                || TextUtils.isEmpty(inputPhone.getText().toString())
                || TextUtils.isEmpty(inputEmail.getText().toString())
                || TextUtils.isEmpty(inputNewPass.getText().toString())
                || TextUtils.isEmpty(inputConfirmNewPass.getText().toString())
                || inputCountry.getSelectedItemPosition() == 0
                || inputAccountType.getSelectedItemPosition() == 0) {
            Toast.makeText(Account_Edit.this, "All areas must be filled.", Toast.LENGTH_LONG).show();
        }
        else {
            // INITIALIZE USER INFO VARIABLES
            String name = inputName.getText().toString();
            String street = inputStreet.getText().toString();
            String city = inputCity.getText().toString();
            String province = inputProvince.getText().toString();
            String country = inputCountry.getSelectedItem().toString();
            int countrySpinner = inputCountry.getSelectedItemPosition();
            String postal = inputPostal.getText().toString();
            String phone = inputPhone.getText().toString();
            String email = inputEmail.getText().toString();
            String newPass = inputNewPass.getText().toString();
            String confirmNewPass = inputConfirmNewPass.getText().toString();
            String accountType = inputAccountType.getSelectedItem().toString();
            int accountTypeSpinner = inputAccountType.getSelectedItemPosition();

            // MORE ERROR HANDLING
            if (phone.length() != 10) { // MUST BE 10 DIGITS
                inputPhone.setError("Enter a 10 digit phone number.");
            }
            else if (!email.contains("@") || !email.contains(".")) { // MUST HAVE "@" AND A PERIOD
                inputEmail.setError("Enter a valid E-mail address.");
            }
            else if (!Objects.equals(newPass, confirmNewPass)) { // PASSWORD MUST MATCH
                inputNewPass.setError("Passwords do not match.");
            }
            else {
                boolean emailExists = foodLoopDB.checkEmailExists(email);

                if (!emailExists) { // CHECK FOR EXISTING ACCOUNT
                    inputEmail.setError("This email isn't in the database.");
                    Toast.makeText(this, "Account not found.", Toast.LENGTH_LONG).show();
                }
                else {
                    boolean updated = foodLoopDB.updateAccount( // UPDATE ACCOUNT
                            name, street, city, province, country, countrySpinner,
                            postal, phone, email, newPass, accountType, accountTypeSpinner
                    );
                    // PROVIDE CONFIRMATION TO THE USER
                    if (updated)
                        Toast.makeText(this, "Account Updated!", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
                }
                // DO WE FORCE THE USER TO LEAVE THE PAGE AFTER UPDATING?
            startActivity(new Intent(Account_Edit.this, Account_Profile.class));
            }
        }
    }

    public void toProfilePage(View view) {
        startActivity(new Intent(Account_Edit.this, Account_Profile.class));
    }
}