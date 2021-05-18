package com.example.ordertakingapp.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ordertakingapp.R;
import com.example.ordertakingapp.RestUtils;
import com.example.ordertakingapp.api.APIClient;
import com.example.ordertakingapp.api.RestApiInterface;
import com.example.ordertakingapp.request.BlockUnBlockChefRequest;
import com.example.ordertakingapp.request.DeleteWaiterRequest;
import com.example.ordertakingapp.response.BlockUnBlockChefResponse;
import com.example.ordertakingapp.response.DeleteWaiterResponse;
import com.example.ordertakingapp.utils.ConnectionDetector;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KitchenUserDetailView extends AppCompatActivity {

    Button btn_remove,btn_block, btn_unblock;

    String _id, chef_name, chef_addr, chef_emailid;

    long chef_id, chef_number;

    TextView txt_chef_id, txt_chef_name,txt_chef_mob,txt_chef_emailid,txt_chef_addr;

    private String TAG = "KitchenUserDetailView";

    AVLoadingIndicatorView avi_indicator;

    AlertDialog alertDialog;

    String msg,status,chef_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_user_detail_view);

    //    Objects.requireNonNull(getSupportActionBar()).hide();

        Log.w("onCreate",TAG);

        avi_indicator = findViewById(R.id.avi_indicator);
        avi_indicator.setVisibility(View.GONE);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            _id = extras.getString("_id");

            chef_id = extras.getLong("chef_id");

            chef_name = extras.getString("chef_name");

            chef_addr= extras.getString("chef_addr");

            chef_number = extras.getLong("chef_number");

            chef_emailid = extras.getString("chef_emailid");

            chef_status = extras.getString("status");


            Log.w(TAG,"_id : "+_id +"chef_id : "+chef_id

                    +"chef_name" + chef_name +"chef_addr : "+chef_addr

                    +"chef_number : "+chef_number +"chef_emailid : "+chef_emailid

                    +"chef_status : "+chef_status);
        }


        txt_chef_id = findViewById(R.id.txt_kitchenId);

        txt_chef_name = findViewById(R.id.txt_kitchenName);

        txt_chef_addr = findViewById(R.id.txt_address);

        txt_chef_emailid = findViewById(R.id.txt_mailId);

        txt_chef_mob = findViewById(R.id.txt_number);

        btn_remove = findViewById(R.id.btn_remove);

        if (chef_id!= 0) {

            txt_chef_id.setText("Waiter ID : "+chef_id);
        }

        if (chef_name != null&&!chef_name.isEmpty()) {

            txt_chef_name.setText("Waiter Name : "+chef_name);
        }

        if (chef_number!= 0) {

            txt_chef_mob.setText("Mobile Number: "+chef_number);
        }

        if (chef_emailid!= null&&!chef_emailid.isEmpty()) {

            txt_chef_emailid.setText("Mailid :"+chef_emailid);
        }

        if (chef_addr!= null&&!chef_addr.isEmpty()) {

            txt_chef_addr.setText("Address : "+chef_addr);
        }


        btn_block = findViewById(R.id.btn_block);

        btn_unblock = findViewById(R.id.btn_unblock);

        if(chef_status!=null&&!chef_status.isEmpty()) {

            if (chef_status.equals("true")) {

                btn_block.setVisibility(View.VISIBLE);

                btn_unblock.setVisibility(View.GONE);

                btn_block.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(KitchenUserDetailView.this);
                        builder.setTitle("Block");
                        builder.setMessage("Are you sure you want to block ?");

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {

                                    msg="Blocked";

                                    status = "false";

                                    blockorunblocklistResponseCall(msg,status );

                                }

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
                });

            }

            else {

                btn_block.setVisibility(View.GONE);

                btn_unblock.setVisibility(View.VISIBLE);

                btn_unblock.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(KitchenUserDetailView.this);
                        builder.setTitle("Unblock");
                        builder.setMessage("Are you sure you want to unblock ?");

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {

                                    msg="UnBlocked";

                                    status = "true";

                                    blockorunblocklistResponseCall(msg,status );

                                }

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
                });





            }

        }


        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(KitchenUserDetailView.this);

                builder.setTitle("Remove");
                builder.setMessage("Are you sure you want to remove ?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {

                            removeResponseCall();

                        }
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
        });



    }


    private void removeResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<DeleteWaiterResponse> call = apiInterface.deletechefResponseCall(RestUtils.getContentType(), deleteWaiterRequest());
        Log.w(TAG,"DeleteChefResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<DeleteWaiterResponse>() {
            @Override
            public void onResponse(@NonNull Call<DeleteWaiterResponse> call, @NonNull Response<DeleteWaiterResponse> response) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"DeleteChefResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if ( 200 == response.body().getCode()) {

                        Toast.makeText(getApplicationContext(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(KitchenUserDetailView.this,KitchenUserDetails.class));

                    }else{
                        showErrorLoading(response.body().getMessage());
                    }
                }

            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<DeleteWaiterResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("DeleteWaiterResponse flr", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private DeleteWaiterRequest deleteWaiterRequest() {

        /*
         * _id : 6099001377ada17c9682975e
         */

        DeleteWaiterRequest deleteWaiterRequest = new DeleteWaiterRequest();
        deleteWaiterRequest.set_id(_id);

        Log.w(TAG,"DeleteChefRequest"+ new Gson().toJson(deleteWaiterRequest));
        return deleteWaiterRequest;

    }

    private void blockorunblocklistResponseCall(String msg, String status) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<BlockUnBlockChefResponse> call = apiInterface.blockorunblockchiefResponseCall(RestUtils.getContentType(), blockUnBlockChefRequest(status));
        Log.w(TAG,"BlockUnBlockChefResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<BlockUnBlockChefResponse>() {
            @Override
            public void onResponse(@NonNull Call<BlockUnBlockChefResponse> call, @NonNull Response<BlockUnBlockChefResponse> response) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"BlockUnBlockChefResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if ( 200 == response.body().getCode()) {

                        Toast.makeText(getApplicationContext(), ""+msg, Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(KitchenUserDetailView.this,KitchenUserDetails.class));

                    }else{
                        showErrorLoading(response.body().getMessage());
                    }
                }

            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<BlockUnBlockChefResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("BlockUnBlockChefResponse flr", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private BlockUnBlockChefRequest blockUnBlockChefRequest(String status) {

        /*
         * _id : 6099001377ada17c9682975e
         * chef_status : true
         */

        BlockUnBlockChefRequest blockUnBlockChefRequest = new BlockUnBlockChefRequest();
        blockUnBlockChefRequest.set_id(_id);
        blockUnBlockChefRequest.setChef_status(status);

        Log.w(TAG,"BlockUnBlockChefRequest"+ new Gson().toJson(blockUnBlockChefRequest));
        return blockUnBlockChefRequest;

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

