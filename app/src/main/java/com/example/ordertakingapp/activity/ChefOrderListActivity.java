package com.example.ordertakingapp.activity;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordertakingapp.R;
import com.example.ordertakingapp.RestUtils;
import com.example.ordertakingapp.SessionManager.SessionManager;
import com.example.ordertakingapp.adapter.ChefOrderListAdapter;
import com.example.ordertakingapp.api.APIClient;
import com.example.ordertakingapp.api.RestApiInterface;
import com.example.ordertakingapp.interfaces.OrderListClickListener;
import com.example.ordertakingapp.request.FetchChefOrderHistoryRequest;
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

public class ChefOrderListActivity extends AppCompatActivity implements OrderListClickListener,View.OnClickListener {

    AVLoadingIndicatorView avi_indicator;
    RecyclerView rv_kitchendashboardlist;
    TextView txt_norecord;
    private String TAG = "ChefOrderListActivity";
    private SessionManager sessionManager;
    private String restid;
    private Dialog alertDialog;
    DrawerLayout drawerLayout;
    ImageView notification;

    String _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_order_list);

        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getProfileDetails();
        restid = user.get(SessionManager.KEY_RESTID);

        _id = user.get(SessionManager.KEY_ID);

        avi_indicator = findViewById(R.id.avi_indicator);
        avi_indicator.setVisibility(View.GONE);
        rv_kitchendashboardlist = findViewById(R.id.rv_kitchendashboardlist);
        txt_norecord = findViewById(R.id.txt_norecord);


        if(restid != null) {
            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                kitchen_dashboard_ResponseCall();
            }
        }

        drawerLayout = findViewById(R.id.drawer_layout);

        notification = findViewById(R.id.img_notification);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NotificationActivity.class));

            }
        });


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

        redirectActivity(this, KitchenTakingActivity.class);

    }
    public void ClickOrderHistory(View view){

//      recreate();

    }

    public void ClickLogout(View view){
        logout(this);

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




    @Override
    public void onClick(View view) {

        Intent i ;
        switch(view.getId()){
//            case R.id.back:
//                startActivity(new Intent(getApplicationContext(), KitchenTakingActivity.class));
//                finish();
//                break;
        }

    }


    private void kitchen_dashboard_ResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
            Call<KitchenDashoboardListResponse> call = apiInterface.chefMyordersResponseCall(RestUtils.getContentType(), fetchChefOrderHistoryRequest());
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
                            txt_norecord.setText("No Orders Found");
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
        ChefOrderListAdapter chefOrderListAdapter = new ChefOrderListAdapter(getApplicationContext(),data,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_kitchendashboardlist.setLayoutManager(linearLayoutManager);
        rv_kitchendashboardlist.setAdapter(chefOrderListAdapter);

    }

    private FetchChefOrderHistoryRequest fetchChefOrderHistoryRequest() {

        /*
         * chef_id : 60a3b19a9bbb7779da13ac7f
         */

        FetchChefOrderHistoryRequest fetchChefOrderHistoryRequest = new FetchChefOrderHistoryRequest();
        fetchChefOrderHistoryRequest.setChef_id(_id);

        Log.w(TAG,"FetchChefOrderHistoryRequest"+ new Gson().toJson(fetchChefOrderHistoryRequest));
        return fetchChefOrderHistoryRequest;
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
        waiterUpdateAcceptRequest.setChef_id(restid);
        waiterUpdateAcceptRequest.setOrder_id(orderid);
        Log.w(TAG,"waiterUpdateAcceptRequest"+ new Gson().toJson(waiterUpdateAcceptRequest));
        return waiterUpdateAcceptRequest;
    }


}
