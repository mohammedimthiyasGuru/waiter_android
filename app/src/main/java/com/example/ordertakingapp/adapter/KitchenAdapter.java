package com.example.ordertakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordertakingapp.Pojo;
import com.example.ordertakingapp.Pojo_kitchen;
import com.example.ordertakingapp.R;
import com.example.ordertakingapp.activity.NotificationDetail;
import com.example.ordertakingapp.activity.TableItemList;
import com.example.ordertakingapp.response.KitchenDashoboardListResponse;

import java.util.List;

public class KitchenAdapter extends RecyclerView.Adapter<KitchenAdapter.KitchenHolder> {

    Context context;
    List<KitchenDashoboardListResponse.DataBean> data;

    public KitchenAdapter(Context context, List<KitchenDashoboardListResponse.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public KitchenHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.kitchen_card,parent,false);
        return new KitchenHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KitchenHolder holder, int position) {
        holder.txt_table_number.setText(data.get(position).getTable_no());
        holder.txt_table.setText(data.get(position).getTable_name());
        holder.txt_taken.setText(data.get(position).getTaken_by());
        holder.txt_date.setText(data.get(position).getOrder_at());
        holder.txt_status.setText(data.get(position).getStatus());

     /*   holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TableItemList.class);
                view.getContext().startActivity(intent);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class KitchenHolder extends RecyclerView.ViewHolder {

        TextView txt_table_number, txt_table,txt_taken,txt_date,txt_status;


        public KitchenHolder(@NonNull View itemView) {
            super(itemView);
            txt_table_number = itemView.findViewById(R.id.txt_table_number);
            txt_table = itemView.findViewById(R.id.txt_tablename);
            txt_taken = itemView.findViewById(R.id.txt_taken);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_status = itemView.findViewById(R.id.txt_status);
        }
    }
}
