package com.example.foodloop;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter_Request extends RecyclerView.Adapter<Adapter_Request.ViewHolder> {

    final List<String[]> data;
    private static final String[] options = {"Pickups", "Deliveries"};

    public Adapter_Request(List<String[]> data) {
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
            showSelectionDialog(v.getContext(), item);
        });
    }

    private void showSelectionDialog(Context context, String[] item) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialogbox_delivery_pickup, null);
        Spinner spinner = view.findViewById(R.id.spinnerMethod);

        new AlertDialog.Builder(context)
                .setTitle("Request " + item[0]) // item[0] is the Name
                .setView(view)
                .setPositiveButton("Confirm Request", (dialog, which) -> {
                    String selectedMethod = spinner.getSelectedItem().toString();
                    processRequest(context, item, selectedMethod);
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void processRequest(Context context, String[] item, String method) {
        SharedPreferences sp = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String userEmail = sp.getString("email", "");

        DatabaseHelper db = new DatabaseHelper(context);

        Toast.makeText(context, "Request sent for " + method, Toast.LENGTH_SHORT).show();
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
