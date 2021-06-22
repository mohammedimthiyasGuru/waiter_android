package com.waiter.ordertaking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.waiter.ordertaking.Pojo_OrderDetail;
import com.waiter.ordertaking.R;

import java.util.List;

public class OrderDetailAdapter extends  RecyclerView.Adapter<OrderDetailAdapter.OrderDetailHolder>{

    Context context;
    List<Pojo_OrderDetail> orderDetailsList;

    public OrderDetailAdapter(Context context, List<Pojo_OrderDetail> orderDetailsList) {
        this.context = context;
        this.orderDetailsList = orderDetailsList;
    }

    @NonNull
    @Override
    public OrderDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_detail_card,parent,false);
        return new OrderDetailHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailHolder holder, int position) {
        holder.txt_table_number.setText(orderDetailsList.get(position).getTable_number());
        holder.txt_table.setText(orderDetailsList.get(position).getTable_name());
        holder.txt_taken.setText(orderDetailsList.get(position).getTaken_by());
        holder.txt_date.setText(orderDetailsList.get(position).getDate());
        holder.txt_status.setText(orderDetailsList.get(position).getStatus());
        holder.txt_cost.setText(orderDetailsList.get(position).getCost());

    }

    @Override
    public int getItemCount() {
        return orderDetailsList.size();
    }

    public static class OrderDetailHolder extends RecyclerView.ViewHolder {

        TextView txt_table_number, txt_table,txt_taken,txt_date,txt_status,txt_cost;

        public OrderDetailHolder(@NonNull View itemView) {
            super(itemView);

            txt_table_number = itemView.findViewById(R.id.txt_table_number);
            txt_table = itemView.findViewById(R.id.txt_tablename);
            txt_taken = itemView.findViewById(R.id.txt_taken);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_status = itemView.findViewById(R.id.txt_status);
            txt_cost = itemView.findViewById(R.id.txt_cost);



        }
    }
}
