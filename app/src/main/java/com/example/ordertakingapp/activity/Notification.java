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

import com.example.ordertakingapp.adapter.NotificationAdapter;
import com.example.ordertakingapp.Pojo_Notification;
import com.example.ordertakingapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Notification extends AppCompatActivity {

    DrawerLayout drawerLayout;


    RecyclerView recycler;
    NotificationAdapter notificationAdapter;
    List<Pojo_Notification> notifyList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Objects.requireNonNull(getSupportActionBar()).hide();

        drawerLayout = findViewById(R.id.drawer_layout);


        recycler = findViewById(R.id.notify_recycler);

        notifyList =new ArrayList<>();
        notifyList.add(new Pojo_Notification("Notfication 1","some one message",
                "12:30",R.drawable.prof_icon,R.drawable.star));
        notifyList.add(new Pojo_Notification("Notfication 1","some one message",
                "12:30",R.drawable.prof_icon,R.drawable.star));

        notificationAdapter = new NotificationAdapter(this,notifyList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setAdapter(notificationAdapter);

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
    public void ClickDashBoard(View view){

        redirectActivity(this,Notification.class);

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