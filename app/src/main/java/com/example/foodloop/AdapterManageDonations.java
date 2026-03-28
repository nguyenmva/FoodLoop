//package com.example.foodloop;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Spinner;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class AdapterManageDonations extends RecyclerView.Adapter {
//
//    final List<String[]> data;
//    private final String[] options = {"Select", "Approved", "Rejected"};
//
//    public AdapterManageDonations(List<String[]> data) {
//        this.data = data;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_donation, parent, false);
//        return new ViewHolder(v, this);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        String[] item = data.get(position);
//
//        holder.tvStatus.setText(item[0]);
//        holder.tvItem.setText(item[1]);
//        holder.tvRecipient.setText(item[2]);
//
//        holder.isBinding = true;
//
//        if (holder.spinnerRequest.getAdapter() == null) {
//            ArrayAdapter<String> adapter = new ArrayAdapter<>(
//                    holder.itemView.getContext(),
//                    android.R.layout.simple_spinner_item,
//                    options
//            );
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            holder.spinnerRequest.setAdapter(adapter);
//        }
//
//        holder.spinnerRequest.setSelection(0, false);
//
//        holder.isBinding = false;
//    }
//
//    @Override
//    public int getItemCount() {
//        return data.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//
//        TextView tvStatus, tvItem, tvRecipient;
//        Spinner spinnerRequest;
//        boolean isBinding = false;
//
//        private final AdapterManageDonations adapter;
//
//        public ViewHolder(@NonNull View itemView, AdapterManageDonations adapter) {
//            super(itemView);
//            this.adapter = adapter;
//
//            tvStatus = itemView.findViewById(R.id.tvStatus);
//            tvItem = itemView.findViewById(R.id.tvItem);
//            tvRecipient = itemView.findViewById(R.id.tvRecipient);
//            spinnerRequest = itemView.findViewById(R.id.spinnerRequest);
//
//            spinnerRequest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
//                    if (isBinding) return;
//                    if (pos == 0) return;
//
//                    int currentPos = getAbsoluteAdapterPosition();
//                    if (currentPos != RecyclerView.NO_POSITION) {
//                        adapter.data.remove(currentPos);
////                        adapter.notifyItemRemoved(currentPos);
//                    }
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {}
//            });
//        }
//    }
//}
