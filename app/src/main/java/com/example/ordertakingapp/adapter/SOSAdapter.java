package com.example.ordertakingapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordertakingapp.R;
import com.example.ordertakingapp.interfaces.SoSCallListener;
import com.example.ordertakingapp.response.SoSResponse;

import java.util.List;


public class SOSAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "SOSAdapter";

    private Context context;

    private List<SoSResponse.DataBean> sosList;





    SoSResponse.DataBean currentItem;
    private int row_index;
    private SoSCallListener soSCallListener;

    public SOSAdapter(Context context, List<SoSResponse.DataBean> sosList,SoSCallListener soSCallListener) {
        this.context = context;
        this.sosList = sosList;
        this.soSCallListener = soSCallListener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_sos_no_list, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);

    }

    @SuppressLint("SetTextI18n")
    private void initLayoutOne(ViewHolderOne holder, final int position) {
          currentItem = sosList.get(position);
          holder.txt_phn_num.setText(sosList.get(position).getNumber()+"");

          holder.txt_contact.setText(sosList.get(position).getName());


        holder.ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index=position;
                notifyDataSetChanged();
                }




        });




        if(row_index == position){
            Log.w(TAG,"phonenumber : "+sosList.get(position).getNumber());
            soSCallListener.soSCallListener(sosList.get(position).getNumber());
            holder.ll_root.setBackgroundResource(R.drawable.rounded_sos_bgm);
            holder.txt_phn_num.setTextColor(Color.parseColor("#ffffff"));
            holder.txt_contact.setTextColor(Color.parseColor("#ffffff"));//for both textviews
        }
        else {
            holder.ll_root.setBackgroundResource(R.drawable.user_bgm_trans);
            holder.txt_phn_num.setTextColor(Color.parseColor("#555555"));
            holder.txt_contact.setTextColor(Color.parseColor("#555555"));//for both textviews
        }





    }












    @Override
    public int getItemCount() {
        return sosList.size();



    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_phn_num,txt_contact;
        public LinearLayout ll_root;




        public ViewHolderOne(View itemView) {
            super(itemView);

            txt_phn_num = itemView.findViewById(R.id.txt_phn_num);
            txt_contact = itemView.findViewById(R.id.txt_contact);
            ll_root = itemView.findViewById(R.id.ll_root);




        }




    }










}
