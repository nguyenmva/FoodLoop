package com.example.foodloop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GiaDonateAdapter extends RecyclerView.Adapter<GiaDonateAdapter.ViewHolder> {

    final List<String[]> data;
    private DatabaseHelper foodLoopDB;
    private static final String[] options = {"Select", "Approved", "Rejected"};

    public GiaDonateAdapter(List<String[]> data) {
        this.data = data;
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
        holder.tvStatus.setText(item[0]);
        holder.tvItem.setText(item[1]);
        holder.tvRecipient.setText(item[2]);

        //Start bind
        holder.isBinding = true;

        //Condition to show spinner control
        if (item[2] == null) {
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
        boolean isBinding = false;

        private final GiaDonateAdapter adapter;

        public ViewHolder(@NonNull View itemView, GiaDonateAdapter adapter) {
            super(itemView);
            this.adapter = adapter;

            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvItem = itemView.findViewById(R.id.tvItem);
            tvRecipient = itemView.findViewById(R.id.tvRecipient);
            spinnerRequest = itemView.findViewById(R.id.spinnerRequest);

            spinnerRequest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    if (isBinding) return;
                    if (pos == 0) return;

                    int currentPos = getAbsoluteAdapterPosition();
                    if (currentPos != RecyclerView.NO_POSITION) {
                        adapter.data.get(currentPos)[0] = options[pos];
                        adapter.notifyItemChanged(currentPos);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {}
            });
        }
    }

}
