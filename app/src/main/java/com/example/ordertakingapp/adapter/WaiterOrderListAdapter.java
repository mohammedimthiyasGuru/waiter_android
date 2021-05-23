package com.example.ordertakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordertakingapp.R;
import com.example.ordertakingapp.activity.TableItemListActivity;
import com.example.ordertakingapp.activity.WaiterOrderDetailListActivity;
import com.example.ordertakingapp.interfaces.OrderListClickListener;
import com.example.ordertakingapp.response.KitchenDashoboardListResponse;

import java.util.List;

public class WaiterOrderListAdapter extends RecyclerView.Adapter<WaiterOrderListAdapter.KitchenHolder> {

    private String TAG = "WaiterOrderListAdapter";
    Context context;
    List<KitchenDashoboardListResponse.DataBean> data;
    OrderListClickListener orderListClickListener;

    public WaiterOrderListAdapter(Context context, List<KitchenDashoboardListResponse.DataBean> data, OrderListClickListener orderListClickListener) {
        this.context = context;
        this.data = data;
        this.orderListClickListener = orderListClickListener;
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

        if(data.get(position).getWaiter_status()!=null&&!data.get(position).getWaiter_status().equals(""))

        {
            holder.txt_status.setText(data.get(position).getWaiter_status());

        }

        else {

            holder.txt_status.setText("");
        }



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WaiterOrderDetailListActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id",data.get(position).get_id());
                intent.putExtra("orderid",data.get(position).getOrder_id());
                intent.putExtra("restid",data.get(position).getRest_id());
                context.startActivity(intent);
            }

        });
        holder.btn_accept.setVisibility(View.GONE);

//        if(data.get(position).getChef_status() != null && data.get(position).getChef_status().isEmpty()){
//            holder.btn_accept.setVisibility(View.VISIBLE);
//        }else{
//
//        }
//
//        holder.btn_accept.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                orderListClickListener.orderListClickListener(data.get(position).getOrder_id());
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class KitchenHolder extends RecyclerView.ViewHolder {

        TextView txt_table_number, txt_table,txt_taken,txt_date,txt_status;
        Button btn_accept;


        public KitchenHolder(@NonNull View itemView) {
            super(itemView);
            txt_table_number = itemView.findViewById(R.id.txt_table_number);
            txt_table = itemView.findViewById(R.id.txt_tablename);
            txt_taken = itemView.findViewById(R.id.txt_taken);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_status = itemView.findViewById(R.id.txt_status);
            btn_accept = itemView.findViewById(R.id.btn_accept);
            btn_accept.setVisibility(View.GONE);
        }
    }
}
