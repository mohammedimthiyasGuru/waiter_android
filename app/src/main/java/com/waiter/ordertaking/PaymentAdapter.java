package com.waiter.ordertaking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.PaymentDetailHolder>  {

    Context context;
    List<Pojo_PaymentDetails> pojo_paymentDetailsList;

    public PaymentAdapter(Context context, List<Pojo_PaymentDetails> pojo_paymentDetailsList) {
        this.context = context;
        this.pojo_paymentDetailsList = pojo_paymentDetailsList;
    }

    @NonNull
    @Override
    public PaymentDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.transaction_list_card,parent,false);
        return new PaymentDetailHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentDetailHolder holder, int position) {
        holder.pro_img.setImageResource(pojo_paymentDetailsList.get(position).getProImg());
        holder.txt_name.setText(pojo_paymentDetailsList.get(position).getTransactionName());
        holder.txt_content.setText(pojo_paymentDetailsList.get(position).getTransactionContent());
        holder.txt_amount.setText(pojo_paymentDetailsList.get(position).getAmount());
        holder.txt_date.setText(pojo_paymentDetailsList.get(position).getDate());
        holder.txt_status.setText(pojo_paymentDetailsList.get(position).getStatus());

    }

    @Override
    public int getItemCount() {
        return pojo_paymentDetailsList.size();
    }

    public static class PaymentDetailHolder extends RecyclerView.ViewHolder {
        ImageView pro_img;
        TextView txt_name,txt_content,txt_amount,txt_date,txt_status;
        public PaymentDetailHolder(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.txt_transactionName);
            txt_content = itemView.findViewById(R.id.txt_transactionContent);
            txt_amount = itemView.findViewById(R.id.txt_amount);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_status = itemView.findViewById(R.id.txt_status);
            pro_img = itemView.findViewById(R.id.profile_image);
        }
    }
}
