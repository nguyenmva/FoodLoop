package com.example.foodloop;

import android.content.Intent;
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

public class CreateAccount extends AppCompatActivity {
    String name, street, city, province, country, postal,
            phone, email, password, confirmPass;
    int countrySpinnerSelection;
    EditText inputName, inputStreet, inputCity, inputProvince, inputPostal,
            inputPhone, inputEmail, inputPassword, inputConfirmPass;
    Spinner inputCountry;
    private DatabaseHelper foodLoopDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // INITIALIZE INTERFACE VARIABLES
        inputName = findViewById(R.id.inputName);
        inputStreet = findViewById(R.id.inputStreet);
        inputCity = findViewById(R.id.inputCity);
        inputProvince = findViewById(R.id.inputProvince);
        inputCountry = findViewById(R.id.inputCountry);
        inputPostal = findViewById(R.id.inputPostal);
        inputPhone = findViewById(R.id.inputPhone);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPass);
        inputConfirmPass = findViewById(R.id.inputConfirmPass);

        // INITIALIZE DATABASE
        foodLoopDB = new DatabaseHelper(this);
    }

    // WRITING TO DATABASE
    public void createAccount(View view) {
        // ERROR HANDLING, NO EMPTY FIELDS.
        if (TextUtils.isEmpty(inputName.getText().toString())
            || TextUtils.isEmpty(inputStreet.getText().toString())
            || TextUtils.isEmpty(inputCity.getText().toString())
            || TextUtils.isEmpty(inputProvince.getText().toString())
            || TextUtils.isEmpty(inputPostal.getText().toString())
            || TextUtils.isEmpty(inputPhone.getText().toString())
            || TextUtils.isEmpty(inputEmail.getText().toString())
            || TextUtils.isEmpty(inputPassword.getText().toString())
            || TextUtils.isEmpty(inputConfirmPass.getText().toString())) {
            Toast.makeText(CreateAccount.this, "All areas must be filled.", Toast.LENGTH_LONG).show();
        }
        else {
            // INITIALIZE USER INFO VARIABLES
            name = inputName.getText().toString();
            street = inputStreet.getText().toString();
            city = inputCity.getText().toString();
            province = inputProvince.getText().toString();
            country = inputCountry.getSelectedItem().toString();
            countrySpinnerSelection = inputCountry.getSelectedItemPosition();
            postal = inputPostal.getText().toString();
            phone = inputPhone.getText().toString();
            email = inputEmail.getText().toString();
            password = inputPassword.getText().toString();
            confirmPass = inputConfirmPass.getText().toString();

            // MORE ERROR HANDLING
            if (phone.length() != 10) { // MUST BE 10 DIGITS
                inputPhone.setError("Enter a 10 digit phone number.");
            }
            else if (!email.contains("@") || !email.contains(".")) { // MUST HAVE "@" AND A PERIOD
                inputEmail.setError("Enter a valid E-mail address.");
            }
            else if (!Objects.equals(password, confirmPass)) { // PASSWORD MUST MATCH
                inputPassword.setError("Passwords do not match.");
            }
            else {
                boolean inserted = foodLoopDB.createAccount(
                        name, street, city, province, country,
                        postal, phone, email, password
                );
                // PROVIDE CONFIRMATION TO THE USER
                if(inserted)
                    Toast.makeText(this, "Account Created!", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
            }
//            startActivity(new Intent(CreateAccount.this, MainActivity.class));
        }
    }
    public void showAllAccounts(View view){

        Cursor result = foodLoopDB.getAllAccounts();
        Toast.makeText(this, "Rows: " + result.getCount(), Toast.LENGTH_LONG).show();
//        Toast.makeText(this, "List size: " + accountRows.size(), Toast.LENGTH_LONG).show();
        result.close();
    }

    // Page Links
    public void toTestPage(View view) {
        startActivity(new Intent(CreateAccount.this, MainActivity.class));
    }
}
