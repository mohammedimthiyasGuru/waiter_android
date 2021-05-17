package com.example.ordertakingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordertakingapp.R;
import com.example.ordertakingapp.interfaces.CatagListListener;
import com.example.ordertakingapp.request.CreateOrderRequest;
import com.example.ordertakingapp.response.CategoryListResponse;

import java.util.List;

public class OverViewItemListAdapter extends RecyclerView.Adapter<OverViewItemListAdapter.NewMyHolder> {

    Context context;
    List<CreateOrderRequest.ItemDetailBean> itemDetailBeanList;

    public OverViewItemListAdapter(Context context,List<CreateOrderRequest.ItemDetailBean> itemDetailBeanLists) {
        this.context = context;
        this.itemDetailBeanList = itemDetailBeanLists;
    }

    @NonNull
    @Override
    public NewMyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_overviewitem,viewGroup,false);
        return new NewMyHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull NewMyHolder holder, int position) {

        int pos = position + 1;

        if(itemDetailBeanList.get(position).getItem_name()!=null&&!itemDetailBeanList.get(position).getItem_name().isEmpty()){

            holder.txt_itemname.setText("Item Name : " + itemDetailBeanList.get(position).getItem_name());
        }

//        if(itemDetailBeanList.get(position).getTable_no()!=null&&!itemDetailBeanList.get(position).getItem_name().isEmpty()){
//
//            holder.txt_table_number.setText("Table Number : "+ itemDetailBeanList.get(position).getTable_no());
//        }
//
//        if(itemDetailBeanList.get(position).getItem_price()!=0){
//
//            holder.txt_price.setText("Item Price : "+itemDetailBeanList.get(position).getItem_price());
//        }


        if(itemDetailBeanList.get(position).getItem_count()!=0){

            holder.txt_itemCount.setText("Item Count : "+itemDetailBeanList.get(position).getItem_count());
        }




    }

    @Override
    public int getItemCount() {
        return itemDetailBeanList.size();
    }

    public static class NewMyHolder extends RecyclerView.ViewHolder {
        TextView txt_itemname,txt_table_number,txt_price,txt_itemCount;

        public NewMyHolder(@NonNull View itemView) {
            super(itemView);
            txt_itemname = itemView.findViewById(R.id.txt_itemname);

            txt_table_number = itemView.findViewById(R.id.txt_table_number);

            txt_price = itemView.findViewById(R.id.txt_price);

            txt_itemCount = itemView.findViewById(R.id.txt_itemCount);
        }
    }
}
