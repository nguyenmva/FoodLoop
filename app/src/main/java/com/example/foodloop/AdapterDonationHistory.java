//package com.example.foodloop;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class AdapterDonationHistory
//        extends RecyclerView.Adapter<AdapterDonationHistory.ViewHolder> {
////    ArrayList<RecyclerRowObject> donationRows;
////    List<String[]> data;
////    public RecyclerDonationsAdapter(ArrayList<RecyclerRowObject> donationRows) {
////        this.donationRows = donationRows;
//    final List<String[]> data;
//    private final String[] options = {"Select", "Approved", "Rejected"};
//
//    public AdapterDonationHistory(List<String[]> data) {
//        this.data = data;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.recycler_history_donation_list_row, parent, false);
//        return new ViewHolder(v, this);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        String[] item = data.get(position);
//
//        holder.tvStatus.setText(item[0]);
//        holder.tvItemName.setText(item[1]);
//        holder.tvRecipient.setText(item[2]);
//
//        holder.isBinding = true;
//
////        if (holder.spinnerRequest.getAdapter() == null) {
////            ArrayAdapter<String> adapter = new ArrayAdapter<>(
////                    holder.itemView.getContext(),
////                    android.R.layout.simple_spinner_item,
////                    options
////            );
////            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////            holder.spinnerRequest.setAdapter(adapter);
////        }
////
////        holder.spinnerRequest.setSelection(0, false);
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
//        TextView tvStatus, tvItemName, tvRecipient;
//        boolean isBinding = false;
//
//        private final AdapterDonationHistory adapter;
//
//        public ViewHolder(@NonNull View itemView, AdapterDonationHistory adapter) {
//            super(itemView);
//            this.adapter = adapter;
//
//            tvStatus = itemView.findViewById(R.id.tvStatus);
//            tvItemName = itemView.findViewById(R.id.tvItemName);
//            tvRecipient = itemView.findViewById(R.id.tvRecipient);
//        }
//    }
//}
////
////    }
////    public RecyclerDonationsAdapter(List<String[]> data) {
////        this.data = data;
////    }
////
////    @NonNull
////    @Override
////    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////        LayoutInflater inflater = LayoutInflater.from(parent.getContext()); // Please inflate me on my parent (Recycler).
////        View view = inflater.inflate(R.layout.recycler_history_request_list_row, parent, false);
////
////        return new ViewHolder(view);
////    }
////
////    @Override
////    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
////        String[] item = data.get(position);
////
////        holder.status.setText(item[1]);
////        holder.itemName.setText(item[2]);
////        holder.recipient.setText(item[3]);
////
////
////
//////        holder.status.setText(donationRows.get(position).getStatus());
//////        holder.itemName.setText(donationRows.get(position).getItemName());
//////        holder.recipient.setText(donationRows.get(position).getRecipient());
////    }
////
////    @Override
////    public int getItemCount() { // To determine the positions of the viewable rows within the DB.
////        return donationRows.size();
////    }
////
////    public class ViewHolder extends RecyclerView.ViewHolder{
////        TextView  status, itemName, recipient;
////
////        public ViewHolder(@NonNull View itemView) {
////            super(itemView);
////
////            status = itemView.findViewById(R.id.tvStatus);
////            itemName = itemView.findViewById(R.id.tvItemName);
////            recipient = itemView.findViewById(R.id.tvRecipient);
////        }
////    }
////}
