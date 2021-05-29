package com.example.ordertakingapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.ordertakingapp.PaymentAdapter;
import com.example.ordertakingapp.Pojo_PaymentDetails;
import com.example.ordertakingapp.R;

import java.util.ArrayList;
import java.util.List;

public class PaymentDetails extends AppCompatActivity {
    DrawerLayout drawerLayout;

    ImageView notification;

    RecyclerView recycler;
    List<Pojo_PaymentDetails> pojo_paymentDetailsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);
        //Objects.requireNonNull(getSupportActionBar()).hide();

        drawerLayout = findViewById(R.id.drawer_layout);

        recycler = findViewById(R.id.transactionlist);
        getList();

        PaymentAdapter paymentAdapter = new PaymentAdapter(this,pojo_paymentDetailsList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setAdapter(paymentAdapter);

        notification = findViewById(R.id.img_notification);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NotificationActivity.class));

            }
        });
    }

    private void getList() {
        pojo_paymentDetailsList = new ArrayList<>();
        pojo_paymentDetailsList.add(new Pojo_PaymentDetails("Money transaction","Mohammad","USD 1000",
                "NOv 06", "Successful",R.drawable.prof_icon));

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

    private void waiterDetails(PaymentDetails paymentDetails) {
        redirectActivity(this,WaiterDetails.class);
    }
    public void ClickKitchenUserDetails(View view){

        kitchenUserDetails(this);
    }

    private void kitchenUserDetails(PaymentDetails paymentDetails) {
        redirectActivity(this,KitchenUserDetails.class);
    }
    public void ClickOrderDetails(View view){

        orderDetails(this);
    }

    private void orderDetails(PaymentDetails paymentDetails) {
        redirectActivity(this,OrderDetails.class);
    }
    public void ClickPaymentDetails(View view){

        paymentDetails(this);
    }

    private void paymentDetails(PaymentDetails paymentDetails) {
        redirectActivity(this,PaymentDetails.class);
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