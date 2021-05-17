package com.example.ordertakingapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ordertakingapp.R;

import java.util.ArrayList;
import java.util.Objects;

public class TableItemList extends AppCompatActivity implements View.OnClickListener {
    Spinner spin_order;

    TextView txt_table_no,txt_table,txt_taken,txt_date,txt_status;
    TextView txt_back;


    String[] order ={"Select","Order Waiting","Order Complete"};
    ArrayList<Integer> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_item_list);
        Objects.requireNonNull(getSupportActionBar()).hide();

        txt_table_no = findViewById(R.id.txt_table_number);
        txt_table = findViewById(R.id.txt_tablename);
        txt_taken = findViewById(R.id.txt_taken);
        txt_date = findViewById(R.id.txt_date);
        txt_status = findViewById(R.id.txt_status);

        txt_back = findViewById(R.id.back);
        txt_back.setOnClickListener(this);

        spin_order = (Spinner)findViewById(R.id.blood_spin);
        ArrayAdapter<String> bloodAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,order);
        bloodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_order.setAdapter(bloodAdapter);
        spin_order.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        String T_name = getIntent().getStringExtra("Table name");
        String Taken_name = getIntent().getStringExtra("Taken by");
        String Date = getIntent().getStringExtra("Date");
        String Status = getIntent().getStringExtra("Status");

        txt_table.setText(T_name);
        txt_taken.setText(Taken_name);
        txt_date.setText(Date);
        txt_status.setText(Status);



    }

    @Override
    public void onClick(View view) {

        Intent i ;
        switch(view.getId()){
            case R.id.back:
                startActivity(new Intent(getApplicationContext(), KitchenTakingActivity.class));
                finish();
                break;
        }

    }
}