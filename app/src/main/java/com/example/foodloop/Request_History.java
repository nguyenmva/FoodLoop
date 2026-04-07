package com.example.foodloop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Request_History extends AppCompatActivity {

    RecyclerView rv;
    public static ArrayList<String[]> requestHistory = new ArrayList<>();
    private DatabaseHelper foodLoopDB;
    private SharedPreferences sharedPreference;
    private static final String SHARED_PREF_NAME = "LOG_IN_CREDENTIALS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_request_history);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // INITIALIZE DATABASE AND SHARED PREFERENCES
        foodLoopDB = new DatabaseHelper(this);
        sharedPreference = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        getRequestHistory();
    }

    public void getRequestHistory() {
        String savedEmail = sharedPreference.getString("email", "");
        if (savedEmail.isEmpty()) {
            Toast.makeText(this, "Nothing in the sharedPreference", Toast.LENGTH_SHORT).show();
            return;
        }
        requestHistory.clear();

        Cursor requestCursor = foodLoopDB.getRequestHistory(savedEmail);
        if (requestCursor != null) {
            while (requestCursor.moveToNext()) {
                String itemName = requestCursor.getString(requestCursor.getColumnIndexOrThrow(DatabaseHelper.DONATION_ITEM_NAME_FLD));
                String status = requestCursor.getString(requestCursor.getColumnIndexOrThrow("Status"));
                String location = requestCursor.getString(requestCursor.getColumnIndexOrThrow(DatabaseHelper.REQUEST_LOCATION_FLD));

                Log.d("DEBUG", "Request Status = " + status);

                requestHistory.add(new String[]{itemName, status, location});
            }
            requestCursor.close();
        }

        rv = findViewById(R.id.rvReqHistTrack);
        rv.setLayoutManager(new LinearLayoutManager(this));
        Adapter_Historical adapter = new Adapter_Historical(requestHistory, this, "RequestHistory");
        rv.setAdapter(adapter);

    }

    public void toRequestHomePage(View view) {
        startActivity(new Intent(Request_History.this, Request_Home.class));
    }
}