package com.waiter.ordertaking.activity;

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

import com.waiter.ordertaking.adapter.OrderDetailAdapter;
import com.waiter.ordertaking.Pojo_OrderDetail;
import com.waiter.ordertaking.R;

import java.util.ArrayList;
import java.util.List;

public class OrderDetails extends AppCompatActivity {

    DrawerLayout drawerLayout;

    RecyclerView recycler;
    List<Pojo_OrderDetail> orderDetailsList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        //Objects.requireNonNull(getSupportActionBar()).hide();

        drawerLayout = findViewById(R.id.drawer_layout);
        recycler = findViewById(R.id.rv_orderlist);

        getList();
        OrderDetailAdapter orderDetailAdapter = new OrderDetailAdapter(this,orderDetailsList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setAdapter(orderDetailAdapter);


        ImageView img_refresh = findViewById(R.id.img_refresh);
        img_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getList();
                OrderDetailAdapter orderDetailAdapter = new OrderDetailAdapter(getApplicationContext(),orderDetailsList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                recycler.setLayoutManager(linearLayoutManager);
                recycler.setAdapter(orderDetailAdapter);

            }
        });

    }
    private void getList() {
        orderDetailsList = new ArrayList<>();
        orderDetailsList.add(new Pojo_OrderDetail("1","Order 1","Order Count:32","19-04-2021 07:00 PM",
                "Order Delivery","₹ 1000"));
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

    private void waiterDetails(OrderDetails orderDetails) {
        redirectActivity(this,WaiterDetails.class);
    }
    public void ClickKitchenUserDetails(View view){

        kitchenUserDetails(this);
    }

    private void kitchenUserDetails(OrderDetails orderDetails) {
        redirectActivity(this,KitchenUserDetails.class);
    }
    public void ClickOrderDetails(View view){

        orderDetails(this);
    }

    private void orderDetails(OrderDetails orderDetails) {
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
