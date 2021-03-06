package com.waiter.ordertaking.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
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
import android.widget.Toast;

import com.waiter.ordertaking.R;
import com.waiter.ordertaking.RestUtils;
import com.waiter.ordertaking.SessionManager.SessionManager;
import com.waiter.ordertaking.adapter.TableListAdapter;
import com.waiter.ordertaking.api.APIClient;
import com.waiter.ordertaking.api.RestApiInterface;
import com.waiter.ordertaking.interfaces.CheckTableAvaStatusListener;
import com.waiter.ordertaking.request.TableAvaStatusRequest;
import com.waiter.ordertaking.request.TableListRequest;
import com.waiter.ordertaking.response.TableAvaStatusResponse;
import com.waiter.ordertaking.response.TableListResponse;
import com.waiter.ordertaking.utils.ConnectionDetector;
import com.waiter.ordertaking.waiter.WaiterAdminRequestActivity;
import com.waiter.ordertaking.waiter.WaiterNotificationActivity;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WaiterActivity extends AppCompatActivity implements CheckTableAvaStatusListener {

    ImageView img_refresh;

    DrawerLayout drawerLayout;
    AVLoadingIndicatorView avi_indicator;
    private Dialog alertDialog;
    SessionManager sessionManager;
    private String restid;
    private String TAG = "WaiterActivity";

    RecyclerView rv_tablelist;
    TextView txt_norecord;

    String tableno;

    String restrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter);


        //Objects.requireNonNull(getSupportActionBar()).hide();

        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getProfileDetails();
        restid = user.get(SessionManager.KEY_RESTID);
        drawerLayout = findViewById(R.id.drawer_layout);
        avi_indicator = findViewById(R.id.avi_indicator);
        avi_indicator.setVisibility(View.GONE);
        rv_tablelist = findViewById(R.id.rv_tablelist);
        txt_norecord = findViewById(R.id.txt_norecord);




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

        redirectActivity(this, WaiterActivity.class);
    }

    public void ClickDashBoard(View view){

        redirectActivity(this, DashBoardActivity.class);

    }

    public void ClickOrderHistory(View view){

        redirectActivity(this, WaiterOrderListActivity.class);

    }

    public void ClickAdminRequest(View view){
        Intent intent = new Intent(getApplicationContext(), WaiterAdminRequestActivity.class);
        startActivity(intent);

    }

    public void ClickSoSRequest(View view){
        Intent intent = new Intent(getApplicationContext(), SoSWaiterActivity.class);
        startActivity(intent);

    } public void ClickNotifications(View view){
        Intent intent = new Intent(getApplicationContext(), WaiterNotificationActivity.class);
        startActivity(intent);

    }


    public void ClickLogout(View view){
        logout(this);

    }

    public void logout(Activity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout ?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sessionManager.logoutUser();
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



    private void table_listResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<TableListResponse> call = apiInterface.table_listResponseCall(RestUtils.getContentType(), tableListRequest());
        Log.w(TAG,"table_listResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<TableListResponse>() {
            @Override
            public void onResponse(@NonNull Call<TableListResponse> call, @NonNull Response<TableListResponse> response) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"TableListResponse" + new Gson().toJson(response.body()));
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

            @Override
            public void onFailure(@NonNull Call<TableListResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("TableListResponse flr", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void setViewTableList(List<TableListResponse.DataBean> data) {
        rv_tablelist.setLayoutManager(new GridLayoutManager(this, 3));
        rv_tablelist.setItemAnimator(new DefaultItemAnimator());
        TableListAdapter tableListAdapter = new TableListAdapter(getApplicationContext(), data,this);
        rv_tablelist.setAdapter(tableListAdapter);

    }
    private TableListRequest tableListRequest() {
        TableListRequest tableListRequest = new TableListRequest();
        tableListRequest.setRest_id(restid);
        Log.w(TAG,"tableListRequest"+ new Gson().toJson(tableListRequest));
        return tableListRequest;
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

                          Intent intent = new Intent(WaiterActivity.this, CatagActivity.class);
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