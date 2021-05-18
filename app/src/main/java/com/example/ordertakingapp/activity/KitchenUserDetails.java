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
import android.widget.TextView;

import com.example.ordertakingapp.adapter.KitchenUserDetailAdapter;
import com.example.ordertakingapp.Pojo_KitchenUserDetail;
import com.example.ordertakingapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class KitchenUserDetails extends AppCompatActivity {

    DrawerLayout drawerLayout;

    ImageView notification;

    RecyclerView recycler;
    List<Pojo_KitchenUserDetail> kitchenUserDetail = new ArrayList<>();
    TextView txt_ViewMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_user_details);
        //Objects.requireNonNull(getSupportActionBar()).hide();
        txt_ViewMore = findViewById(R.id.txt_viewMore);
        drawerLayout = findViewById(R.id.drawer_layout);
        recycler = findViewById(R.id.kitchenUserlist);
        getList();

        KitchenUserDetailAdapter kitchenUserDetailAdapter = new KitchenUserDetailAdapter(this,kitchenUserDetail);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setAdapter(kitchenUserDetailAdapter);

        notification = findViewById(R.id.img_notification);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Notification.class));

            }
        });

    }

    private void getList() {
        kitchenUserDetail = new ArrayList<>();
        kitchenUserDetail.add(new Pojo_KitchenUserDetail("012345","Mohammad","Order Count:32","19/04/2021",
                "12:00","View More",R.drawable.prof_icon));

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