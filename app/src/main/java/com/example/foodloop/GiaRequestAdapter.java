package com.example.foodloop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GiaRequestAdapter extends RecyclerView.Adapter<GiaRequestAdapter.ViewHolder> {

    final List<String[]> data;

    public GiaRequestAdapter(List<String[]> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_donation, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String[] item = data.get(position);

        holder.tvStatus.setText(item[0]);
        holder.tvItem.setText(item[1]);
        holder.tvRecipient.setText(item[2]);

        holder.spinnerRequest.setVisibility(View.GONE);
        holder.btnNotify.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvStatus, tvItem, tvRecipient;
        Spinner spinnerRequest;
        Button btnNotify, btnEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvItem = itemView.findViewById(R.id.tvItem);
            tvRecipient = itemView.findViewById(R.id.tvRecipient);
            spinnerRequest = itemView.findViewById(R.id.spinnerRequest);
            btnNotify = itemView.findViewById(R.id.btnNotify);
            btnEdit = itemView.findViewById(R.id.btnEdit);
        }
    }
}
