package com.example.ordertakingapp.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordertakingapp.R;
import com.example.ordertakingapp.RestUtils;
import com.example.ordertakingapp.SessionManager.SessionManager;
import com.example.ordertakingapp.adapter.SOSAdapter;
import com.example.ordertakingapp.api.APIClient;
import com.example.ordertakingapp.api.RestApiInterface;
import com.example.ordertakingapp.interfaces.SoSCallListener;
import com.example.ordertakingapp.request.SoSRequest;
import com.example.ordertakingapp.response.SoSResponse;
import com.example.ordertakingapp.utils.ConnectionDetector;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SoSWaiterActivity extends AppCompatActivity implements SoSCallListener {

    AVLoadingIndicatorView avi_indicator;
    private String TAG = "SoSActivity";
    private String restid;
    private List<SoSResponse.DataBean> sosList;
    RecyclerView rv_sosnumbers;
    Button btn_call;
    TextView txt_no_records;
    ImageView img_close;
    private String sosPhonenumber;
    private static final int REQUEST_PHONE_CALL =1 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);

        SessionManager sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getProfileDetails();
        // restid = user.get(SessionManager.KEY_RESTID);
        restid = user.get(SessionManager.KEY_RESTID);
        avi_indicator = findViewById(R.id.avi_indicator);
        avi_indicator.setVisibility(View.GONE);


        rv_sosnumbers = (RecyclerView)findViewById(R.id.rv_sosnumbers);
         btn_call = (Button)findViewById(R.id.btn_call);
         txt_no_records = (TextView)findViewById(R.id.txt_no_records);
         img_close = (ImageView)findViewById(R.id.img_close);

        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),WaiterActivity.class));
                finish();
            }
        });

        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
            soSResponseCall();
        }

            btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SoSWaiterActivity.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                }
                else
                {
                    gotoPhone();
                }

            }
        });

    }

    @SuppressLint("LogNotTimber")
    private void soSResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<SoSResponse> call = ApiService.soSResponseCall(RestUtils.getContentType(),soSRequest());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<SoSResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<SoSResponse> call, @NonNull Response<SoSResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"SuccessResponse"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        sosList = response.body().getData();

                        if(sosList != null && sosList.size()>0){
                            rv_sosnumbers.setVisibility(View.VISIBLE);
                            btn_call.setVisibility(View.VISIBLE);
                            txt_no_records.setVisibility(View.GONE);
                            rv_sosnumbers.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            rv_sosnumbers.setItemAnimator(new DefaultItemAnimator());

                            SOSAdapter sosAdapter = new SOSAdapter(getApplicationContext(), sosList, SoSWaiterActivity.this);
                            rv_sosnumbers.setAdapter(sosAdapter);
                        }
                        else{
                            rv_sosnumbers.setVisibility(View.GONE);
                            btn_call.setVisibility(View.GONE);
                            txt_no_records.setVisibility(View.VISIBLE);
                            txt_no_records.setText("No phone numbers");

                        }
                    }

                }


            }

            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<SoSResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"SuccessResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private SoSRequest soSRequest() {
        /*
         * rest_id : 6098ff1b074e747b0fcd04b5
         * waiter_id : 60a3b19a9bbb7779da13ac7f
         * waiter_name : Dinesh
         * type : Waiter
         * title : Need Food
         * request_text : I need food to eat the, i am hungry
         * request_date : 23-10-2020 11:00 AM
         */
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        SoSRequest soSRequest = new SoSRequest();
        soSRequest.setRest_id(restid);

        Log.w(TAG,"soSRequest"+ "--->" + new Gson().toJson(soSRequest));
        return soSRequest;
    }


    @Override
    public void soSCallListener(long phonenumber) {
        if(phonenumber != 0){
            sosPhonenumber = String.valueOf(phonenumber);
        }
    }

    private void gotoPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + sosPhonenumber));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);
    }
}