package com.waiter.ordertaking.kitchen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.waiter.ordertaking.R;
import com.waiter.ordertaking.RestUtils;
import com.waiter.ordertaking.SessionManager.SessionManager;
import com.waiter.ordertaking.api.APIClient;
import com.waiter.ordertaking.api.RestApiInterface;


import com.waiter.ordertaking.request.KitchenAdminCreateRequest;
import com.waiter.ordertaking.response.DropDownCatListResponse;
import com.waiter.ordertaking.response.WaiterAdminRequestListResponse;
import com.waiter.ordertaking.response.SuccessResponse;
import com.waiter.ordertaking.utils.ConnectionDetector;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KitchenAdminNewRequestActivity extends AppCompatActivity {


    private String TAG = "KitchenAdminNewRequestActivity";
    AVLoadingIndicatorView avi_indicator;
    TextView txt_back;
    LottieAnimationView back_icon;

    Spinner spr_category_type;
    EditText edt_comment;
    Button btn_submit;







    SessionManager session;
    String type = "",name = "",userid = "";
    private String fromactivity;
    private List<WaiterAdminRequestListResponse.DataBean> KitchenAdminCreateRequest;
    private List<DropDownCatListResponse.DataBean> catTypeList;
    private String strCatTitle;
    private String restid,username,usertype;


    @SuppressLint("LogNotTimber")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_admin_new_request);
        Log.w(TAG,"onCreate-->");


        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            fromactivity = extras.getString("fromactivity");
            Log.w(TAG,"Bundle "+" fromactivity : "+fromactivity);



        }

        avi_indicator = findViewById(R.id.avi_indicator);
        avi_indicator.setVisibility(View.GONE);

        spr_category_type = findViewById(R.id.spr_category_type);
        edt_comment = findViewById(R.id.edt_comment);
        btn_submit = findViewById(R.id.btn_submit);

        spr_category_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                strCatTitle = spr_category_type.getSelectedItem().toString();

                Log.w(TAG,"strCatTitle : "+strCatTitle);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });




        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getProfileDetails();
        type = user.get(SessionManager.KEY_TYPE);
        userid = user.get(SessionManager.KEY_ID);
        restid = user.get(SessionManager.KEY_RESTID);
        username= user.get(SessionManager.KEY_USER_NAME);
        usertype= user.get(SessionManager.KEY_TYPE);


        Log.w(TAG,"session--->"+"type :"+type+" "+"name :"+" "+name);
        txt_back = findViewById(R.id.back);
        back_icon = findViewById(R.id.back_icon);


        back_icon.setOnClickListener(v -> onBackPressed());

        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
            dropDownListResponseCall();
        }



        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              addYourRequestValidator();
            }
        });





    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void addYourRequestValidator() {
        boolean can_proceed = true;
           if(validdSelectCatgoryTitle()){
            if (edt_comment.getText().toString().trim().equals("")) {
                edt_comment.setError("Please enter the details ");
                edt_comment.requestFocus();
                can_proceed = false;
            }else {
                can_proceed = true;
            }
        }else{
               can_proceed = false;
           }


        if (can_proceed) {
            if (new ConnectionDetector(KitchenAdminNewRequestActivity.this).isNetworkAvailable(KitchenAdminNewRequestActivity.this)) {
                waiterAdminCreateResponseCall();

            }

        }






    }

    public boolean validdSelectCatgoryTitle() {
        if(strCatTitle.equalsIgnoreCase("Select Category Title")){
            final AlertDialog alertDialog = new AlertDialog.Builder(KitchenAdminNewRequestActivity.this).create();
            alertDialog.setMessage(getString(R.string.err_msg_category_title));
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                    (dialog, which) -> alertDialog.cancel());
            alertDialog.show();

            return false;
        }

        return true;
    }


    @SuppressLint("LogNotTimber")
    private void waiterAdminCreateResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<SuccessResponse> call = ApiService.chefAdminCreateResponseCall(RestUtils.getContentType(), KitchenAdminCreateRequest());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<SuccessResponse> call, @NonNull Response<SuccessResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"SuccessResponse"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if(response.body().getCode() == 200){

                        onBackPressed();


                    }

                }


            }

            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<SuccessResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"SuccessResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private KitchenAdminCreateRequest KitchenAdminCreateRequest() {

        /*
         * rest_id : 6098ff1b074e747b0fcd04b5
         * chef_id : 60a3b19a9bbb7779da13ac7f
         * chef_name : Dinesh
         * type : Chef
         * title : Need Food
         * request_text : I need food to eat the, i am hungry
         * request_date : 23-10-2020 11:00 AM
         */

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());


        KitchenAdminCreateRequest KitchenAdminCreateRequest = new KitchenAdminCreateRequest();
        KitchenAdminCreateRequest.setRest_id(restid);
        KitchenAdminCreateRequest.setChef_id(userid);
        KitchenAdminCreateRequest.setChef_name(username);
        KitchenAdminCreateRequest.setType(usertype);
        KitchenAdminCreateRequest.setTitle(strCatTitle);
        KitchenAdminCreateRequest.setRequest_text(edt_comment.getText().toString());
        KitchenAdminCreateRequest.setRequest_date(currentDateandTime);
        Log.w(TAG,"KitchenAdminCreateRequest"+ "--->" + new Gson().toJson(KitchenAdminCreateRequest));
        return KitchenAdminCreateRequest;
    }






    @Override
    public void onBackPressed() {
        super.onBackPressed();
            startActivity(new Intent(getApplicationContext(),KitchenAdminRequestActivity.class));
            finish();


    }

    @SuppressLint("LogNotTimber")
    public void dropDownListResponseCall(){
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<DropDownCatListResponse> call = apiInterface.dropDownListResponseCall(RestUtils.getContentType());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<DropDownCatListResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<DropDownCatListResponse> call, @NonNull Response<DropDownCatListResponse> response) {
                avi_indicator.smoothToHide();


                if (response.body() != null) {
                    if(200 == response.body().getCode()){
                        Log.w(TAG,"DropDownCatListResponse" + new Gson().toJson(response.body()));
                        if(response.body().getData()!= null) {
                            catTypeList = response.body().getData();
                        }
                        if(catTypeList != null && catTypeList.size()>0){
                            setSprCatType(catTypeList);
                        }


                    }

                }

            }


            @Override
            public void onFailure(@NonNull Call<DropDownCatListResponse> call,@NonNull  Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"DropDownCatListResponse flr"+t.getMessage());
            }
        });

    }

    private void setSprCatType(List<DropDownCatListResponse.DataBean> catTypeList) {
        ArrayList<String> catArrayList = new ArrayList<>();
        catArrayList.add("Select Category Title");
        for (int i = 0; i < catTypeList.size(); i++) {

            String catTitle = catTypeList.get(i).getTitle();
            Log.w(TAG,"catTitle-->"+catTitle);
            catArrayList.add(catTitle);

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(KitchenAdminNewRequestActivity.this, R.layout.spinner_item, catArrayList);
            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item); // The drop down view
            spr_category_type.setAdapter(spinnerArrayAdapter);


        }
    }


}
