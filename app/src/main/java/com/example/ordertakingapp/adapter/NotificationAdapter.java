package com.example.ordertakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordertakingapp.Pojo_Notification;
import com.example.ordertakingapp.R;
import com.example.ordertakingapp.activity.NotificationDetail;

import java.util.List;


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyHolder> {


    Context context;
    List<Pojo_Notification> notifiList;

    public NotificationAdapter(Context context, List<Pojo_Notification> notifiList) {
        this.context = context;
        this.notifiList = notifiList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.notification_list,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

        myHolder.name.setText(notifiList.get(i).getNotification_name());
        myHolder.content.setText(notifiList.get(i).getNotification_content());
        myHolder.time.setText(notifiList.get(i).getTime());
        myHolder.star.setImageResource(notifiList.get(i).getStar());
        myHolder.profile.setImageResource(notifiList.get(i).getProfile_img());


        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(),NotificationDetail.class);
                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return notifiList.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView name,content,time;
        ImageView star;
        ImageView profile;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card1);

            name = itemView.findViewById(R.id.txt_notification);
            content= itemView.findViewById(R.id.txt_msg);
            time= itemView.findViewById(R.id.txt_time);
            star = itemView.findViewById(R.id.star);
            profile= itemView.findViewById(R.id.profile_image);
        }
    }
}