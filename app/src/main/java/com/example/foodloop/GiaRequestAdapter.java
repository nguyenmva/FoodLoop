package com.example.foodloop;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GiaRequestAdapter extends RecyclerView.Adapter<GiaRequestAdapter.ViewHolder> {

    final List<String[]> data;
    private static final String[] options = {"Pickups", "Deliveries"};

    public GiaRequestAdapter(List<String[]> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_search_donations_item_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String[] item = data.get(position);
        holder.tvItemName.setText(item[0]);
        holder.tvExpiryDate.setText("Expires: " + item[1]);
        holder.tvQuantity.setText("Qty: " + item[2]);
        holder.tvDonorInfo.setText("Donor Location: " + item[3]);
        holder.tvOfferType.setText(item[4]);


        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), SubmitRequestPage.class);
            intent.putExtra("DONATION_ID", item[0]); // Pass the ID to the next screen
            intent.putExtra("ITEM_NAME", item[1]);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItemName, tvExpiryDate, tvQuantity, tvDonorInfo, tvOfferType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvExpiryDate = itemView.findViewById(R.id.tvExpiryDate);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvDonorInfo = itemView.findViewById(R.id.tvDonorInfo);
            tvOfferType = itemView.findViewById(R.id.tvOfferType);

        }
    }
}
