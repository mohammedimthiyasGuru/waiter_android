package com.waiter.ordertaking.activity;

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

import com.waiter.ordertaking.R;
import com.waiter.ordertaking.RestUtils;
import com.waiter.ordertaking.api.APIClient;
import com.waiter.ordertaking.api.RestApiInterface;
import com.waiter.ordertaking.request.BlockUnBlockWaiterRequest;
import com.waiter.ordertaking.request.DeleteWaiterRequest;
import com.waiter.ordertaking.response.BlockUnBlockWaiterResponse;
import com.waiter.ordertaking.response.DeleteWaiterResponse;
import com.waiter.ordertaking.utils.ConnectionDetector;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WaiterDetailsView extends AppCompatActivity {

    Button btn_remove,btn_block,btn_unblock;

    String _id, waiter_name, waiter_addr, waiter_emailid;

    long waiter_id, waiter_number;

    TextView wait_id, wait_name,wait_mob,wait_emailid,wait_addr;

    private String TAG = "WaiterDetailsView";

    AVLoadingIndicatorView avi_indicator;

    AlertDialog alertDialog;

    String msg,status,wait_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter_details_view);
       // Objects.requireNonNull(getSupportActionBar()).hide();

        Log.w("onCreate",TAG);

        avi_indicator = findViewById(R.id.avi_indicator);
        avi_indicator.setVisibility(View.GONE);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            _id = extras.getString("_id");

            waiter_id = extras.getLong("waiter_id");

            waiter_name = extras.getString("waiter_name");

            waiter_addr= extras.getString("waiter_addr");

            waiter_number = extras.getLong("waiter_number");

            waiter_emailid = extras.getString("waiter_emailid");

            wait_status = extras.getString("status");


            Log.w(TAG,"_id : "+_id +"waiter_id : "+waiter_id

                    +"waiter_name : "+waiter_name +"waiter_addr : "+waiter_addr

                    +"waiter_number : "+waiter_number +"waiter_emailid : "+waiter_emailid

                    +"wait_status : "+wait_status);
        }


        wait_id = findViewById(R.id.txt_waiterId);

        wait_name = findViewById(R.id.txt_waiterName);

        wait_addr = findViewById(R.id.txt_address);

        wait_emailid = findViewById(R.id.txt_mailId);

        wait_mob = findViewById(R.id.txt_number);

        btn_remove = findViewById(R.id.btn_remove);

        if (waiter_id!= 0) {

            wait_id.setText("Waiter ID : "+waiter_id);
        }

        if (waiter_name!= null&&!waiter_name.isEmpty()) {

            wait_name.setText("Waiter Name : "+waiter_name);
        }

        if (waiter_number!= 0) {

            wait_mob.setText("Mobile Number: "+waiter_number);
        }

        if (waiter_emailid!= null&&!waiter_emailid.isEmpty()) {

            wait_emailid.setText("Mailid :"+waiter_emailid);
        }

        if (waiter_addr!= null&&!waiter_addr.isEmpty()) {

            wait_addr.setText("Address : "+waiter_addr);
        }


        btn_block = findViewById(R.id.btn_block);

        btn_unblock = findViewById(R.id.btn_unblock);

        if(wait_status!=null&&!wait_status.isEmpty()){

            if(wait_status.equals("true")){

                btn_block.setVisibility(View.VISIBLE);

                btn_unblock.setVisibility(View.GONE);

                btn_block.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(WaiterDetailsView.this);
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(WaiterDetailsView.this);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(WaiterDetailsView.this);

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
        Call<DeleteWaiterResponse> call = apiInterface.deletewaiterResponseCall(RestUtils.getContentType(), deleteWaiterRequest());
        Log.w(TAG,"DeleteWaiterResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<DeleteWaiterResponse>() {
            @Override
            public void onResponse(@NonNull Call<DeleteWaiterResponse> call, @NonNull Response<DeleteWaiterResponse> response) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"DeleteWaiterResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if ( 200 == response.body().getCode()) {

                        Toast.makeText(getApplicationContext(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(WaiterDetailsView.this,WaiterDetails.class));

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

        Log.w(TAG,"DeleteWaiterRequest"+ new Gson().toJson(deleteWaiterRequest));
        return deleteWaiterRequest;

    }

    private void blockorunblocklistResponseCall(String msg, String status) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<BlockUnBlockWaiterResponse> call = apiInterface.blockorunblockwaiterResponseCall(RestUtils.getContentType(), blockUnBlockWaiterRequest(status));
        Log.w(TAG,"BlockUnBlockWaiterResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<BlockUnBlockWaiterResponse>() {
            @Override
            public void onResponse(@NonNull Call<BlockUnBlockWaiterResponse> call, @NonNull Response<BlockUnBlockWaiterResponse> response) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"BlockUnBlockWaiterResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if ( 200 == response.body().getCode()) {

                        Toast.makeText(getApplicationContext(), ""+msg, Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(WaiterDetailsView.this,WaiterDetails.class));

                    }else{
                        showErrorLoading(response.body().getMessage());
                    }
                }

            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<BlockUnBlockWaiterResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("BlockUnBlockWaiterResponse flr", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private BlockUnBlockWaiterRequest blockUnBlockWaiterRequest(String status) {

        /*
         * _id : 6099001377ada17c9682975e
         * waiter_status : true
         */

        BlockUnBlockWaiterRequest blockUnBlockWaiterRequest = new BlockUnBlockWaiterRequest();
        blockUnBlockWaiterRequest.set_id(_id);
        blockUnBlockWaiterRequest.setWaiter_status(status);

        Log.w(TAG,"BlockUnBlockWaiterRequest"+ new Gson().toJson(blockUnBlockWaiterRequest));
        return blockUnBlockWaiterRequest;

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