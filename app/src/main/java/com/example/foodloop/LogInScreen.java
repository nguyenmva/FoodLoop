package com.example.foodloop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LogInScreen extends AppCompatActivity {

    private CheckBox cbRememberMe;
    private SharedPreferences sharedPreference;
    private static final String SHARED_PREF_NAME = "LOG_IN_CREDENTIALS";


    // DB STUFF
    private DatabaseHelper foodLoopDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView title = findViewById(R.id.regTitle);
        EditText email = findViewById(R.id.userEmail);
        EditText password = findViewById(R.id.userPass);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnSignUp = findViewById(R.id.btnSignUp);
        TextView login = findViewById(R.id.txtLogin);
        ImageView imagelogo = findViewById(R.id.fdLogo);
        CheckBox cbRememberMe = findViewById(R.id.cbRememberMe);

        sharedPreference = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        foodLoopDB = new DatabaseHelper(this);

        String savedEmail  = sharedPreference.getString("email", "");
        String savedPassword = sharedPreference.getString("password", "");

        boolean isChecked = sharedPreference.getBoolean("isChecked", false);

        if (isChecked){
            email.setText(savedEmail);
            password.setText(savedPassword);
            cbRememberMe.setChecked(true);
        }

        btnSignUp.setOnClickListener(v -> {
            startActivity(new Intent(LogInScreen.this, CreateAccount.class));
        });

        btnLogin.setOnClickListener(v->{
            String userEmail =  email.getText().toString();
            String userPass =  password.getText().toString();

            if(TextUtils.isEmpty(userEmail)){
                email.setError("Username cannot be empty");
                Toast.makeText(LogInScreen.this, "Please enter your username", Toast.LENGTH_LONG).show();
            }
            else if(TextUtils.isEmpty(userPass)){
                password.setError("Password cannot be empty");
                Toast.makeText(LogInScreen.this, "Please enter your password", Toast.LENGTH_LONG).show();
            }
//            else{
//                boolean emailExists = foodLoopDB.checkEmailExists(userEmail);
//
//                if (!emailExists) { // CHECK FOR EXISTING ACCOUNT THEN FOR MATCHING PASSWORD
//                    email.setError("This email isn't in the database.");
//                    Toast.makeText(this, "Account not found.", Toast.LENGTH_LONG).show();
//                }
//                else if (!foodLoopDB.checkLoginCredentials(userEmail, userPass)) {
//                    password.setError("Wrong!");
//                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
//                }
                else {
                    SharedPreferences.Editor editor = sharedPreference.edit();

                    if (cbRememberMe.isChecked()){
                        editor.putString("email", userEmail);
                        editor.putString("password", userPass);
                        editor.putBoolean("isChecked", true);
                    }else{
                        editor.clear();
                    }
                    editor.apply();

                    Toast.makeText(LogInScreen.this, "Log in Successful!!!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LogInScreen.this, DonationHomePage.class));
                        // As of 3/30, updated to Donation Home Page for now -Gia
                        // Change this to the Home page when that's up.
//                }
            }
        });
    }
}