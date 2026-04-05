package com.example.foodloop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.*;

public class UniversalAdapter extends RecyclerView.Adapter<UniversalAdapter.ViewHolder> {

    private ArrayList<String[]> data;
    String mode;
    Context context;
    DatabaseHelper foodLoopDB;

    public UniversalAdapter(ArrayList<String[]> data, Context context, String mode) {
        this.data = data;
        this.context = context;
        this.mode = mode;
        this.foodLoopDB = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public UniversalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.item_universal, parent, false);
        return new UniversalAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UniversalAdapter.ViewHolder holder, int position) {
        String[] item = data.get(position);
        switch (mode) {
            case "ActiveRequest":
                //Values: Item, Status, Location
                holder.tvItem.setText(item[0]);
                holder.tvStatus.setText(item[1]);
                holder.tvLocation.setText(item[2]);

                holder.tvDateRequested.setVisibility(View.GONE);
                holder.tvRequestor.setVisibility(View.GONE);
                break;
            case "RequestHistory":
                //Values: Date Requested, Item, Location
                holder.tvDateRequested.setText(item[0]);
                holder.tvItem.setText(item[1]);
                holder.tvLocation.setText(item[2]);

                holder.tvStatus.setVisibility(View.GONE);
                holder.tvRequestor.setVisibility(View.GONE);
                break;
            case "DonationHistory":
                //Values: Status, Item, Requestor
                holder.tvStatus.setText(item[0]);
                holder.tvItem.setText(item[1]);
                holder.tvRequestor.setText(item[2]);

                holder.tvDateRequested.setVisibility(View.GONE);
                holder.tvLocation.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItem, tvStatus, tvLocation, tvDateRequested, tvRequestor;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tvItem);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvDateRequested = itemView.findViewById(R.id.tvDateRequested);
            tvRequestor = itemView.findViewById(R.id.tvRequestor);
        }
    }
}
