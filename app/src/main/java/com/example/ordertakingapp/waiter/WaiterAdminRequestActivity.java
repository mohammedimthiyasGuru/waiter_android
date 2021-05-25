package com.example.ordertakingapp.waiter;

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
import com.example.ordertakingapp.adapter.WaiterAdminRequestListAdapter;
import com.example.ordertakingapp.api.APIClient;
import com.example.ordertakingapp.api.RestApiInterface;
import com.example.ordertakingapp.request.WaiterAdminRequestListRequest;
import com.example.ordertakingapp.response.WaiterAdminRequestListResponse;
import com.example.ordertakingapp.utils.ConnectionDetector;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WaiterAdminRequestActivity extends AppCompatActivity {


    private String TAG = "WaiterAdminRequestActivity";


    TextView txt_norecord;


    RecyclerView rv_admin_request_list;


    AVLoadingIndicatorView avi_indicator;


    TextView txt_back;
    LottieAnimationView back_icon;







    SessionManager session;
    String type = "",name = "",userid = "";
    private String fromactivity;
    private List<WaiterAdminRequestListResponse.DataBean> kitchenAdminResponseList;


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
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(new Intent(getApplicationContext(), WaiterAdminNewRequestActivity.class));
              finish();
            }
        });



        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getProfileDetails();
        type = user.get(SessionManager.KEY_TYPE);
        userid = user.get(SessionManager.KEY_ID);


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
        Call<WaiterAdminRequestListResponse> call = ApiService.kitchenAdminRequestListResponse(RestUtils.getContentType(),kitchenAdminRequestListRequest());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<WaiterAdminRequestListResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<WaiterAdminRequestListResponse> call, @NonNull Response<WaiterAdminRequestListResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"NotificationGetlistResponse"+ "--->" + new Gson().toJson(response.body()));


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
            public void onFailure(@NonNull Call<WaiterAdminRequestListResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"WaiterAdminRequestListResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private WaiterAdminRequestListRequest kitchenAdminRequestListRequest() {
        /*
         * user_id : 5ee3666a5dfb34019b13c3a2
         */
        WaiterAdminRequestListRequest waiterAdminRequestListRequest = new WaiterAdminRequestListRequest();
        waiterAdminRequestListRequest.setWaiter_id(userid);
        Log.w(TAG,"waiterAdminRequestListRequest"+ "--->" + new Gson().toJson(waiterAdminRequestListRequest));
        return waiterAdminRequestListRequest;
    }






    @Override
    public void onBackPressed() {
        super.onBackPressed();

            finish();


    }

    private void setView() {
        rv_admin_request_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv_admin_request_list.setItemAnimator(new DefaultItemAnimator());
        WaiterAdminRequestListAdapter waiterAdminRequestListAdapter = new WaiterAdminRequestListAdapter(getApplicationContext(), kitchenAdminResponseList);
        rv_admin_request_list.setAdapter(waiterAdminRequestListAdapter);

    }
}
