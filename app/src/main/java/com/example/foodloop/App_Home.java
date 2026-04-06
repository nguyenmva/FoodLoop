package com.example.foodloop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class App_Home extends AppCompatActivity {
    private DatabaseHelper foodLoopDB;
    private SharedPreferences sharedPreference;
    private static final String SHARED_PREF_NAME = "LOG_IN_CREDENTIALS";

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
        sharedPreference = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String savedEmail = sharedPreference.getString("email", "");

        foodLoopDB = new DatabaseHelper(this);

        Cursor gimmeID = foodLoopDB.getActiveRequests(savedEmail);

        if (gimmeID != null) {
//            String requestID = gimmeID.getString(gimmeID.getColumnIndexOrThrow(DatabaseHelper.REQUEST_ID_FLD));
//            int notificationFlag = gimmeID.getInt(gimmeID.getColumnIndexOrThrow(DatabaseHelper.REQUEST_NOTIFICATION_FLAG_FLD));

            int idColIndex = gimmeID.getColumnIndex(DatabaseHelper.REQUEST_ID_FLD);
            int flagColIndex = gimmeID.getColumnIndex(DatabaseHelper.REQUEST_NOTIFICATION_FLAG_FLD);
            int itemNameColIndex = gimmeID.getColumnIndex(DatabaseHelper.DONATION_ITEM_NAME_FLD);


            while (gimmeID.moveToNext()) {
                if (idColIndex != -1 && flagColIndex != -1) {
                    String requestID = gimmeID.getString(idColIndex);
                    String itemName = gimmeID.getString(itemNameColIndex);
                    int notificationFlag = gimmeID.getInt(flagColIndex);

                    if (notificationFlag == 1) {
                        Toast.makeText(this, "Come get " + itemName + "!)", Toast.LENGTH_LONG).show();

                        boolean gotIt = foodLoopDB.updateNotificationFlag(requestID, 0);
                        if (!gotIt) {
                            Toast.makeText(this, "Failed to clear notification", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        } else {
            Toast.makeText(this, "Error fetching data", Toast.LENGTH_LONG).show();
        }
        if (gimmeID != null) {
            gimmeID.close();
        }
    }
//            while (gimmeID.moveToNext()) {
//                String requestID = gimmeID.getString(gimmeID.getColumnIndexOrThrow(DatabaseHelper.REQUEST_ID_FLD));
//
//                boolean check = foodLoopDB.checkNotifications(savedEmail);
//                if (check) {
//                    Toast.makeText(this, "COME GET YOUR STUFF! (Request ID: " + requestID + ")", Toast.LENGTH_LONG).show();
//
//                    boolean gotIt = foodLoopDB.updateNotificationFlag(requestID, "0");
//                    if (!gotIt)
//                        Toast.makeText(this, "Failed to clear notification", Toast.LENGTH_SHORT).show();
//                }
//            }
//            gimmeID.close();
//        }
//        else {
//            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
//        }
//    }

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