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

import com.example.ordertakingapp.Pojo_KitchenUserDetail;
import com.example.ordertakingapp.R;
import com.example.ordertakingapp.activity.KitchenUserDetailView;
import com.example.ordertakingapp.activity.KitchenUserDetails;
import com.example.ordertakingapp.activity.WaiterDetailsView;

import java.util.List;

public class KitchenUserDetailAdapter extends  RecyclerView.Adapter<KitchenUserDetailAdapter.KitchenDetailHolder>{
    Context context;
    List<Pojo_KitchenUserDetail> kitchenUserDetail;

    public KitchenUserDetailAdapter(Context context, List<Pojo_KitchenUserDetail> kitchenUserDetail) {
        this.context = context;
        this.kitchenUserDetail = kitchenUserDetail;
    }

    @NonNull
    @Override
    public KitchenDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.kitcher_userdetail_card,parent,false);
        return new KitchenDetailHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KitchenDetailHolder holder, int position) {
        // holder.proImg.setImageResource(waiterDetailsList.get(position).getProImg());
        holder.txt_kitchenrNo.setText(kitchenUserDetail.get(position).getKitchenNo());
        holder.txt_kitchenUserName.setText(kitchenUserDetail.get(position).getKitchenUserName());
        holder.txt_orderCount.setText(kitchenUserDetail.get(position).getOrderCount());
        holder.txt_date.setText(kitchenUserDetail.get(position).getDate());
        holder.txt_time.setText(kitchenUserDetail.get(position).getTime());
        holder.txt_viewMore.setText(kitchenUserDetail.get(position).getViewMore());

        holder.txt_viewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), KitchenUserDetailView.class);
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return kitchenUserDetail.size();
    }

    public static class KitchenDetailHolder extends RecyclerView.ViewHolder {
        TextView txt_kitchenrNo,txt_kitchenUserName,txt_orderCount,txt_date,txt_time,txt_viewMore;
        ImageView proImg;
        public KitchenDetailHolder(@NonNull View itemView) {
            super(itemView);
            txt_kitchenrNo = itemView.findViewById(R.id.txt_kitchenNo);
            txt_kitchenUserName = itemView.findViewById(R.id.txt_kitchenUseName);
            txt_orderCount = itemView.findViewById(R.id.txt_orderCount);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_time = itemView.findViewById(R.id.txt_time);
            txt_viewMore = itemView.findViewById(R.id.txt_viewMore);
            proImg = itemView.findViewById(R.id.profile_image);
        }
    }
}
