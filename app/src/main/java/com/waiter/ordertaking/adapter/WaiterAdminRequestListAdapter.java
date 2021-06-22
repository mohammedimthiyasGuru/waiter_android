package com.waiter.ordertaking.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.waiter.ordertaking.R;
import com.waiter.ordertaking.response.WaiterAdminRequestListResponse;

import java.util.List;


public class WaiterAdminRequestListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "WaiterAdminRequestListAdapter";
    private Context context;

    WaiterAdminRequestListResponse.DataBean currentItem;
    private List<WaiterAdminRequestListResponse.DataBean> kitchenAdminResponseList;



    private int currentSelectedPosition = RecyclerView.NO_POSITION;



    public WaiterAdminRequestListAdapter(Context context, List<WaiterAdminRequestListResponse.DataBean> kitchenAdminResponseList) {
        this.context = context;
        this.kitchenAdminResponseList = kitchenAdminResponseList;

       

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_kitchen_admin_request_cardview, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

  private void initLayoutOne(ViewHolderOne holder, final int position) {
        currentItem = kitchenAdminResponseList.get(position);
        if(currentItem.getTitle() != null) {
            holder.txt_request_title.setText(currentItem.getTitle());
        }
        if(currentItem.getRequest_text() != null){
            holder.txt_request_text.setText(currentItem.getRequest_text());

        }
        if(currentItem.getRequest_date()!= null) {
            holder.txt_request_date.setText(currentItem.getRequest_date());
        }
        if(currentItem.getResponse_text() != null && !currentItem.getResponse_text().isEmpty()) {
            holder.txt_response_text.setVisibility(View.VISIBLE);
            holder.txt_response_text.setText(currentItem.getResponse_text());
        }else{
            holder.txt_response_text.setVisibility(View.GONE);
        }
        if(currentItem.getResponse_date() != null && !currentItem.getResponse_date().isEmpty()) {
            holder.txt_response_date.setText(currentItem.getResponse_date());
            holder.txt_response_date.setVisibility(View.VISIBLE);
        }else{
            holder.txt_response_date.setVisibility(View.GONE);
        }

      if(currentItem.getResponse_text() != null && !currentItem.getResponse_text().isEmpty()) {
          holder.img_request_mark_status.setBackgroundResource(R.drawable.green_dot);
      }else{
          holder.img_request_mark_status.setBackgroundResource(R.drawable.red_dot);
      }


      holder.ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentSelectedPosition = position;
                notifyDataSetChanged();



            }




        });

      if (currentSelectedPosition == position) {
          holder.ll_response_details.setVisibility(View.VISIBLE);
          //holder.txt_message.setVisibility(View.GONE);


      } else {
         // holder.txt_message.setVisibility(View.VISIBLE);
          holder.ll_response_details.setVisibility(View.GONE);
      }

   }

   @Override
    public int getItemCount() {
        
        return kitchenAdminResponseList.size();
    }
   

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_request_title,txt_request_text,txt_request_date;
        public TextView txt_response_text,txt_response_date;
        public LinearLayout ll_root,ll_response_details;
        public ImageView img_request_mark_status;



        public ViewHolderOne(View itemView) {
            super(itemView);

            txt_request_title = itemView.findViewById(R.id.txt_request_title);
            txt_request_text = itemView.findViewById(R.id.txt_request_text);
            txt_request_date = itemView.findViewById(R.id.txt_request_date);
            ll_root = itemView.findViewById(R.id.ll_root);
            ll_response_details = itemView.findViewById(R.id.ll_response_details);
            ll_response_details.setVisibility(View.GONE);
            txt_response_text = itemView.findViewById(R.id.txt_response_text);
            txt_response_date = itemView.findViewById(R.id.txt_response_date);
            img_request_mark_status = itemView.findViewById(R.id.img_request_mark_status);
        }

    }

}
