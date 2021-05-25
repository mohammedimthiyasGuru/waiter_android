package com.example.ordertakingapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

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
import com.example.ordertakingapp.admin.AdminRequestActivity;
import com.example.ordertakingapp.api.APIClient;
import com.example.ordertakingapp.api.RestApiInterface;
import com.example.ordertakingapp.kitchen.KitchenAdminRequestActivity;
import com.example.ordertakingapp.request.DashboardRequest;
import com.example.ordertakingapp.response.DashboardResponse;
import com.example.ordertakingapp.utils.ConnectionDetector;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoardActivity extends AppCompatActivity {

    ImageView notification;

    TextView txt_user,txt_item,txt_order,txt_notification,txt_totalSale,txt_table;
    TextView txt_userCount,txt_itemCount,txt_orderCount,txt_notifiCount,txt_totalCount,txt_tableCount;

    DrawerLayout drawerLayout;
    private String TAG = "DashBoard";

    AVLoadingIndicatorView avi_indicator;
    private Dialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        //Objects.requireNonNull(getSupportActionBar()).hide();

        avi_indicator = findViewById(R.id.avi_indicator);
        avi_indicator.setVisibility(View.GONE);

        drawerLayout = findViewById(R.id.drawer_layout);
        txt_user = findViewById(R.id.txt_user);
        txt_item = findViewById(R.id.txt_item);
        txt_order = findViewById(R.id.txt_order);
        txt_notification = findViewById(R.id.txt_notification);
        txt_totalSale = findViewById(R.id.txt_totalSale);
        txt_table = findViewById(R.id.txt_table);

        txt_userCount = findViewById(R.id.txt_userCount);
        txt_itemCount = findViewById(R.id.txt_itemCount);
        txt_orderCount = findViewById(R.id.txt_orderCount);
        txt_notifiCount = findViewById(R.id.txt_notifiCount);
        txt_totalCount = findViewById(R.id.txt_totalCount);
        txt_tableCount = findViewById(R.id.txt_tableCount);


        notification = findViewById(R.id.img_notification);
        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
            dashboardResponseCall();
        }


        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Notification.class));

            }
        });

    }

    private void dashboardResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<DashboardResponse> call = apiInterface.dashboardResponseCall(RestUtils.getContentType(),dashboardRequest());
        Log.w(TAG,"dashboardResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<DashboardResponse>() {
           @SuppressLint("SetTextI18n")
           @Override
           public void onResponse(@NonNull Call<DashboardResponse> call, @NonNull Response<DashboardResponse> response) {
              avi_indicator.smoothToHide();

               if (response.body() != null) {
                   if ( 200 == response.body().getCode()){

                       Log.w(TAG,"dashboardResponseCall " + new Gson().toJson(response.body()));

                       if(response.body().getData() != null){
                           txt_userCount.setText(""+response.body().getData().getUser_count());
                           txt_itemCount.setText(""+response.body().getData().getItem_count());
                           txt_orderCount.setText(""+response.body().getData().getOrder_count());
                           txt_notifiCount.setText(""+response.body().getData().getNotification_count());
                           txt_totalCount.setText(""+response.body().getData().getToday_sale());
                           txt_tableCount.setText(""+response.body().getData().getTable_count());
                       }


                   }else{
                       showErrorLoading(response.body().getMessage());
                   }
               }

           }

           @Override
           public void onFailure(@NonNull Call<DashboardResponse> call, @NonNull Throwable t) {
               avi_indicator.smoothToHide();
               Log.w(TAG,"dashboardResponseCall flr "+ t.toString());
           }
       });

    }

    private DashboardRequest dashboardRequest() {
        DashboardRequest dashboardRequest = new DashboardRequest();
        return dashboardRequest;
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

    private void dashboardDetails(DashBoardActivity dashBoardActivity) {
        redirectActivity(this,DashBoardActivity.class);
    }
    public void ClickWaiterDetails(View view){

        waiterDetails(this);
    }

    private void waiterDetails(DashBoardActivity dashBoardActivity) {
        redirectActivity(this,WaiterDetails.class);
    }
    public void ClickKitchenUserDetails(View view){

        kitchenUserDetails(this);
    }

    private void kitchenUserDetails(DashBoardActivity dashBoardActivity) {
        redirectActivity(this,KitchenUserDetails.class);
    }
    public void ClickOrderDetails(View view){

        orderDetails(this);
    }

    private void orderDetails(DashBoardActivity dashBoardActivity) {
        redirectActivity(this,AdminOrderListActivity.class);
    }

    public void ClickPaymentDetails(View view){

        paymentDetails(this);
    }

    private void paymentDetails(DashBoardActivity dashBoardActivity) {
        redirectActivity(this,PaymentDetails.class);
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

}