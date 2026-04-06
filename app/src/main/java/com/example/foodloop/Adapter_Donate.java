package com.example.foodloop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter_Donate extends RecyclerView.Adapter<Adapter_Donate.ViewHolder> {
    Context context;
    final List<String[]> data;
    private DatabaseHelper foodLoopDB;
    private static final String[] options = {"Select", "Approved", "Rejected"};

    public Adapter_Donate(List<String[]> data, Context context) {
        this.data = data;
        this.context = context;
        this.foodLoopDB = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_donation, parent, false);
        return new ViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String[] item = data.get(position);

        //Set text views
        holder.tvStatus.setText(item[1]);
        holder.tvItem.setText(item[2]);
        holder.tvRecipient.setText(item[3] == null ? "" : item[3]);

        //Start bind
        holder.isBinding = true;

        //Condition to show spinner control
        if (item[3] == null) {
            //No requestor = no approval/rejection
            holder.spinnerRequest.setVisibility(View.GONE);
        } else {
            //Requestor = can approve/reject
            holder.spinnerRequest.setVisibility(View.VISIBLE);

            //Approve/reject possibilities
            if (holder.spinnerRequest.getAdapter() == null) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(holder.itemView.getContext(),
                        android.R.layout.simple_spinner_item, options);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                holder.spinnerRequest.setAdapter(adapter);
            }

            //Assign spinner selection
            String status = item[1] == null ? "" : item[1];
            int index = 0;
            if (status.equals("Approved")) index = 1;
            else if (status.equals("Rejected")) index = 2;
            holder.spinnerRequest.setSelection(index);
        }
        //End bind
        holder.isBinding = false;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvStatus, tvItem, tvRecipient;
        Spinner spinnerRequest;
        Button btnNotify, btnEdit;
        boolean isBinding = false;


        private final Adapter_Donate adapter;

        public ViewHolder(@NonNull View itemView, Adapter_Donate adapter) {
            super(itemView);
            this.adapter = adapter;

            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvItem = itemView.findViewById(R.id.tvItem);
            tvRecipient = itemView.findViewById(R.id.tvRecipient);
            spinnerRequest = itemView.findViewById(R.id.spinnerRequest);
            btnNotify = itemView.findViewById(R.id.btnNotify);
            btnEdit = itemView.findViewById(R.id.btnEdit);

            btnNotify.setOnClickListener(v -> {
                Toast.makeText(adapter.context, "Reminder Sent!", Toast.LENGTH_SHORT).show();
            });

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentPos = getAbsoluteAdapterPosition();
                    // Find the row that the button belongs to.

                    if (currentPos != RecyclerView.NO_POSITION) {
                        String donationIDPos = adapter.data.get(currentPos)[0];
                        // Get data from List<String[]> data by index. (CTRL-F "Adapter Index" on ActiveDonations.java to see index values)
                        int donationID = Integer.parseInt(donationIDPos);

                        Intent intent = new Intent(adapter.context, Donation_Edit.class);
                        intent.putExtra("extraDonationID", donationID);
                        adapter.context.startActivity(intent);
                    }
                }
            });


//                    SharedPreferences preferences = adapter.context.getSharedPreferences("savedDonationID", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = preferences.edit();
//                    editor.putInt("id_Donation", 1); //Edit Donations page
//                    editor.apply();

            spinnerRequest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    if (isBinding) return; //Disable listener
                    if (pos == 0) return; //Do not accept "Select" as a response

                    int currentPos = getAdapterPosition(); //Find row of interaction
                    if (currentPos == RecyclerView.NO_POSITION) return;

                    String newStatus = options[pos]; //Get index of option: 0 = select, 1 = approve, 2 = reject
                    String donationID = adapter.data.get(currentPos)[0]; //Get donationID from data list
                    String requestID = adapter.data.get(currentPos)[4]; //Get requestID from data list

                    if (requestID == null || requestID.trim().isEmpty()) {
                        Toast.makeText(adapter.context, "No request found for this donation", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    boolean success = false;
                    if (newStatus.equals("Rejected")) {
                        //Update DB
                        success = adapter.foodLoopDB.rejectRequest(Integer.parseInt(requestID));

                        //Status: Rejected = Remove
                        if (success) {
                            adapter.data.remove(currentPos);
                            adapter.notifyItemRemoved(currentPos);
                        }
                    } else if (newStatus.equals("Approved")){
                        //Update DB
                        success = adapter.foodLoopDB.approveRequest(
                                Integer.parseInt(requestID), Integer.parseInt(donationID));

                        //Status: Approved = update row
                        if (success) {
                            adapter.data.get(currentPos)[1] = newStatus;
                            adapter.notifyItemChanged(currentPos);
                        }
                    }

                    if(!success){
                        //Update list: fail
                        Toast.makeText(adapter.context, "Failed to update status", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {}
            });
        }
    }

}
