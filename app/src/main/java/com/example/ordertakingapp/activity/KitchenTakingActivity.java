package com.example.ordertakingapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ordertakingapp.R;
import com.example.ordertakingapp.RestUtils;
import com.example.ordertakingapp.SessionManager.SessionManager;
import com.example.ordertakingapp.adapter.KitchenAdapter;
import com.example.ordertakingapp.api.APIClient;
import com.example.ordertakingapp.api.RestApiInterface;
import com.example.ordertakingapp.interfaces.OrderListClickListener;
import com.example.ordertakingapp.kitchen.KitchenAdminRequestActivity;
import com.example.ordertakingapp.kitchen.KitchenNotificationActivity;
import com.example.ordertakingapp.request.KitchenDashoboardListRequest;
import com.example.ordertakingapp.request.WaiterUpdateAcceptRequest;
import com.example.ordertakingapp.response.KitchenDashoboardListResponse;
import com.example.ordertakingapp.response.SuccessResponse;
import com.example.ordertakingapp.utils.ConnectionDetector;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KitchenTakingActivity extends AppCompatActivity implements OrderListClickListener {

    DrawerLayout drawerLayout;



    ImageView img_num1;
    ImageView notification;

    AVLoadingIndicatorView avi_indicator;
    RecyclerView rv_kitchendashboardlist;
    TextView txt_norecord;
    private String TAG = "KitchenTakingActivity";
    private SessionManager sessionManager;
    private String restid,chefid;
    private Dialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_taking);

        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getProfileDetails();
        restid = user.get(SessionManager.KEY_RESTID);

        chefid = user.get(SessionManager.KEY_ID);

      //  Objects.requireNonNull(getSupportActionBar()).hide();
        drawerLayout = findViewById(R.id.drawer_layout);
        img_num1 = findViewById(R.id.img_no1);

        avi_indicator = findViewById(R.id.avi_indicator);
        avi_indicator.setVisibility(View.GONE);
        rv_kitchendashboardlist = findViewById(R.id.rv_kitchendashboardlist);
        txt_norecord = findViewById(R.id.txt_norecord);



        notification = findViewById(R.id.img_notification);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), KitchenNotificationActivity.class));

            }
        });

        if(restid != null) {
            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                kitchen_dashboard_ResponseCall();
            }
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






    private void kitchen_dashboard_ResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<KitchenDashoboardListResponse> call = apiInterface.kitchen_dashboard_ResponseCall(RestUtils.getContentType(), kitchenDashoboardListRequest());
        Log.w(TAG,"KitchenDashoboardListResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<KitchenDashoboardListResponse>() {
            @Override
            public void onResponse(@NonNull Call<KitchenDashoboardListResponse> call, @NonNull Response<KitchenDashoboardListResponse> response) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"KitchenDashoboardListResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if ( 200 == response.body().getCode()) {

                        if(response.body().getData() != null && response.body().getData().size()>0){
                            rv_kitchendashboardlist.setVisibility(View.VISIBLE);
                            txt_norecord.setVisibility(View.GONE);
                            setViewKitcenList(response.body().getData());
                        }else {
                            rv_kitchendashboardlist.setVisibility(View.GONE);
                            txt_norecord.setVisibility(View.VISIBLE);
                        }


                    }else{
                        showErrorLoading(response.body().getMessage());
                    }
                }

            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<KitchenDashoboardListResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("KitchenDashoboardListResponse flr", "--->" + t.getMessage());
            }
        });

    }
    private void setViewKitcenList(List<KitchenDashoboardListResponse.DataBean> data) {
        KitchenAdapter kitchenAdapter = new KitchenAdapter(getApplicationContext(),data,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_kitchendashboardlist.setLayoutManager(linearLayoutManager);
        rv_kitchendashboardlist.setAdapter(kitchenAdapter);

    }
    private KitchenDashoboardListRequest kitchenDashoboardListRequest() {
        KitchenDashoboardListRequest kitchenDashoboardListRequest = new KitchenDashoboardListRequest();
        kitchenDashoboardListRequest.setRest_id(restid);
        Log.w(TAG,"kitchenDashoboardListRequest"+ new Gson().toJson(kitchenDashoboardListRequest));
        return kitchenDashoboardListRequest;
    }
    public void showErrorLoading(String errormesage){
        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(errormesage);
        alertDialogBuilder.setPositiveButton("ok",
                (arg0, arg1) -> hideLoading());


        androidx.appcompat.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    public void hideLoading(){
        try {
            alertDialog.dismiss();
        }catch (Exception ignored){

        }
    }

    @Override
    public void orderListClickListener(String orderid) {
        if(orderid != null){
            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                waiterUpdateAcceptResponseCall(orderid);
            }
        }
    }

    private void waiterUpdateAcceptResponseCall(String orderid) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<SuccessResponse> call = apiInterface.waiterUpdateAcceptResponseCall(RestUtils.getContentType(), waiterUpdateAcceptRequest(orderid));
        Log.w(TAG,"waiterUpdateAcceptResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @Override
            public void onResponse(@NonNull Call<SuccessResponse> call, @NonNull Response<SuccessResponse> response) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"waiterUpdateAcceptResponseCall" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if ( 200 == response.body().getCode()) {
                        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                            kitchen_dashboard_ResponseCall();
                        }


                    }else{
                        showErrorLoading(response.body().getMessage());
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
    private WaiterUpdateAcceptRequest waiterUpdateAcceptRequest(String orderid) {
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


}
