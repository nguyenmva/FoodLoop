//package com.example.foodloop;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class AdapterAccounts
//        extends RecyclerView.Adapter<AdapterAccounts.MyViewHolder> {
//
//    ArrayList<RecyclerRowObject> accountRows;
//
//    public AdapterAccounts(ArrayList<RecyclerRowObject> accountRows) {
//        this.accountRows = accountRows;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        // To inflate the layout for each row, from code to something interactable.
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext()); // Please inflate me on my parent (Recycler).
//        View view = inflater.inflate(R.layout.recycler_accounts_list_row, parent, false);
//
//        return new MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        // Bind values/data to each row based on the row's position
//        holder.tvName.setText(accountRows.get(position).getName());
//        holder.tvStreet.setText(accountRows.get(position).getStreet());
//        holder.tvCity.setText(accountRows.get(position).getCity());
//        holder.tvProvince.setText(accountRows.get(position).getProvince());
//        holder.tvCountry.setText(accountRows.get(position).getCountry());
//        holder.tvPostal.setText(accountRows.get(position).getPostal());
//        holder.tvPhone.setText(accountRows.get(position).getPhone());
//        holder.tvEmail.setText(accountRows.get(position).getEmail());
//        holder.tvPassword.setText(accountRows.get(position).getPassword());
//    }
//
//    @Override
//    public int getItemCount() { // To determine the positions of the viewable rows within the DB.
//        return accountRows.size();
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder{
//        // Keeps all the row's UI elements so RecyclerView can reuse them.
//        // I.E. When an item moves off the screen, it's UI definitions are saved and applied to items coming into the screen.
//        TextView tvName, tvStreet, tvCity, tvProvince, tvCountry,
//                tvPostal, tvPhone, tvEmail, tvPassword;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            tvName = itemView.findViewById(R.id.row_name);
//            tvStreet = itemView.findViewById(R.id.row_street);
//            tvCity = itemView.findViewById(R.id.row_city);
//            tvProvince = itemView.findViewById(R.id.row_province);
//            tvCountry = itemView.findViewById(R.id.row_country);
//            tvPostal = itemView.findViewById(R.id.row_postal);
//            tvPhone = itemView.findViewById(R.id.row_phone);
//            tvEmail = itemView.findViewById(R.id.row_email);
//            tvPassword = itemView.findViewById(R.id.row_password);
//        }
//    }
//}
