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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordertakingapp.Pojo_WaiterDetail;
import com.example.ordertakingapp.R;
import com.example.ordertakingapp.RestUtils;
import com.example.ordertakingapp.SessionManager.SessionManager;
import com.example.ordertakingapp.adapter.WaiterDetailAdapter;
import com.example.ordertakingapp.api.APIClient;
import com.example.ordertakingapp.api.RestApiInterface;
import com.example.ordertakingapp.request.FetchWaiterListRequest;
import com.example.ordertakingapp.response.FetchWaiterListResponse;
import com.example.ordertakingapp.utils.ConnectionDetector;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WaiterDetails extends AppCompatActivity {

    DrawerLayout drawerLayout;

    ImageView notification;

    RecyclerView recycler;
    List<Pojo_WaiterDetail> waiterDetailsList = new ArrayList<>();
    TextView txt_ViewMore;

    private String TAG = "WaiterDetails";

    private String restid,user_id;

    AVLoadingIndicatorView avi_indicator;

    List<FetchWaiterListResponse.DataBean> dataBeanList;

    TextView txt_no_waiterlist;

    private Dialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter_details);
      //  Objects.requireNonNull(getSupportActionBar()).hide();

        Log.w("onCreate",TAG);

        txt_ViewMore = findViewById(R.id.txt_viewMore);
        drawerLayout = findViewById(R.id.drawer_layout);
        recycler = findViewById(R.id.waiterlist);

        avi_indicator = findViewById(R.id.avi_indicator);
        txt_no_waiterlist = findViewById(R.id.txt_no_waiterlist);
        avi_indicator = findViewById(R.id.avi_indicator);
        avi_indicator.setVisibility(View.GONE);

        SessionManager sessionManager = new SessionManager(getApplicationContext());

        HashMap<String, String> user = sessionManager.getProfileDetails();

        restid = user.get(SessionManager.KEY_RESTID);

        user_id = user.get(SessionManager.KEY_ID);

        Log.w(TAG,"restid : "+restid );

        Log.w(TAG,"user_id : "+user_id );

        notification = findViewById(R.id.img_notification);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NotificationActivity.class));

            }
        });

        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
            getwaiterlistResponseCall();
        }

    }

    private void getwaiterlistResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<FetchWaiterListResponse> call = apiInterface.getwaiterlistResponseCall(RestUtils.getContentType(), fetchWaiterListRequest());
        Log.w(TAG,"FetchWaiterListResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<FetchWaiterListResponse>() {
            @Override
            public void onResponse(@NonNull Call<FetchWaiterListResponse> call, @NonNull Response<FetchWaiterListResponse> response) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"FetchWaiterListResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if ( 200 == response.body().getCode()) {

                        if(response.body().getData() != null && response.body().getData().size()>0){

                            dataBeanList = response.body().getData();

                            recycler.setVisibility(View.VISIBLE);
                            txt_no_waiterlist.setVisibility(View.GONE);
                            setViewWaiterList(response.body().getData());
                        }else {
                            recycler.setVisibility(View.GONE);
                            txt_no_waiterlist.setVisibility(View.VISIBLE);
                            txt_no_waiterlist.setText("No Waiters Found");
                        }


                    }else{
                        showErrorLoading(response.body().getMessage());
                    }
                }

            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<FetchWaiterListResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("FetchWaiterListResponse flr", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private FetchWaiterListRequest fetchWaiterListRequest() {

        /*
         * rest_id : 6098ff1b074e747b0fcd04b5
         */


        FetchWaiterListRequest fetchWaiterListRequest = new FetchWaiterListRequest();
        fetchWaiterListRequest.setRest_id(restid);

        Log.w(TAG,"FetchWaiterListRequest"+ new Gson().toJson(fetchWaiterListRequest));
        return fetchWaiterListRequest;

    }

    private void setViewWaiterList(List<FetchWaiterListResponse.DataBean> dataBeanList) {
        WaiterDetailAdapter waiterDetailAdapter = new WaiterDetailAdapter(this,dataBeanList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setAdapter(waiterDetailAdapter);

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

    private void waiterDetails(WaiterDetails waiterDetails) {
        redirectActivity(this,WaiterDetails.class);
    }
    public void ClickKitchenUserDetails(View view){

        kitchenUserDetails(this);
    }

    private void kitchenUserDetails(WaiterDetails waiterDetails) {
        redirectActivity(this,KitchenUserDetails.class);
    }
    public void ClickOrderDetails(View view){

        orderDetails(this);
    }

    private void orderDetails(WaiterDetails waiterDetails) {
        redirectActivity(this,OrderDetails.class);
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
}