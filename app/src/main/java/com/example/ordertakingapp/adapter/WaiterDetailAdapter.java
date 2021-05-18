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

import com.example.ordertakingapp.R;
import com.example.ordertakingapp.activity.WaiterDetailsView;
import com.example.ordertakingapp.response.FetchWaiterListResponse;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


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


        if (dataBean.getCreatedAt()!= null&&!dataBean.getCreatedAt().isEmpty()) {

            String date = getDate(dataBean.getCreatedAt());

            holder.txt_date.setText(""+ date.substring(0, date.indexOf(' ')));

            holder.txt_time.setText( ""+date.substring(date.indexOf(' ') + 1));

        }


        holder.txt_viewMore.setOnClickListener(v -> {

            Intent intent = new Intent(context, WaiterDetailsView.class);

            intent.putExtra("_id",dataBean.get_id());

            intent.putExtra("waiter_id",dataBean.getWaiter_number());

            intent.putExtra("waiter_name",dataBean.getWaiter_name());

            intent.putExtra("waiter_number",dataBean.getWaiter_emergency_no());

            intent.putExtra("waiter_addr",dataBean.getWaiter_address());

            intent.putExtra("waiter_emailid",dataBean.getWaiter_emailid());

            intent.putExtra("status",dataBean.getWaiter_status());

            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount()
    {
        return waiterDetailsList.size();
    }

    public static class  WaiterDetailHolder extends ViewHolder {
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
