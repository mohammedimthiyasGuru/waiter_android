package com.example.ordertakingapp.kitchen;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordertakingapp.R;
import com.example.ordertakingapp.RestUtils;
import com.example.ordertakingapp.SessionManager.SessionManager;
import com.example.ordertakingapp.activity.ChefOrderListActivity;
import com.example.ordertakingapp.activity.LoginActivity;
import com.example.ordertakingapp.activity.SoSActivity;
import com.example.ordertakingapp.adapter.NotificationAdapter;
import com.example.ordertakingapp.api.APIClient;
import com.example.ordertakingapp.api.RestApiInterface;
import com.example.ordertakingapp.interfaces.NotificationsClickListener;
import com.example.ordertakingapp.request.NotificationListRequest;
import com.example.ordertakingapp.request.NotificationMarkReadRequest;
import com.example.ordertakingapp.response.NotificationListResponse;
import com.example.ordertakingapp.response.SuccessResponse;
import com.example.ordertakingapp.utils.ConnectionDetector;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KitchenNotificationActivity extends AppCompatActivity implements NotificationsClickListener {

    private String TAG  = "KitchenNotificationActivity";

    DrawerLayout drawerLayout;


    RecyclerView notify_recycler;
    NotificationAdapter notificationAdapter;


    AVLoadingIndicatorView avi_indicator;
    TextView txt_norecord;

    String userid;
    private List<NotificationListResponse.DataBean> notificationList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_notification);
        //Objects.requireNonNull(getSupportActionBar()).hide();

        Log.w(TAG,"onCreate :");

        SessionManager session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getProfileDetails();
        String type = user.get(SessionManager.KEY_TYPE);
         userid = user.get(SessionManager.KEY_ID);
        String restid = user.get(SessionManager.KEY_RESTID);

        drawerLayout = findViewById(R.id.drawer_layout);

        avi_indicator = findViewById(R.id.avi_indicator);
        avi_indicator.setVisibility(View.GONE);
        notify_recycler = findViewById(R.id.notify_recycler);
        txt_norecord = findViewById(R.id.txt_norecord);

        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
            notificationListResponseCall();
        }





    }

    public void ClickMenu(View view){

        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {

        drawerLayout.openDrawer(GravityCompat.START);
    }
    public void ClickLogo(View view){

        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome(View view){
        recreate();
    }


    public void ClickOrderHistory(View view){

        redirectActivity(this, ChefOrderListActivity.class);

    }

    public void ClickLogout(View view){
        logout(this);

    }

    public void ClickAdminRequest(View view){
        Intent intent = new Intent(getApplicationContext(), KitchenAdminRequestActivity.class);
        startActivity(intent);

    }

    public void ClickSoSRequest(View view){
        Intent intent = new Intent(getApplicationContext(), SoSActivity.class);
        startActivity(intent);

    }

    public  void logout(Activity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout ?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });
        builder.show();

    }

    public static void redirectActivity(Activity activity, Class aclass) {
        Intent intent = new Intent(activity,aclass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);

    }

    @Override
    protected void onPause() {
        super.onPause();

        closeDrawer(drawerLayout);
    }



    @SuppressLint("LogNotTimber")
    private void notificationListResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<NotificationListResponse> call = ApiService.notificationListResponseCall(RestUtils.getContentType(),notificationListRequest());
        Log.w(TAG,"notificationListResponseCall url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<NotificationListResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<NotificationListResponse> call, @NonNull Response<NotificationListResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"notificationListResponseCall"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if(response.body().getCode() == 200){

                        if(response.body().getData() != null && response.body().getData().size()>0){
                            notificationList = response.body().getData();
                            txt_norecord.setVisibility(View.GONE);
                            notify_recycler.setVisibility(View.VISIBLE);
                            setView();
                        }else{
                            notify_recycler.setVisibility(View.GONE);
                            txt_norecord.setVisibility(View.VISIBLE);
                            txt_norecord.setText("No new admin requests");

                        }


                    }

                }


            }

            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<NotificationListResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"AdminRequestListResponse flr"+"--->" + t.getMessage());
            }
        });

    }

    private void setView() {
        notificationAdapter = new NotificationAdapter(getApplicationContext(),notificationList,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        notify_recycler.setLayoutManager(linearLayoutManager);
        notify_recycler.setAdapter(notificationAdapter);
    }

    @SuppressLint("LogNotTimber")
    private NotificationListRequest notificationListRequest() {


        /*
         * rest_id : 6098ff1b074e747b0fcd04b5
         */

        NotificationListRequest notificationListRequest = new NotificationListRequest();
        notificationListRequest.setUser_id(userid);
        Log.w(TAG,"notificationListRequest"+ "--->" + new Gson().toJson(notificationListRequest));
        return notificationListRequest;
    }


    @Override
    public void notificationsClickListener(String id) {
        if(id != null){
            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                markreadResponseCall(id);
            }
        }
    }

    @SuppressLint("LogNotTimber")
    private void markreadResponseCall(String id) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<SuccessResponse> call = ApiService.markreadResponseCall(RestUtils.getContentType(),notificationMarkReadRequest(id));
        Log.w(TAG,"markreadResponseCall url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<SuccessResponse> call, @NonNull Response<SuccessResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"markreadResponseCall"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                          notificationListResponseCall();
                        }


                    }

                }


            }

            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<SuccessResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"markreadResponseCall flr"+"--->" + t.getMessage());
            }
        });

    }

    @SuppressLint("LogNotTimber")
    private NotificationMarkReadRequest notificationMarkReadRequest(String id) {


        /*
         * _id : 6098ff1b074e747b0fcd04b5
         */

        NotificationMarkReadRequest notificationMarkReadRequest = new NotificationMarkReadRequest();
        notificationMarkReadRequest.set_id(id);
        Log.w(TAG,"notificationMarkReadRequest"+ "--->" + new Gson().toJson(notificationMarkReadRequest));
        return notificationMarkReadRequest;
    }
}