package com.example.foodloop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.*;

public class Adapter_Request_Active extends RecyclerView.Adapter<Adapter_Request_Active.ViewHolder>{
    private ArrayList<String[]> data;
    Context context;

    public Adapter_Request_Active(ArrayList<String[]> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter_Request_Active.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.item_requested, parent, false);
        return new Adapter_Request_Active.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Request_Active.ViewHolder holder, int position) {
        String[] item = data.get(position);
        //Text Values: Item, Status, Donor, Location
        holder.tvItem.setText(item[0]);
        holder.tvStatus.setText(item[1]);
        holder.tvLocation.setText(item[2]);
        holder.tvRequestID.setText(item[3]);


        //View Button
        holder.btnView.setOnClickListener(v -> {
            Intent intent = new Intent(context, Request_View.class);
            intent.putExtra("REQUEST_ID", item[3]);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItem, tvStatus, tvLocation, tvRequestID;
        Button btnView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tvItem);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvRequestID = itemView.findViewById(R.id.tvRequestID);
            btnView = itemView.findViewById(R.id.btnView);
        }
    }
}
