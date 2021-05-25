package com.example.ordertakingapp.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.ordertakingapp.R;
import com.example.ordertakingapp.RestUtils;
import com.example.ordertakingapp.SessionManager.SessionManager;
import com.example.ordertakingapp.adapter.AdminRequestListAdapter;
import com.example.ordertakingapp.adapter.ChefAdminRequestListAdapter;
import com.example.ordertakingapp.api.APIClient;
import com.example.ordertakingapp.api.RestApiInterface;
import com.example.ordertakingapp.kitchen.KitchenAdminNewRequestActivity;
import com.example.ordertakingapp.request.AdminRequestListRequest;
import com.example.ordertakingapp.request.KitchenAdminRequestListRequest;
import com.example.ordertakingapp.response.AdminRequestListResponse;
import com.example.ordertakingapp.utils.ConnectionDetector;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminRequestActivity extends AppCompatActivity {


    private String TAG = "AdminRequestActivity";


    TextView txt_norecord;


    RecyclerView rv_admin_request_list;


    AVLoadingIndicatorView avi_indicator;


    TextView txt_back;
    LottieAnimationView back_icon;







    SessionManager session;
    String type = "",name = "",userid = "",restid="";
    private String fromactivity;
    private List<AdminRequestListResponse.DataBean> kitchenAdminResponseList;


    @SuppressLint("LogNotTimber")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_admin_request);
        Log.w(TAG,"onCreate-->");


        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            fromactivity = extras.getString("fromactivity");
            Log.w(TAG,"Bundle "+" fromactivity : "+fromactivity);



        }

        avi_indicator = findViewById(R.id.avi_indicator);
        rv_admin_request_list = findViewById(R.id.rv_admin_request_list);
        txt_norecord = findViewById(R.id.txt_norecord);

        avi_indicator.setVisibility(View.GONE);

        FloatingActionButton fab_add = findViewById(R.id.fab_add);

        fab_add.setVisibility(View.GONE);


        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getProfileDetails();
        type = user.get(SessionManager.KEY_TYPE);
        userid = user.get(SessionManager.KEY_ID);
        restid = user.get(SessionManager.KEY_RESTID);


        Log.w(TAG,"session--->"+"type :"+type+" "+"name :"+" "+name);
        txt_back = findViewById(R.id.back);
        back_icon = findViewById(R.id.back_icon);


        back_icon.setOnClickListener(v -> onBackPressed());


        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
            notificationGetlistResponseCall();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @SuppressLint("LogNotTimber")
    private void notificationGetlistResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<AdminRequestListResponse> call = ApiService.adminRequestListResponse(RestUtils.getContentType(),adminRequestListRequest());
        Log.w(TAG,"AdminRequestListResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<AdminRequestListResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<AdminRequestListResponse> call, @NonNull Response<AdminRequestListResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"AdminRequestListResponse"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if(response.body().getCode() == 200){

                        if(response.body().getData() != null && response.body().getData().size()>0){
                            kitchenAdminResponseList = response.body().getData();
                            txt_norecord.setVisibility(View.GONE);
                            rv_admin_request_list.setVisibility(View.VISIBLE);
                            setView();
                        }else{
                            rv_admin_request_list.setVisibility(View.GONE);
                            txt_norecord.setVisibility(View.VISIBLE);
                            txt_norecord.setText("No new admin requests");

                        }


                    }

                }


            }

            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<AdminRequestListResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"AdminRequestListResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private AdminRequestListRequest adminRequestListRequest() {


        /*
         * rest_id : 6098ff1b074e747b0fcd04b5
         */

        AdminRequestListRequest adminRequestListRequest = new AdminRequestListRequest();
        adminRequestListRequest.setRest_id(restid);
        Log.w(TAG,"AdminRequestListRequest"+ "--->" + new Gson().toJson(adminRequestListRequest));
        return adminRequestListRequest;
    }






    @Override
    public void onBackPressed() {
        super.onBackPressed();

            finish();


    }

    private void setView() {
        rv_admin_request_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv_admin_request_list.setItemAnimator(new DefaultItemAnimator());
        AdminRequestListAdapter adminRequestListAdapter = new AdminRequestListAdapter(getApplicationContext(), kitchenAdminResponseList);
        rv_admin_request_list.setAdapter(adminRequestListAdapter);

    }
}
