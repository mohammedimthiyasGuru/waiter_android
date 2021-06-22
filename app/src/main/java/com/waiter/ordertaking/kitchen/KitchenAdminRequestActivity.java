package com.waiter.ordertaking.kitchen;

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
import com.waiter.ordertaking.R;
import com.waiter.ordertaking.RestUtils;
import com.waiter.ordertaking.SessionManager.SessionManager;
import com.waiter.ordertaking.activity.DashBoardActivity;
import com.waiter.ordertaking.activity.KitchenTakingActivity;
import com.waiter.ordertaking.adapter.ChefAdminRequestListAdapter;
import com.waiter.ordertaking.api.APIClient;
import com.waiter.ordertaking.api.RestApiInterface;
import com.waiter.ordertaking.request.KitchenAdminRequestListRequest;
import com.waiter.ordertaking.response.KitchenAdminRequestListResponse;
import com.waiter.ordertaking.utils.ConnectionDetector;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KitchenAdminRequestActivity extends AppCompatActivity {


    private String TAG = "KitchenAdminRequestActivity";


    TextView txt_norecord;


    RecyclerView rv_admin_request_list;


    AVLoadingIndicatorView avi_indicator;


    TextView txt_back;
    LottieAnimationView back_icon;







    SessionManager session;
    String type = "",name = "",userid = "";
    private String fromactivity;
    private List<KitchenAdminRequestListResponse.DataBean> kitchenAdminResponseList;


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
              startActivity(new Intent(getApplicationContext(),KitchenAdminNewRequestActivity.class));
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
        Call<KitchenAdminRequestListResponse> call = ApiService.chefAdminRequestListResponse(RestUtils.getContentType(),kitchenAdminRequestListRequest());
        Log.w(TAG,"KitchenAdminRequestListResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<KitchenAdminRequestListResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<KitchenAdminRequestListResponse> call, @NonNull Response<KitchenAdminRequestListResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"KitchenAdminRequestListResponse"+ "--->" + new Gson().toJson(response.body()));


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
                            txt_norecord.setText("No admin requests");

                        }


                    }

                }


            }

            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<KitchenAdminRequestListResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"KitchenAdminRequestListResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private KitchenAdminRequestListRequest kitchenAdminRequestListRequest() {


        /*
         * chef_id : 60a3b19a9bbb7779da13ac7f
         */

        KitchenAdminRequestListRequest kitchenAdminRequestListRequest = new KitchenAdminRequestListRequest();
        kitchenAdminRequestListRequest.setChef_id(userid);
        Log.w(TAG,"KitchenAdminRequestListRequest"+ "--->" + new Gson().toJson(kitchenAdminRequestListRequest));
        return kitchenAdminRequestListRequest;
    }






    @Override
    public void onBackPressed() {

            startActivity(new Intent(KitchenAdminRequestActivity.this, KitchenTakingActivity.class));


    }

    private void setView() {
        rv_admin_request_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv_admin_request_list.setItemAnimator(new DefaultItemAnimator());
        ChefAdminRequestListAdapter chefAdminRequestListAdapter = new ChefAdminRequestListAdapter(getApplicationContext(), kitchenAdminResponseList);
        rv_admin_request_list.setAdapter(chefAdminRequestListAdapter);

    }
}
