package com.waiter.ordertaking.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.waiter.ordertaking.R;
import com.waiter.ordertaking.interfaces.CheckTableAvaStatusListener;
import com.waiter.ordertaking.request.AdminTableListRequest;
import com.waiter.ordertaking.response.AdminTableListResponse;
import com.waiter.ordertaking.response.TableListResponse;

import java.util.List;


public class AdminTableListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "AdminTableListAdapter";

    private Context context;

    List<AdminTableListResponse.DataBean> data;

    AdminTableListResponse.DataBean currentItem;

    CheckTableAvaStatusListener checkTableAvaStatusListener;


    public AdminTableListAdapter(Context context, List<AdminTableListResponse.DataBean> data, CheckTableAvaStatusListener checkTableAvaStatusListener) {
        this.context = context;
        this.data = data;
        this.checkTableAvaStatusListener = checkTableAvaStatusListener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_tablelist, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);

    }

    @SuppressLint({"SetTextI18n", "ObsoleteSdkInt"})
    private void initLayoutOne(ViewHolderOne holder, final int position) {
        currentItem = data.get(position);
        if(currentItem.getTable_no() != null&&!currentItem.getTable_no().isEmpty()) {
            holder.txt_table_number.setText(currentItem.getTable_no());
        }
        else {

            holder.txt_table_number.setText("");
        }

        if(currentItem.getTable_status() != null&&!currentItem.getTable_status().isEmpty()) {
            holder.txt_tablestatus.setText(currentItem.getTable_status());
        }
        else {
            holder.txt_tablestatus.setText("");
        }

        if(currentItem.getTable_desc() != null&&!currentItem.getTable_desc().isEmpty()) {
            holder.txt_desc.setText(currentItem.getTable_desc());
        }
        else {
            holder.txt_desc.setText("");
        }

        if(currentItem.getTable_order_status() != null&&!currentItem.getTable_order_status().isEmpty()) {
            holder.txt_availbstatus.setText(currentItem.getTable_order_status());
        }
        else {
            holder.txt_availbstatus.setText("");
        }

    }

    @Override
    public int getItemCount() {
        return data.size();



    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_table_number,txt_tablestatus,txt_desc,txt_availbstatus;



        public ViewHolderOne(View itemView) {
            super(itemView);

            txt_table_number = itemView.findViewById(R.id.txt_table_number);

            txt_tablestatus = itemView.findViewById(R.id.txt_tablestatus);

            txt_desc = itemView.findViewById(R.id.txt_desc);

            txt_availbstatus = itemView.findViewById(R.id.txt_availbstatus);


        }




    }

}



