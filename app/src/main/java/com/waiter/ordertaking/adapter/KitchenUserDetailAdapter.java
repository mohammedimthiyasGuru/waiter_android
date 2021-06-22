package com.waiter.ordertaking.adapter;

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

import com.waiter.ordertaking.R;
import com.waiter.ordertaking.activity.KitchenUserDetailView;
import com.waiter.ordertaking.response.FetchChiefListResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class KitchenUserDetailAdapter extends  RecyclerView.Adapter<KitchenUserDetailAdapter.KitchenDetailHolder>{
    Context context;
    List<FetchChiefListResponse.DataBean> kitchenUserDetail;

    public KitchenUserDetailAdapter(Context context,List<FetchChiefListResponse.DataBean> kitchenUserDetail) {
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

        final FetchChiefListResponse.DataBean dataBean = kitchenUserDetail.get(position);

        if (dataBean.getChef_number()!= 0) {

            holder.txt_kitchenrNo.setText(""+dataBean.getChef_number());
        }

        if (dataBean.getChef_name()!= null&&!dataBean.getChef_name().isEmpty()) {

            holder.txt_kitchenUserName.setText(""+dataBean.getChef_name());
        }

        if (dataBean.getChef_emailid()!= null&&!dataBean.getChef_emailid().isEmpty()) {

            holder.txt_orderCount.setText(""+dataBean.getChef_emailid());
        }


        if (dataBean.getCreatedAt()!= null&&!dataBean.getCreatedAt().isEmpty()) {

            String date = getDate(dataBean.getCreatedAt());

            holder.txt_date.setText(""+ date.substring(0, date.indexOf(' ')));

            holder.txt_time.setText( ""+date.substring(date.indexOf(' ') + 1));

        }


        holder.txt_viewMore.setOnClickListener(v -> {

            Intent intent = new Intent(context, KitchenUserDetailView.class);

            intent.putExtra("_id",dataBean.get_id());

            intent.putExtra("chef_id",dataBean.getChef_number());

            intent.putExtra("chef_name",dataBean.getChef_name());

            intent.putExtra("chef_number",dataBean.getChef_emergency_no());

            intent.putExtra("chef_addr",dataBean.getChef_address());

            intent.putExtra("chef_emailid",dataBean.getChef_emailid());

            intent.putExtra("status",dataBean.getChef_status());

            context.startActivity(intent);
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

    private String getDate(String ourDate)
    {
        try
        {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date value = formatter.parse(ourDate);

            SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy HH:mm"); //this format changeable
            dateFormatter.setTimeZone(TimeZone.getDefault());
            ourDate = dateFormatter.format(value);

            Log.w("ourDate", ourDate);
        }
        catch (Exception e)
        {
            Log.w("Exception", ""+e.getMessage());

            ourDate = "00-00-0000 00:00";
        }
        return ourDate;
    }
}
