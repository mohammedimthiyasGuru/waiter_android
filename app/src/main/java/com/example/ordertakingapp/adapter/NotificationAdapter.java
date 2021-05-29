package com.example.ordertakingapp.adapter;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ordertakingapp.R;
import com.example.ordertakingapp.interfaces.NotificationsClickListener;
import com.example.ordertakingapp.response.NotificationListResponse;

import java.util.List;


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyHolder> {

    String TAG = "NotificationAdapter";

    Context context;
    private List<NotificationListResponse.DataBean> notificationList;

    private int currentSelectedPosition = RecyclerView.NO_POSITION;

    NotificationsClickListener notificationsClickListener;


    public NotificationAdapter(Context context, List<NotificationListResponse.DataBean> notificationList, NotificationsClickListener notificationsClickListener) {
        this.context = context;
        this.notificationList = notificationList;
        this.notificationsClickListener = notificationsClickListener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.notification_list,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int position) {



        myHolder.name.setText(notificationList.get(position).getNotify_title());
        myHolder.txt_msg.setText(notificationList.get(position).getNotify_descri());
        myHolder.txt_msg_details.setText(notificationList.get(position).getNotify_descri());
        myHolder.time.setText(notificationList.get(position).getNotify_time());

        Log.w(TAG,"NotifyImage : "+notificationList.get(position).getNotify_img());
        if (notificationList.get(position).getNotify_img() != null && !notificationList.get(position).getNotify_img().trim().isEmpty()) {
            Glide.with(context)
                    .load(notificationList.get(position).getNotify_img())
                    .into(myHolder.profile);
        }else {
            Glide.with(context)
                    .load(R.drawable.notification)
                    .into(myHolder.profile);

        }

        if(notificationList.get(position).isRead_status()){
            myHolder.img_notification_mark_status.setBackgroundResource(R.drawable.green_dot);
        }else{
            myHolder.img_notification_mark_status.setBackgroundResource(R.drawable.red_dot);
        }


        myHolder.ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentSelectedPosition = position;
                notifyDataSetChanged();
                if(!notificationList.get(position).isRead_status()){
                 notificationsClickListener.notificationsClickListener(notificationList.get(position).get_id());
                }

            }
        });

        if (currentSelectedPosition == position) {
            myHolder.txt_msg_details.setVisibility(View.VISIBLE);
            myHolder.txt_msg.setVisibility(View.GONE);


        } else {
            myHolder.txt_msg_details.setVisibility(View.GONE);
            myHolder.txt_msg.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView name,txt_msg,txt_msg_details,time;
        ImageView profile,img_notification_mark_status;
        LinearLayout ll_root;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card1);

            name = itemView.findViewById(R.id.txt_notification);
            txt_msg= itemView.findViewById(R.id.txt_msg);
            txt_msg_details= itemView.findViewById(R.id.txt_msg_details);
            time= itemView.findViewById(R.id.txt_time);

            profile= itemView.findViewById(R.id.profile_image);
            img_notification_mark_status= itemView.findViewById(R.id.img_notification_mark_status);
            ll_root = itemView.findViewById(R.id.ll_root);
        }
    }
}