package com.waiter.ordertaking.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.waiter.ordertaking.R;
import com.waiter.ordertaking.interfaces.AddItemListener;
import com.waiter.ordertaking.interfaces.RemoveItemListener;
import com.waiter.ordertaking.response.CategoryItemListResponse;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

             Context context;
             List<CategoryItemListResponse.DataBean> data;
             int count;
             AddItemListener addItemListener;

             RemoveItemListener removeItemListener;

    public MyAdapter(Context context, List<CategoryItemListResponse.DataBean> data, AddItemListener addItemListener, RemoveItemListener removeItemListener) {
        this.context = context;
        this.data = data;
        this.addItemListener = addItemListener;
        this.removeItemListener=removeItemListener;
    }


    @NonNull
             @Override
     public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                 View view = LayoutInflater.from(context).inflate(R.layout.catag_list,viewGroup,false);
                 return new MyHolder(view);
             }

             @Override
     public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

                 myHolder.listName.setText(data.get(i).getItem_name());
                 myHolder.listCount.setText(data.get(i).getItem_count()+"");

                 count = data.get(i).getItem_count();

                 Log.w("MyAdapter", String.valueOf(count));

                 myHolder.Minus.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {

                         count = Integer.parseInt(myHolder.listCount.getText().toString());
                         if(count != 0){
                             count--;
                             myHolder.listCount.setText(count + "" );

                             removeItemListener.removeitemdetails(data.get(i).getCat_id(),data.get(i).getItem_id(),

                                     data.get(i).getItem_name(),data.get(i).getPrice());



                         }
                     }
                 });

                 myHolder.Plus.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         count = Integer.parseInt(myHolder.listCount.getText().toString());
                         count++;
                         myHolder.listCount.setText(count + "" );

                         addItemListener.additemdetails(data.get(i).getCat_id(),data.get(i).getItem_id(),

                                 data.get(i).getItem_name(),data.get(i).getPrice());
                     }
                 });


    }

        @Override
     public int getItemCount() {
                 return data.size();
            }

            public static class MyHolder extends RecyclerView.ViewHolder {
                 TextView listName,listCount;
                 ImageView Minus,Plus;

             public MyHolder(@NonNull View itemView) {
                         super(itemView);

                         listName = itemView.findViewById(R.id.list_name);
                         listCount = itemView.findViewById(R.id.list_count);
                         Minus = itemView.findViewById(R.id.btn_minus);
                         Plus = itemView.findViewById(R.id.btn_plus);
                     }
     }

}



