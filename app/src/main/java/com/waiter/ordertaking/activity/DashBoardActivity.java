package com.waiter.ordertaking.activity;

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

import com.waiter.ordertaking.R;
import com.waiter.ordertaking.RestUtils;
import com.waiter.ordertaking.SessionManager.SessionManager;
import com.waiter.ordertaking.admin.AdminRequestActivity;
import com.waiter.ordertaking.admin.TableListActivity;
import com.waiter.ordertaking.api.APIClient;
import com.waiter.ordertaking.api.RestApiInterface;
import com.waiter.ordertaking.request.DashboardRequest;
import com.waiter.ordertaking.response.DashboardResponse;
import com.waiter.ordertaking.utils.ConnectionDetector;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoardActivity extends AppCompatActivity {

    ImageView img_refresh;

    TextView txt_user,txt_item,txt_order,txt_notification,txt_totalSale,txt_table;
    TextView txt_userCount,txt_itemCount,txt_orderCount,txt_notifiCount,txt_totalCount,txt_tableCount;

    DrawerLayout drawerLayout;
    private String TAG = "DashBoardActivity";

    AVLoadingIndicatorView avi_indicator;
    private Dialog alertDialog;

    String type, userid, restid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        //Objects.requireNonNull(getSupportActionBar()).hide();

        SessionManager session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getProfileDetails();
        type = user.get(SessionManager.KEY_TYPE);
        userid = user.get(SessionManager.KEY_ID);
        restid = user.get(SessionManager.KEY_RESTID);


        Log.w(TAG,"session--->"+"type :"+type+" "+"userid :"+" "+userid+" restid : "+restid);

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


        img_refresh = findViewById(R.id.img_refresh);
        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
            dashboardResponseCall();
        }


        img_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                    dashboardResponseCall();
                }
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

        dashboardRequest.setRest_id(restid);

        Log.w(TAG,"DashboardRequest"+ new Gson().toJson(dashboardRequest));

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

    public void ClickNotifications(View view){
          notificationDetails(view);
    }

    private void notificationDetails(View view) {
        redirectActivity(this,NotificationActivity.class);
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

    public void ClickTablelist(View view){
        Intent intent = new Intent(getApplicationContext(), TableListActivity.class);
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