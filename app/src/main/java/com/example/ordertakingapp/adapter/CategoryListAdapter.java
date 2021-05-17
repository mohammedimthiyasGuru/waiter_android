package com.example.ordertakingapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordertakingapp.ListPojo;
import com.example.ordertakingapp.R;
import com.example.ordertakingapp.interfaces.CatagListListener;
import com.example.ordertakingapp.response.CategoryListResponse;

import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.NewMyHolder> {

    Context context;
    List<CategoryListResponse.DataBean> data;
    private int selected_position = -1;

    CatagListListener catagListListener;

    public CategoryListAdapter(Context context, List<CategoryListResponse.DataBean> data,CatagListListener catagListListener) {
        this.context = context;
        this.data = data;
        this.catagListListener = catagListListener;
    }

    @NonNull
    @Override
    public NewMyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.catag_list1,viewGroup,false);
        return new NewMyHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull NewMyHolder holder, int position) {


        holder.listName1.setText(data.get(position).getCat_name());
        holder.listName1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected_position = position;
                catagListListener.CatagListListener(data.get(position).getCat_id());
                notifyDataSetChanged();

            }
        });

        if (selected_position == position) {
            // do your stuff here like
            //Change selected item background color
            holder.listName1.setBackgroundResource(R.drawable.rounded_corner);

        } else {
            // do your stuff here like
            //Change  unselected item background color
            holder.listName1.setBackgroundResource(R.drawable.rounded_corner_white);

        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class NewMyHolder extends RecyclerView.ViewHolder {
        TextView listName1;

        public NewMyHolder(@NonNull View itemView) {
            super(itemView);
            listName1 = itemView.findViewById(R.id.list_name1);
        }
    }
}
