package com.example.ordertakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.ordertakingapp.Pojo_WaiterDetail;
import com.example.ordertakingapp.R;
import com.example.ordertakingapp.activity.WaiterDetailsView;
import com.example.ordertakingapp.response.FetchWaiterListResponse;
import com.google.gson.Gson;

import java.util.List;


public class WaiterDetailAdapter extends  RecyclerView.Adapter<WaiterDetailAdapter.WaiterDetailHolder> {

    private static final String TAG ="WaiterDetail" ;
    Context context;
    List<FetchWaiterListResponse.DataBean> waiterDetailsList;

    public WaiterDetailAdapter(Context context, List<FetchWaiterListResponse.DataBean> waiterDetailsList) {
        this.context = context;
        this.waiterDetailsList = waiterDetailsList;
    }

    @NonNull
    @Override
    public WaiterDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.waiter_detail_card,parent,false);
        return new WaiterDetailHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WaiterDetailHolder holder, int position) {

       Log.w(TAG," list size "+waiterDetailsList.size());
       Log.w(TAG," list "+new Gson().toJson(waiterDetailsList));

       final FetchWaiterListResponse.DataBean dataBean = waiterDetailsList.get(position);
       // holder.proImg.setImageResource(waiterDetailsList.get(position).getProImg());

        if (dataBean.getWaiter_number()!= 0) {

            holder.txt_waiterNo.setText(""+dataBean.getWaiter_number());
        }

        if (dataBean.getWaiter_name()!= null&&!dataBean.getWaiter_name().isEmpty()) {

            holder.txt_waiterName.setText(""+dataBean.getWaiter_name());
        }

        if (dataBean.getWaiter_emailid()!= null&&!dataBean.getWaiter_emailid().isEmpty()) {

            holder.txt_orderCount.setText(""+dataBean.getWaiter_emailid());
        }


        if (dataBean.getWaiter_name()!= null&&!dataBean.getWaiter_name().isEmpty()) {

            holder.txt_waiterName.setText(""+dataBean.getWaiter_name());
        }


        holder.txt_viewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), WaiterDetailsView.class);
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return waiterDetailsList.size();
    }

    public static class  WaiterDetailHolder extends RecyclerView.ViewHolder {
        TextView txt_waiterNo,txt_waiterName,txt_orderCount,txt_date,txt_time,txt_viewMore;
        ImageView proImg;

        public WaiterDetailHolder(@NonNull View itemView) {
            super(itemView);

            txt_waiterNo = itemView.findViewById(R.id.txt_waiterNo);
            txt_waiterName = itemView.findViewById(R.id.txt_WaiterName);
            txt_orderCount = itemView.findViewById(R.id.txt_orderCount);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_time = itemView.findViewById(R.id.txt_time);
            txt_viewMore = itemView.findViewById(R.id.txt_viewMore);
            proImg = itemView.findViewById(R.id.profile_image);

        }
    }
}
