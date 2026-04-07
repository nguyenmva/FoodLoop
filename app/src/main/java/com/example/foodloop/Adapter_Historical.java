package com.example.foodloop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.*;

public class Adapter_Historical extends RecyclerView.Adapter<Adapter_Historical.ViewHolder> {

    private ArrayList<String[]> data;
    String mode;
    Context context;
    DatabaseHelper foodLoopDB;

    public Adapter_Historical(ArrayList<String[]> data, Context context, String mode) {
        this.data = data;
        this.context = context;
        this.mode = mode;
        this.foodLoopDB = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public Adapter_Historical.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.item_historical, parent, false);
        return new Adapter_Historical.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Historical.ViewHolder holder, int position) {
        String[] item = data.get(position);
        switch (mode) {
            case "DonationHistory":
                holder.tvItem.setText(item[0]);
                holder.tvLocation.setText(item[1]);
                holder.tvRequestor.setText(item[2]);

                //Show requester row
                holder.requestorRow.setVisibility(View.VISIBLE);

                //Hide the status row: label + text view
                holder.statusRow.setVisibility(View.GONE);
                break;
            case "RequestHistory":
                holder.tvItem.setText(item[0]);
                holder.tvStatus.setText(item[1]);
                holder.tvLocation.setText(item[2]);

                //Show status row
                holder.statusRow.setVisibility(View.VISIBLE);

                //Hide the requestor row
                holder.requestorRow.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItem, tvStatus, tvLocation, tvDateRequested, tvRequestor;
        LinearLayout statusRow, requestorRow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tvItem);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvRequestor = itemView.findViewById(R.id.tvRequestor);

            statusRow = itemView.findViewById(R.id.statusRow);
            requestorRow = itemView.findViewById(R.id.requestorRow);
        }
    }
}
