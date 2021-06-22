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
import com.waiter.ordertaking.response.TableListResponse;



import java.util.List;



public class TableListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "TableListAdapter";

    private Context context;

    List<TableListResponse.DataBean> data;

    TableListResponse.DataBean currentItem;

    CheckTableAvaStatusListener checkTableAvaStatusListener;


    public TableListAdapter(Context context,   List<TableListResponse.DataBean> data, CheckTableAvaStatusListener checkTableAvaStatusListener) {
        this.context = context;
        this.data = data;
        this.checkTableAvaStatusListener = checkTableAvaStatusListener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_petlover_services, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);

    }

    @SuppressLint({"SetTextI18n", "ObsoleteSdkInt"})
    private void initLayoutOne(ViewHolderOne holder, final int position) {
        currentItem = data.get(position);
        if(currentItem.getTable_no() != null) {
            holder.txt_table_number.setText(currentItem.getTable_no());
        }


        Log.w(TAG,"currentItem.getBackground_color() : "+currentItem.getTable_color_code());

        if(currentItem.getTable_color_code() != null) {
            int color = Color.parseColor(currentItem.getTable_color_code());
            Log.w(TAG,"color : "+color);
            Drawable unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.layout_bg_service);
            Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
            DrawableCompat.setTint(wrappedDrawable, color);

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                holder.ll_root.setBackground(wrappedDrawable);
            } else {
                holder.ll_root.setBackgroundDrawable(wrappedDrawable);
            }
            //holder.ll_root.setBackgroundResource(R.drawable.layout_bg_service);
        }



        holder.ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if(data.get(position).getRest_id() != null&&data.get(position).getTable_no()!=null) {

                        checkTableAvaStatusListener.checkTabStatus(data.get(position).getTable_no(),data.get(position).getRest_id());


//                        Intent intent = new Intent(context, CatagActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent.putExtra("restid", data.get(position).getRest_id());
//                        context.startActivity(intent);
                    }

            }




        });


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
        public TextView txt_table_number;
        public LinearLayout ll_root;




        public ViewHolderOne(View itemView) {
            super(itemView);
            txt_table_number = itemView.findViewById(R.id.txt_table_number);
            ll_root = itemView.findViewById(R.id.ll_root);



        }




    }

}



