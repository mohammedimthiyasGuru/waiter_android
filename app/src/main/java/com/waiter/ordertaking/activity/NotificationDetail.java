package com.waiter.ordertaking.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.waiter.ordertaking.R;

public class NotificationDetail extends AppCompatActivity {

    TextView txt_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_detail);
        //Objects.requireNonNull(getSupportActionBar()).hide();
        txt_back = findViewById(R.id.back);
        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
                finish();

            }
        });
    }
}