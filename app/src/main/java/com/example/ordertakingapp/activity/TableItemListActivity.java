package com.example.ordertakingapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ordertakingapp.R;
import com.example.ordertakingapp.RestUtils;
import com.example.ordertakingapp.SessionManager.SessionManager;
import com.example.ordertakingapp.adapter.KitchenAdapter;
import com.example.ordertakingapp.adapter.TableItemListAdapter;
import com.example.ordertakingapp.api.APIClient;
import com.example.ordertakingapp.api.RestApiInterface;
import com.example.ordertakingapp.request.FetchOrderByIdRequest;
import com.example.ordertakingapp.request.KitchenDashoboardListRequest;
import com.example.ordertakingapp.request.WaiterUpdateAcceptRequest;
import com.example.ordertakingapp.response.KitchenDashoboardListResponse;
import com.example.ordertakingapp.response.OrderDetailsResponse;
import com.example.ordertakingapp.response.SuccessResponse;
import com.example.ordertakingapp.utils.ConnectionDetector;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TableItemListActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG  = "TableItemListActivity";
    Spinner spin_order;

    TextView txt_table_number,txt_tablename,txt_taken,txt_date,txt_status,txt_cost;
    TextView txt_back;



    String id,orderid,restid,chefid;
    AVLoadingIndicatorView avi_indicator;

    RecyclerView rv_table_item_list;
    TextView txt_norecord;
    Button btn_complete;
    private Dialog dialog;

    private SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_item_list);
        Log.w(TAG,"onCreate : ");
    //    Objects.requireNonNull(getSupportActionBar()).hide();

        avi_indicator = findViewById(R.id.avi_indicator);
        avi_indicator.setVisibility(View.GONE);

        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getProfileDetails();

        chefid = user.get(SessionManager.KEY_ID);

        btn_complete = findViewById(R.id.btn_complete);
        rv_table_item_list = findViewById(R.id.rv_table_item_list);
        txt_norecord = findViewById(R.id.txt_norecord);

        txt_table_number = findViewById(R.id.txt_table_number);
        txt_tablename = findViewById(R.id.txt_tablename);
        txt_taken = findViewById(R.id.txt_taken);
        txt_date = findViewById(R.id.txt_date);
        txt_status = findViewById(R.id.txt_status);
        txt_cost = findViewById(R.id.txt_cost);

        txt_back = findViewById(R.id.back);
        txt_back.setOnClickListener(this);

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







    }

    @Override
    public void onClick(View view) {

        Intent i ;
        switch(view.getId()){
            case R.id.back:
                startActivity(new Intent(getApplicationContext(), KitchenTakingActivity.class));
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

                            if(response.body().getData().getOrder_status() != null && response.body().getData().getOrder_status().equalsIgnoreCase("Booked")){
                                btn_complete.setVisibility(View.VISIBLE);
                            }else{
                                btn_complete.setVisibility(View.GONE);
                            }

                            txt_table_number.setText(response.body().getData().getTable_no());
                            txt_tablename.setText(response.body().getData().getOrder_id());
                            txt_taken.setText(response.body().getData().getTaken_by());
                            txt_date.setText(response.body().getData().getOrder_date_book());
                            txt_status.setText(response.body().getData().getOrder_status());
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

    private void waiterCompleteAcceptResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<SuccessResponse> call = apiInterface.waiterCompleteAcceptResponseCall(RestUtils.getContentType(), waiterUpdateAcceptRequest());
        Log.w(TAG,"waiterUpdateAcceptResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @Override
            public void onResponse(@NonNull Call<SuccessResponse> call, @NonNull Response<SuccessResponse> response) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"waiterUpdateAcceptResponseCall" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if ( 200 == response.body().getCode()) {
                       startActivity(new Intent(getApplicationContext(),KitchenTakingActivity.class));
                       finish();


                    }
                }

            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<SuccessResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("waiterUpdateAcceptResponseCall flr", "--->" + t.getMessage());
            }
        });

    }
    private WaiterUpdateAcceptRequest waiterUpdateAcceptRequest() {
        /*
         * order_id : 1621243271346
         * chef_id : 609900e577ada17c96829762
         */
        WaiterUpdateAcceptRequest waiterUpdateAcceptRequest = new WaiterUpdateAcceptRequest();
        waiterUpdateAcceptRequest.setChef_id(chefid);
        waiterUpdateAcceptRequest.setOrder_id(orderid);
        Log.w(TAG,"waiterUpdateAcceptRequest"+ new Gson().toJson(waiterUpdateAcceptRequest));
        return waiterUpdateAcceptRequest;
    }

    private void showCompleteStatusAlert() {

        try {
            dialog = new Dialog(TableItemListActivity.this);
            dialog.setContentView(R.layout.alert_approve_reject_layout);
            TextView tvheader = dialog.findViewById(R.id.tvInternetNotConnected);
            tvheader.setText(R.string.completemsg);
            Button dialogButtonApprove = dialog.findViewById(R.id.btnApprove);
            dialogButtonApprove.setText("Yes");
            Button dialogButtonRejected = dialog.findViewById(R.id.btnReject);
            dialogButtonRejected.setText("No");

            dialogButtonApprove.setOnClickListener(view -> {
                dialog.dismiss();

                waiterCompleteAcceptResponseCall();


            });
            dialogButtonRejected.setOnClickListener(view -> dialog.dismiss());
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }



}