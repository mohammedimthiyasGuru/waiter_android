package com.example.ordertakingapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ordertakingapp.R;
import com.example.ordertakingapp.RestUtils;
import com.example.ordertakingapp.SessionManager.SessionManager;
import com.example.ordertakingapp.api.APIClient;
import com.example.ordertakingapp.api.RestApiInterface;
import com.example.ordertakingapp.request.LoginRequest;
import com.example.ordertakingapp.response.LoginResponse;
import com.example.ordertakingapp.utils.ConnectionDetector;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button btn_login1;
    TextInputEditText txt_number;
    private String TAG = "LoginActivity";

    AVLoadingIndicatorView avi_indicator;
    private Dialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Objects.requireNonNull(getSupportActionBar()).hide();
        txt_number = findViewById(R.id.mobile_no);
        btn_login1  = findViewById(R.id.login1);
        avi_indicator = findViewById(R.id.avi_indicator);
        avi_indicator.setVisibility(View.GONE);

        btn_login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Number = Objects.requireNonNull(txt_number.getText()).toString();

                if (TextUtils.isEmpty(Number)) {
                    Toast.makeText(LoginActivity.this, "Please Enter Number", Toast.LENGTH_SHORT).show();
                }else{
                    if (new ConnectionDetector(LoginActivity.this).isNetworkAvailable(LoginActivity.this)) {
                        loginResponseCall();
                    }
                }

            }

        });

    }



    private void loginResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<LoginResponse> call = apiInterface.loginResponseCall(RestUtils.getContentType(), loginRequest());
        Log.w(TAG,"LoginResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"Login Response" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if ( 200 == response.body().getCode()) {
                        SessionManager sessionManager = new SessionManager(LoginActivity.this);
                        sessionManager.setIsLogin(true);
                        sessionManager.createLoginSession(
                                response.body().getData().get_id(),
                                response.body().getData().getUser_name(),
                                response.body().getData().getPhone_no(),
                                String.valueOf(response.body().getData().getUser_type()),
                                response.body().getData().getRest_id()
                        );

                        if(response.body().getData().getUser_type() == 1){
                            Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), DashBoardActivity.class);
                            startActivity(intent);
                        } else  if(response.body().getData().getUser_type() == 2){
                            Toast.makeText(LoginActivity.this,"Login Success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), WaiterActivity.class);
                            startActivity(intent);

                        }else  if(response.body().getData().getUser_type() == 3){
                            Toast.makeText(LoginActivity.this,"Login Success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), KitchenTakingActivity.class);
                            startActivity(intent);

                        }

                    }else{
                        showErrorLoading(response.body().getMessage());
                    }
                }

                }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("LoginResponse flr", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private LoginRequest loginRequest() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPhone_no(Objects.requireNonNull(txt_number.getText()).toString());
        Log.w(TAG,"loginRequest"+ new Gson().toJson(loginRequest));
        return loginRequest;
    }
    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(errormesage);
        alertDialogBuilder.setPositiveButton("ok",
                (arg0, arg1) -> hideLoading());


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    public void hideLoading(){
        try {
            alertDialog.dismiss();
        }catch (Exception ignored){

        }
    }

}