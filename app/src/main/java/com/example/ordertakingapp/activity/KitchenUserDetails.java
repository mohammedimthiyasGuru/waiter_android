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

import com.example.ordertakingapp.Pojo_KitchenUserDetail;
import com.example.ordertakingapp.R;
import com.example.ordertakingapp.RestUtils;
import com.example.ordertakingapp.SessionManager.SessionManager;
import com.example.ordertakingapp.adapter.KitchenUserDetailAdapter;
import com.example.ordertakingapp.api.APIClient;
import com.example.ordertakingapp.api.RestApiInterface;
import com.example.ordertakingapp.request.FetchChiefListRequest;
import com.example.ordertakingapp.response.FetchChiefListResponse;
import com.example.ordertakingapp.utils.ConnectionDetector;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KitchenUserDetails extends AppCompatActivity {

    DrawerLayout drawerLayout;

    ImageView notification;

    RecyclerView recycler;
    List<Pojo_KitchenUserDetail> kitchenUserDetail = new ArrayList<>();
    TextView txt_ViewMore;

    private String TAG = "KitchenUserDetails";

    private String restid,user_id;

    AVLoadingIndicatorView avi_indicator;

    List<FetchChiefListResponse.DataBean> dataBeanList;

    TextView txt_no_waiterlist;

    private Dialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_user_details);

        Log.w("onCreate",TAG);


        //Objects.requireNonNull(getSupportActionBar()).hide();
        txt_ViewMore = findViewById(R.id.txt_viewMore);
        drawerLayout = findViewById(R.id.drawer_layout);
        recycler = findViewById(R.id.kitchenUserlist);


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
                startActivity(new Intent(getApplicationContext(), Notification.class));

            }
        });

        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
            getchieflistResponseCall();
        }


    }


    private void getchieflistResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<FetchChiefListResponse> call = apiInterface.getcheflistResponseCall(RestUtils.getContentType(),fetchChiefListRequest());
        Log.w(TAG,"FetchChiefListResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<FetchChiefListResponse>() {
            @Override
            public void onResponse(@NonNull Call<FetchChiefListResponse> call, @NonNull Response<FetchChiefListResponse> response) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"FetchChiefListResponse" + new Gson().toJson(response.body()));
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
                            txt_no_waiterlist.setText("No Chef's Found");
                        }


                    }else{
                        showErrorLoading(response.body().getMessage());
                    }
                }

            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<FetchChiefListResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("FetchChiefListResponse flr", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private FetchChiefListRequest fetchChiefListRequest() {

        /*
         * rest_id : 6098ff1b074e747b0fcd04b5
         */


        FetchChiefListRequest fetchChiefListRequest = new FetchChiefListRequest();
        fetchChiefListRequest.setRest_id(restid);

        Log.w(TAG,"FetchChiefListRequest"+ new Gson().toJson(fetchChiefListRequest));
        return fetchChiefListRequest;

    }

    private void setViewWaiterList(List<FetchChiefListResponse.DataBean> dataBeanList) {

        KitchenUserDetailAdapter kitchenUserDetailAdapter = new KitchenUserDetailAdapter(this,dataBeanList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setAdapter(kitchenUserDetailAdapter);


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



    private void getList() {
        kitchenUserDetail = new ArrayList<>();
        kitchenUserDetail.add(new Pojo_KitchenUserDetail("012345","Mohammad","Order Count:32","19/04/2021",
                "12:00","View More", R.drawable.prof_icon));

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

    public void ClickKitchenUserDetails(View view){

        kitchenUserDetails(this);
    }

    private void kitchenUserDetails(KitchenUserDetails kitchenUserDetails) {
        redirectActivity(this,KitchenUserDetails.class);
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