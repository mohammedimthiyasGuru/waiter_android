package com.waiter.ordertaking.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.waiter.ordertaking.R;
import com.waiter.ordertaking.RestUtils;
import com.waiter.ordertaking.adapter.TableItemListAdapter;
import com.waiter.ordertaking.api.APIClient;
import com.waiter.ordertaking.api.RestApiInterface;
import com.waiter.ordertaking.request.FetchOrderByIdRequest;
import com.waiter.ordertaking.request.WaiterUpdateOrderConfirmtRequest;
import com.waiter.ordertaking.response.OrderDetailsResponse;
import com.waiter.ordertaking.response.WaiterUpdateOrderConfirmtResponse;
import com.waiter.ordertaking.utils.ConnectionDetector;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WaiterOrderDetailListActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG  = "WaiterOrderDetailListActivity";
    Spinner spin_order;

    TextView txt_table_number,txt_tablename,txt_taken,txt_date,txt_status,txt_cost;
    TextView txt_back;



    String id,orderid,restid;
    AVLoadingIndicatorView avi_indicator;

    RecyclerView rv_table_item_list;
    TextView txt_norecord;
    Button btn_complete;
    private Dialog dialog;

    LottieAnimationView lottieAnimationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter_order_detail_list);
        Log.w(TAG,"onCreate : ");
    //    Objects.requireNonNull(getSupportActionBar()).hide();

        avi_indicator = findViewById(R.id.avi_indicator);
        avi_indicator.setVisibility(View.GONE);

        btn_complete = findViewById(R.id.btn_complete);
        rv_table_item_list = findViewById(R.id.rv_table_item_list);
        txt_norecord = findViewById(R.id.txt_norecord);

        txt_table_number = findViewById(R.id.txt_table_number);
        txt_tablename = findViewById(R.id.txt_tablename);
        txt_taken = findViewById(R.id.txt_taken);
        txt_date = findViewById(R.id.txt_date);
        txt_status = findViewById(R.id.txt_status);
        txt_cost = findViewById(R.id.txt_cost);

        lottieAnimationView = findViewById(R.id.back_icon);
        lottieAnimationView.setOnClickListener(this);

        btn_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCompleteStatusAlert();
            }
        });







        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            id = extras.getString("id");
            orderid = extras.getString("orderid");
            restid = extras.getString("restid");


        }

        if(orderid != null) {
            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                fetch_order_by_id_ResponseCall();
            }
        }

        ImageView img_refresh = findViewById(R.id.img_refresh);
        img_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(orderid != null) {
                    if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                        fetch_order_by_id_ResponseCall();
                    }
                }
            }
        });







    }

    @Override
    public void onClick(View view) {

        Intent i ;
        switch(view.getId()){
            case R.id.back_icon:
                startActivity(new Intent(getApplicationContext(), WaiterOrderListActivity.class));
                finish();
                break;
        }

    }

    private void fetch_order_by_id_ResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<OrderDetailsResponse> call = apiInterface.fetch_order_by_id_ResponseCall(RestUtils.getContentType(), fetchOrderByIdRequest());
        Log.w(TAG,"fetch_order_by_id_ResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<OrderDetailsResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<OrderDetailsResponse> call, @NonNull Response<OrderDetailsResponse> response) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"KitchenDashoboardListResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if ( 200 == response.body().getCode()) {

                        if(response.body().getData() != null){

                            if(response.body().getData().getWaiter_status() != null && response.body().getData().getWaiter_status().equals("Booked")){
                                btn_complete.setVisibility(View.VISIBLE);
                            }else{
                                btn_complete.setVisibility(View.GONE);
                            }

                            txt_table_number.setText(response.body().getData().getTable_no());
                            txt_tablename.setText(response.body().getData().getOrder_id());
                            txt_taken.setText(response.body().getData().getTaken_by());
                            txt_date.setText(response.body().getData().getOrder_date_book());

                            if(response.body().getData().getWaiter_status()!=null&&!response.body().getData().getWaiter_status().equals(""))

                            {
                                txt_status.setText(response.body().getData().getWaiter_status());

                            }

                            else {

                                txt_status.setText("");
                            }

                            txt_cost.setText("\u20B9 "+response.body().getData().getOrder_cast());

                            if(response.body().getData().getItem_detail() != null && response.body().getData().getItem_detail().size()>0){
                                rv_table_item_list.setVisibility(View.VISIBLE);
                                txt_norecord.setVisibility(View.GONE);
                                setViewTableItemList(response.body().getData().getItem_detail());
                            }
                            else {
                                rv_table_item_list.setVisibility(View.GONE);
                                txt_norecord.setVisibility(View.VISIBLE);
                            }

                        }



                    }
                }

            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<OrderDetailsResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("OrderDetailsResponse flr", "--->" + t.getMessage());
            }
        });

    }

    private void setViewTableItemList(List<OrderDetailsResponse.DataBean.ItemDetailBean> item_detail) {
        TableItemListAdapter kitchenAdapter = new TableItemListAdapter(getApplicationContext(),item_detail);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_table_item_list.setLayoutManager(linearLayoutManager);
        rv_table_item_list.setAdapter(kitchenAdapter);
    }

    private FetchOrderByIdRequest fetchOrderByIdRequest() {
        FetchOrderByIdRequest fetchOrderByIdRequest = new FetchOrderByIdRequest();
        fetchOrderByIdRequest.setOrder_id(orderid);
        Log.w(TAG,"fetchOrderByIdRequest"+ new Gson().toJson(fetchOrderByIdRequest));
        return fetchOrderByIdRequest;
    }

    private void waiterCompleteOrderResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<WaiterUpdateOrderConfirmtResponse> call = apiInterface.waiterCompleteOrderResponseCall(RestUtils.getContentType(), waiterUpdateOrderConfirmtRequest());
        Log.w(TAG,"WaiterUpdateOrderConfirmtResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<WaiterUpdateOrderConfirmtResponse>() {
            @Override
            public void onResponse(@NonNull Call<WaiterUpdateOrderConfirmtResponse> call, @NonNull Response<WaiterUpdateOrderConfirmtResponse> response) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"WaiterUpdateOrderConfirmtResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if ( 200 == response.body().getCode()) {
                       startActivity(new Intent(getApplicationContext(),WaiterOrderListActivity.class));
                       finish();


                    }
                }

            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<WaiterUpdateOrderConfirmtResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("WaiterUpdateOrderConfirmtResponse flr", "--->" + t.getMessage());
            }
        });

    }

    private WaiterUpdateOrderConfirmtRequest waiterUpdateOrderConfirmtRequest() {

        /*
         * order_id : 1621487305975
         * status : Completed
         */


        WaiterUpdateOrderConfirmtRequest waiterUpdateOrderConfirmtRequest = new WaiterUpdateOrderConfirmtRequest();
        waiterUpdateOrderConfirmtRequest.setOrder_id(orderid);
        waiterUpdateOrderConfirmtRequest.setStatus("Completed");
        Log.w(TAG,"waiterUpdateAcceptRequest"+ new Gson().toJson(waiterUpdateOrderConfirmtRequest));
        return waiterUpdateOrderConfirmtRequest;
    }

    private void showCompleteStatusAlert() {

        try {
            dialog = new Dialog(WaiterOrderDetailListActivity.this);
            dialog.setContentView(R.layout.alert_approve_reject_layout);
            TextView tvheader = dialog.findViewById(R.id.tvInternetNotConnected);
            tvheader.setText(R.string.completemsg);
            Button dialogButtonApprove = dialog.findViewById(R.id.btnApprove);
            dialogButtonApprove.setText("Yes");
            Button dialogButtonRejected = dialog.findViewById(R.id.btnReject);
            dialogButtonRejected.setText("No");

            dialogButtonApprove.setOnClickListener(view -> {
                dialog.dismiss();

                waiterCompleteOrderResponseCall();


            });
            dialogButtonRejected.setOnClickListener(view -> dialog.dismiss());
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }



}