package com.waiter.ordertaking.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.waiter.ordertaking.R;
import com.waiter.ordertaking.response.OrderDetailsResponse;

import java.util.List;

public class WaiterOrderDetailListAdapter extends RecyclerView.Adapter<WaiterOrderDetailListAdapter.KitchenHolder> {

    private String TAG = "WaiterOrderDetailListAdapter";
    Context context;
    List<OrderDetailsResponse.DataBean.ItemDetailBean> item_detail;

    public WaiterOrderDetailListAdapter(Context context, List<OrderDetailsResponse.DataBean.ItemDetailBean> item_detail) {
        this.context = context;
        this.item_detail = item_detail;
    }

    @NonNull
    @Override
    public KitchenHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_table_item_list,parent,false);
        return new KitchenHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull KitchenHolder holder, int position) {
        holder.txt_item_name.setText(item_detail.get(position).getItem_name());
        holder.txt_item_count.setText(item_detail.get(position).getItem_count()+"");
        holder.txt_item_prize.setText("\u20B9 "+item_detail.get(position).getItem_price());
        holder.txt_item_status.setText(item_detail.get(position).getItem_status());



       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TableItemList.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id",data.get(position).get_id());
                intent.putExtra("orderid",data.get(position).getOrder_id());
                intent.putExtra("restid",data.get(position).getRest_id());
                context.startActivity(intent);
            }
        });*/




    }

    @Override
    public int getItemCount() {
        return item_detail.size();
    }

    public static class KitchenHolder extends RecyclerView.ViewHolder {

        TextView txt_item_name, txt_item_count,txt_item_prize,txt_item_status;


        public KitchenHolder(@NonNull View itemView) {
            super(itemView);
            txt_item_name = itemView.findViewById(R.id.txt_item_name);
            txt_item_count = itemView.findViewById(R.id.txt_item_count);
            txt_item_prize = itemView.findViewById(R.id.txt_item_prize);
            txt_item_status = itemView.findViewById(R.id.txt_item_status);

        }
    }
}
