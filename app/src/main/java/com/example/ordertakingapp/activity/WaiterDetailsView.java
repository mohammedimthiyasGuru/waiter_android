package com.example.ordertakingapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ordertakingapp.R;
import com.example.ordertakingapp.SessionManager.SessionManager;

import java.util.Objects;

public class WaiterDetailsView extends AppCompatActivity {

    Button btn_remove,btn_block;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter_details_view);
        Objects.requireNonNull(getSupportActionBar()).hide();

        btn_remove = findViewById(R.id.btn_remove);
        btn_block = findViewById(R.id.btn_block);
        btn_block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(WaiterDetailsView.this);
                builder.setTitle("Block");
                builder.setMessage("Are you sure you want to block ?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(getApplicationContext(),"Blocked",Toast.LENGTH_LONG).show();
                        finish();

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
        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(WaiterDetailsView.this);

                builder.setTitle("Remove");
                builder.setMessage("Are you sure you want to remove ?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(getApplicationContext(),"Remove Sucess",Toast.LENGTH_LONG).show();
                        finish();
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


    private void methodBlock() {


    }
    private void methodRemove() {


    }


}