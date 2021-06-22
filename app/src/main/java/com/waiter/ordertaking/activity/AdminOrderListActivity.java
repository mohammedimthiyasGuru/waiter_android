package com.waiter.ordertaking.activity;

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

import com.waiter.ordertaking.R;
import com.waiter.ordertaking.RestUtils;
import com.waiter.ordertaking.SessionManager.SessionManager;
import com.waiter.ordertaking.adapter.AdminOrderListAdapter;
import com.waiter.ordertaking.admin.AdminRequestActivity;
import com.waiter.ordertaking.admin.TableListActivity;
import com.waiter.ordertaking.api.APIClient;
import com.waiter.ordertaking.api.RestApiInterface;
import com.waiter.ordertaking.interfaces.OrderListClickListener;
import com.waiter.ordertaking.request.KitchenDashoboardListRequest;
import com.waiter.ordertaking.request.WaiterUpdateAcceptRequest;
import com.waiter.ordertaking.response.KitchenDashoboardListResponse;
import com.waiter.ordertaking.response.SuccessResponse;
import com.waiter.ordertaking.utils.ConnectionDetector;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminOrderListActivity extends AppCompatActivity implements OrderListClickListener,View.OnClickListener {

    AVLoadingIndicatorView avi_indicator;
    RecyclerView rv_kitchendashboardlist;
    TextView txt_norecord;
    private String TAG = "AdminOrderListActivity";
    private SessionManager sessionManager;
    private String restid;
    private Dialog alertDialog;
    DrawerLayout drawerLayout;
    ImageView img_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order_list);

        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getProfileDetails();
        restid = user.get(SessionManager.KEY_RESTID);

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

        img_refresh = findViewById(R.id.img_refresh);
        img_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(restid != null) {
                    if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                        kitchen_dashboard_ResponseCall();
                    }
                }
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
        recreate();
    }
    public void ClickWaiterDetails(View view){

        waiterDetails(this);
    }

    public void ClickAdminRequest(View view){
        redirectActivity(this, AdminRequestActivity.class);
    }

  //  public void ClickPaymentDetails(View view){
//        redirectActivity(this,PaymentDetails.class);
//    }

    public void ClickTablelist(View view){
        redirectActivity(this, TableListActivity.class);
    }

    public void ClickSoSRequest(View view) {
        redirectActivity(this, SoSAdminActivity.class);
    }

    public void ClickNotifications(View view) {
        redirectActivity(this, NotificationActivity.class);
    }
    public void ClickPaymentDetails(View view) {
        redirectActivity(this, PaymentDetails.class);
    }
    private void waiterDetails(AdminOrderListActivity orderDetails) {
        redirectActivity(this,WaiterDetails.class);
    }
    public void ClickKitchenUserDetails(View view){

        kitchenUserDetails(this);
    }

    private void kitchenUserDetails(AdminOrderListActivity orderDetails) {
        redirectActivity(this,KitchenUserDetails.class);
    }
    public void ClickOrderDetails(View view){

        orderDetails(this);
    }

    private void orderDetails(AdminOrderListActivity orderDetails) {
        redirectActivity(this,AdminOrderListActivity.class);
    }

    public void ClickDashBoardDetails(View view){

        DashboardDetails(this);
    }

    private void DashboardDetails(AdminOrderListActivity orderDetails) {
        redirectActivity(this,DashBoardActivity.class);
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
            case R.id.back:
                startActivity(new Intent(getApplicationContext(), WaiterActivity.class));
                finish();
                break;
        }

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
                            txt_norecord.setText("No orders found");
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
        AdminOrderListAdapter adminOrderListAdapter = new AdminOrderListAdapter(getApplicationContext(),data,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_kitchendashboardlist.setLayoutManager(linearLayoutManager);
        rv_kitchendashboardlist.setAdapter(adminOrderListAdapter);

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
        waiterUpdateAcceptRequest.setChef_id(restid);
        waiterUpdateAcceptRequest.setOrder_id(orderid);
        Log.w(TAG,"waiterUpdateAcceptRequest"+ new Gson().toJson(waiterUpdateAcceptRequest));
        return waiterUpdateAcceptRequest;
    }


}
