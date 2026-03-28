package com.example.foodloop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RecyclerDonationsAdapter extends RecyclerView.Adapter<RecyclerDonationsAdapter.ViewHolder> {

    private ArrayList<RecyclerRowObject> donationList;

    // Constructor to receive the data from the Activity
    public RecyclerDonationsAdapter(ArrayList<RecyclerRowObject> donationList) {
        this.donationList = donationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This "inflates" your row layout (the XML for a single item)
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_donation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data object for the current row
        RecyclerRowObject currentItem = donationList.get(position);

        // Set the text for each view in the row
        holder.tvStatus.setText(currentItem.getStatus());
        holder.tvItemName.setText(currentItem.getItemName());
        holder.tvReceiver.setText(currentItem.getRecipient());
    }

    @Override
    public int getItemCount() {
        return donationList.size();
    }

    // This class "holds" the views for a single row to improve performance
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvStatus, tvItemName, tvReceiver;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Link these to the IDs inside your recycler_row.xml
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvReceiver = itemView.findViewById(R.id.tvRecipient);
        }
    }
}