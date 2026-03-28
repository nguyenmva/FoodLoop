package com.example.foodloop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditAccount extends AppCompatActivity {
    String name, streetName, city, province, country, contactNumber, postalCode, email, password, confirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // READING THE DATA FROM SHARED PREFERENCES
    public void updateProfile(View view) {
        EditText inputName = findViewById(R.id.inputName);
        EditText inputStreet = findViewById(R.id.inputStreet);
        EditText inputCity = findViewById(R.id.inputCity);
        EditText inputProvince = findViewById(R.id.inputProvince);
        Spinner inputCountry = findViewById(R.id.inputCountry);
        EditText inputPostalCode = findViewById(R.id.inputPostal);
        EditText inputContactNumber = findViewById(R.id.inputPhone);
        EditText inputEmail = findViewById(R.id.inputEmail);
        EditText inputPassword = findViewById(R.id.inputPass);
        EditText inputConfirmPass = findViewById(R.id.inputConfirmPass);
        SharedPreferences prefFile = getSharedPreferences("UserInfo", MODE_PRIVATE);

        name = prefFile.getString("nameKey", "");
        streetName = prefFile.getString("streetNameKey", "");
        city = prefFile.getString("cityKey", "");
        province = prefFile.getString("provinceKey", "");
        country = prefFile.getString("countryKey", "");
        postalCode = prefFile.getString("postalCodeKey", "");
        contactNumber = prefFile.getString("contactNumberKey", "");
        email = prefFile.getString("emailKey", "");
        password = prefFile.getString("passwordKey", "");

        inputName.setText(name);
        inputStreet.setText(streetName);
        inputCity.setText(city);
        inputProvince.setText(province);
        inputCountry.setSelection(1);
        inputPostalCode.setText(postalCode);
        inputContactNumber.setText(contactNumber);
        inputEmail.setText(email);
        inputPassword.setText(password);

        Toast.makeText(EditAccount.this, "Account information has been read from xml file.", Toast.LENGTH_LONG).show();
    }

    // PAGE LINKS
    public void toTestPage(View view) {
        startActivity(new Intent(EditAccount.this, MainActivity.class));
    }

//    public void toPrintPage(View view) {
//        startActivity(new Intent(EditAccount.this, PrintActivity.class));
//    }
}