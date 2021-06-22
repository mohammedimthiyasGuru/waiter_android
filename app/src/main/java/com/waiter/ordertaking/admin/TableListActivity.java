package com.waiter.ordertaking.admin;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.waiter.ordertaking.R;
import com.waiter.ordertaking.RestUtils;
import com.waiter.ordertaking.SessionManager.SessionManager;
import com.waiter.ordertaking.activity.AdminOrderListActivity;
import com.waiter.ordertaking.activity.CatagActivity;
import com.waiter.ordertaking.activity.DashBoardActivity;
import com.waiter.ordertaking.activity.KitchenUserDetails;
import com.waiter.ordertaking.activity.LoginActivity;
import com.waiter.ordertaking.activity.NotificationActivity;
import com.waiter.ordertaking.activity.PaymentDetails;
import com.waiter.ordertaking.activity.SoSAdminActivity;
import com.waiter.ordertaking.activity.WaiterDetails;
import com.waiter.ordertaking.adapter.AdminTableListAdapter;
import com.waiter.ordertaking.api.APIClient;
import com.waiter.ordertaking.api.RestApiInterface;
import com.waiter.ordertaking.interfaces.CheckTableAvaStatusListener;
import com.waiter.ordertaking.request.AdminTableListRequest;
import com.waiter.ordertaking.request.TableAvaStatusRequest;
import com.waiter.ordertaking.request.TableListRequest;
import com.waiter.ordertaking.response.AdminTableListResponse;
import com.waiter.ordertaking.response.TableAvaStatusResponse;
import com.waiter.ordertaking.response.TableListResponse;
import com.waiter.ordertaking.utils.ConnectionDetector;

import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TableListActivity extends AppCompatActivity implements CheckTableAvaStatusListener {

    ImageView img_refresh;

    DrawerLayout drawerLayout;
    AVLoadingIndicatorView avi_indicator;
    private Dialog alertDialog;
    SessionManager sessionManager;
    private String restid;
    private String TAG = "TableListActivity";

    RecyclerView rv_tablelist;
    TextView txt_norecord;
    TextView title;

    String tableno;

    String restrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablelist);


        //Objects.requireNonNull(getSupportActionBar()).hide();

        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getProfileDetails();
        restid = user.get(SessionManager.KEY_RESTID);
        drawerLayout = findViewById(R.id.drawer_layout);
        avi_indicator = findViewById(R.id.avi_indicator);
        avi_indicator.setVisibility(View.GONE);
        rv_tablelist = findViewById(R.id.rv_tablelist);
        txt_norecord = findViewById(R.id.txt_norecord);
        title = findViewById(R.id.title);

        title.setText("Table List");

        img_refresh = findViewById(R.id.img_refresh);

        img_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(restid != null) {
                    if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                        table_listResponseCall();
                    }
                }

            }
        });

        if(restid != null) {
            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                table_listResponseCall();
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

    public void ClickDashBoardDetails(View view){

        dashboardDetails(this);
    }

    private void dashboardDetails(TableListActivity dashBoardActivity) {
        redirectActivity(this,DashBoardActivity.class);
    }
    public void ClickWaiterDetails(View view){
        waiterDetails(this);
    }

    public void ClickNotifications(View view){
        notificationDetails(view);
    }

    private void notificationDetails(View view) {
        redirectActivity(this, NotificationActivity.class);
    }

    private void waiterDetails(TableListActivity dashBoardActivity) {
        redirectActivity(this, WaiterDetails.class);
    }
    public void ClickKitchenUserDetails(View view){

        kitchenUserDetails(this);
    }

    private void kitchenUserDetails(TableListActivity dashBoardActivity) {
        redirectActivity(this, KitchenUserDetails.class);
    }
    public void ClickOrderDetails(View view){

        orderDetails(this);
    }

    private void orderDetails(TableListActivity dashBoardActivity) {
        redirectActivity(this, AdminOrderListActivity.class);
    }

    public void ClickPaymentDetails(View view){

        paymentDetails(this);
    }

    private void paymentDetails(TableListActivity dashBoardActivity) {
        redirectActivity(this, PaymentDetails.class);
    }



    public void ClickLogout(View view){
        logout(this);

    }

    public void ClickAdminRequest(View view){
        Intent intent = new Intent(getApplicationContext(), AdminRequestActivity.class);
        startActivity(intent);

    }

    public void ClickSoSRequest(View view){
        Intent intent = new Intent(getApplicationContext(), SoSAdminActivity.class);
        startActivity(intent);

    }

    public  void logout(Activity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout ?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SessionManager sessionManager = new SessionManager(getApplicationContext());
                sessionManager.logoutUser();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i)
            {
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



    private void table_listResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<AdminTableListResponse> call = apiInterface.admintablelistResponseCall(RestUtils.getContentType(), adminTableListRequest() );
        Log.w(TAG,"table_listResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<AdminTableListResponse>() {
            @Override
            public void onResponse(@NonNull Call<AdminTableListResponse> call, @NonNull Response<AdminTableListResponse> response) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"AdminTableListResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if ( 200 == response.body().getCode()) {
                        if(response.body().getData() != null && response.body().getData().size()>0){
                            rv_tablelist.setVisibility(View.VISIBLE);
                            txt_norecord.setVisibility(View.GONE);
                            setViewTableList(response.body().getData());
                        }else {
                            rv_tablelist.setVisibility(View.GONE);
                            txt_norecord.setVisibility(View.VISIBLE);
                        }

                    }else{
                        showErrorLoading(response.body().getMessage());
                    }
                }

            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<AdminTableListResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("AdminTableListResponse flr", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void setViewTableList(List<AdminTableListResponse.DataBean> data) {
        rv_tablelist.setLayoutManager(new LinearLayoutManager(this));
        rv_tablelist.setItemAnimator(new DefaultItemAnimator());
        AdminTableListAdapter adminTableListAdapter = new AdminTableListAdapter(getApplicationContext(), data,this);
        rv_tablelist.setAdapter(adminTableListAdapter);

    }
    private AdminTableListRequest adminTableListRequest() {
        AdminTableListRequest adminTableListRequest = new AdminTableListRequest();
        adminTableListRequest.setRest_id(restid);
        Log.w(TAG,"AdminTableListRequest"+ new Gson().toJson(adminTableListRequest));
        return adminTableListRequest;
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
    public void checkTabStatus(String tablenos, String restid) {

        tableno = tablenos;

        restrid = restid;

        if(restrid != null&&tableno!=null) {
            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                checkTabStatusResponseCall();
            }
        }



    }

    private void checkTabStatusResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<TableAvaStatusResponse> call = apiInterface.check_table_status_ResponseCall(RestUtils.getContentType(), tableAvaStatusRequest());
        Log.w(TAG,"checkTabStatusResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<TableAvaStatusResponse>() {
            @Override
            public void onResponse(@NonNull Call<TableAvaStatusResponse> call, @NonNull Response<TableAvaStatusResponse> response) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"TableListResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if ( 200 == response.body().getCode()) {

                          Intent intent = new Intent(TableListActivity.this, CatagActivity.class);
                          intent.putExtra("restid", restrid);
                          intent.putExtra("tableno", tableno);
;                         startActivity(intent);

                    }else{
                        showErrorLoading(response.body().getMessage());
                    }
                }

            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<TableAvaStatusResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("TableAvaStatusResponse flr", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private TableAvaStatusRequest tableAvaStatusRequest() {
        TableAvaStatusRequest tableAvaStatusRequest = new TableAvaStatusRequest();
        tableAvaStatusRequest.setTable_no(tableno);
        tableAvaStatusRequest.setRest_id(restrid);
        Log.w(TAG,"tableAvaStatusRequest"+ new Gson().toJson(tableAvaStatusRequest));
        return tableAvaStatusRequest;
    }
}