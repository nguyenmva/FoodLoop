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
    //private DatabaseHelper foodLoopDB;
    //EditText inputTestStatus, inputTestItemName, inputTestRecipient;
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

//        inputTestStatus = findViewById(R.id.inputTestStatus);
//        inputTestItemName = findViewById(R.id.inputTestItemName);
//        inputTestRecipient = findViewById(R.id.inputTestRecipient);
        btnActDonations = findViewById(R.id.btnToManageDonations);

        //foodLoopDB = new DatabaseHelper(this);
    }
    public void showAllAccounts(View view){
//        Cursor result = foodLoopDB.getAllAccounts();
//        if(result.getCount() == 0){
//            showMessage("Error", "No Data Found");
//            result.close();
//        }
//        else {
//            Toast.makeText(this, "Number of Accounts: " + result.getCount(), Toast.LENGTH_LONG).show();
////            startActivity(new Intent(MainActivity.this, ViewAccounts.class));
//        }
//        result.close();
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void addHistoryRecord(View view){
//        // ERROR HANDLING, NO EMPTY FIELDS.
//        if (TextUtils.isEmpty(inputTestStatus.getText().toString())
//                || TextUtils.isEmpty(inputTestItemName.getText().toString())
//                || TextUtils.isEmpty(inputTestRecipient.getText().toString())) {
//            Toast.makeText(this, "All areas must be filled.", Toast.LENGTH_LONG).show();
//        }
//        else{
//            boolean inserted = foodLoopDB.createDonationHistory(
//                    inputTestStatus.getText().toString(),
//                    inputTestItemName.getText().toString(),
//                    inputTestRecipient.getText().toString()
//            );
//            if(inserted)
//                Toast.makeText(this,"Record Added",Toast.LENGTH_LONG).show();
//            else
//                Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
//        }
    }


    public void checkHistoryRecords(View view){
//        Cursor result = foodLoopDB.getAllDonationHistory();
//        if(result.getCount() == 0){
//            showMessage("Error", "No Data Found");
//        }
//        else {
//            Toast.makeText(this, "Number of Donations: " + result.getCount(), Toast.LENGTH_LONG).show();
//        }
    }

    // Page Links
    public void toCreatePage(View view) {
//        startActivity(new Intent(MainActivity.this, CreateAccount.class));
    }
    public void toProfilePage(View view) {
//        startActivity(new Intent(MainActivity.this, Profile.class));
    }
    public void toEditPage(View view) {
//        startActivity(new Intent(MainActivity.this, EditAccount.class));
    }
    public void toDonationHistoryPage(View view) {
        startActivity(new Intent(MainActivity.this, HistoryDonation.class));
    }
//    public void toRequestHistoryPage(View view) {
//        startActivity(new Intent(MainActivity.this, HistoryRequest.class));
//    }
    public void toManageDonationsPage(View view) {
        startActivity(new Intent(MainActivity.this, ActiveDonations.class));
    }
}