package com.waiter.ordertaking.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.waiter.ordertaking.ListPojo;
import com.waiter.ordertaking.RestUtils;
import com.waiter.ordertaking.SessionManager.SessionManager;
import com.waiter.ordertaking.adapter.MyAdapter;
import com.waiter.ordertaking.adapter.CategoryListAdapter;
import com.waiter.ordertaking.Pojo;
import com.waiter.ordertaking.R;
import com.waiter.ordertaking.adapter.OverViewItemListAdapter;
import com.waiter.ordertaking.api.APIClient;
import com.waiter.ordertaking.api.RestApiInterface;
import com.waiter.ordertaking.interfaces.AddItemListener;
import com.waiter.ordertaking.interfaces.CatagListListener;
import com.waiter.ordertaking.interfaces.RemoveItemListener;
import com.waiter.ordertaking.request.CategoryItemListRequest;
import com.waiter.ordertaking.request.CreateOrderRequest;
import com.waiter.ordertaking.request.ItemAddOrRemoveRequest;
import com.waiter.ordertaking.request.OverViewItemRequest;
import com.waiter.ordertaking.request.TableListRequest;
import com.waiter.ordertaking.response.CategoryItemListResponse;
import com.waiter.ordertaking.response.CategoryListResponse;
import com.waiter.ordertaking.response.CreateOrderResponse;
import com.waiter.ordertaking.response.ItemAddOrRemoveResponse;
import com.waiter.ordertaking.response.OverViewItemResponse;
import com.waiter.ordertaking.utils.ConnectionDetector;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatagActivity extends AppCompatActivity implements View.OnClickListener, CatagListListener, AddItemListener, RemoveItemListener {

    TextView tv_number;
    private int count;
    TextView txt_back;
    FloatingActionButton fab;


    RecyclerView recyclerView;
    TextView txt_no_itemlist;
    MyAdapter myAdapter;
    List<Pojo> list;

    RecyclerView rv_categorylist;
    List<ListPojo> pojoList;
    LottieAnimationView back_icon;

    AVLoadingIndicatorView avi_indicator;
    TextView txt_no_categorylist;


    CardView card1;
    private String TAG = "CatagActivity";
    private String restid,wait_id,tableno;
    private String cat_id;
    private Dialog alertDialog;
    List<CreateOrderRequest.ItemDetailBean> itemDetailBeanList = new ArrayList<>();

    List<OverViewItemResponse.DataBean> dataBeanList;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catag);
        //Objects.requireNonNull(getSupportActionBar()).hide();

        back_icon = findViewById(R.id.back_icon);
        txt_no_categorylist = findViewById(R.id.txt_no_categorylist);
        avi_indicator = findViewById(R.id.avi_indicator);
        avi_indicator.setVisibility(View.GONE);
        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        SessionManager sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getProfileDetails();
        restid = user.get(SessionManager.KEY_RESTID);
        wait_id = user.get(SessionManager.KEY_ID);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            tableno = extras.getString("tableno");
            Log.w(TAG,"tableno : "+tableno );
        }


        card1 =findViewById(R.id.card1);

        recyclerView = findViewById(R.id.recycler_view);
        txt_no_itemlist = findViewById(R.id.txt_no_itemlist);
        rv_categorylist = findViewById(R.id.rv_categorylist);


        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

        txt_back = findViewById(R.id.back);
        txt_back.setOnClickListener(this);

        if(restid != null) {
            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                categroies_listResponseCall();
            }
        }


    }

    @Override
    public void onClick(View view) {
            switch (view.getId()){

                case R.id.fab:
                    if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                        OverViewItemResponseCall();
                    }

                break;
            case R.id.back:
                backButton();
                break;

        }

    }

    private void OverViewItemResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<OverViewItemResponse> call = apiInterface.confirm_orderResponseCall(RestUtils.getContentType(), overViewItemRequest());
        Log.w(TAG,"OverViewItemResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<OverViewItemResponse>() {
            @Override
            public void onResponse(@NonNull Call<OverViewItemResponse> call, @NonNull Response<OverViewItemResponse> response) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"TableListResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if ( 200 == response.body().getCode()) {

                        if (response.body().getData()!=null) {

                            dataBeanList = response.body().getData();

                            itemDetailBeanList.clear();

                            for(int i=0;i<dataBeanList.size();i++){

                                CreateOrderRequest.ItemDetailBean itemDetailBean = new CreateOrderRequest.ItemDetailBean();

                                itemDetailBean.set_id(dataBeanList.get(i).get_id());

                                itemDetailBean.setCategory_id(dataBeanList.get(i).getCategory_id());

                                itemDetailBean.setItem_id(dataBeanList.get(i).getItem_id());

                                itemDetailBean.setWaiter_id(dataBeanList.get(i).getWaiter_id());

                                itemDetailBean.setTable_no(dataBeanList.get(i).getTable_no());

                                itemDetailBean.setItem_name(dataBeanList.get(i).getItem_name());

                                itemDetailBean.setItem_price(dataBeanList.get(i).getItem_price());

                                itemDetailBean.setItem_status(dataBeanList.get(i).getItem_status());

                                itemDetailBean.setItem_count(dataBeanList.get(i).getItem_count());

                                itemDetailBean.setDate_of_create(dataBeanList.get(i).getDate_of_create());

                                itemDetailBean.setCreatedAt(dataBeanList.get(i).getCreatedAt());

                                itemDetailBean.setUpdatedAt(dataBeanList.get(i).getUpdatedAt());

                                itemDetailBean.set__v(dataBeanList.get(i).get__v());

                                itemDetailBeanList.add(itemDetailBean);


                            }

                            confirmOrder(itemDetailBeanList);
                        }

                    }else{
                        showErrorLoading(response.body().getMessage());
                    }
                }

            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<OverViewItemResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("OverViewItemResponse flr", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    private OverViewItemRequest overViewItemRequest() {
        OverViewItemRequest overViewItemRequest = new OverViewItemRequest();
        overViewItemRequest.setRest_id(restid);
        overViewItemRequest.setTable_no(tableno);
        overViewItemRequest.setWaiter_id(wait_id);
        Log.w(TAG,"overViewItemRequest"+ new Gson().toJson(overViewItemRequest));
        return overViewItemRequest;
    }

    private void createOrder_listResponseCall(List<CreateOrderRequest.ItemDetailBean> data){
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<CreateOrderResponse> call = apiInterface.create_orderResponseCall(RestUtils.getContentType(), createOrderRequest(data));
        Log.w(TAG,"CreateOrderResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<CreateOrderResponse>() {
            @Override
            public void onResponse(@NonNull Call<CreateOrderResponse> call, @NonNull Response<CreateOrderResponse> response) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"CreateOrderResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if ( 200 == response.body().getCode()) {

                        Intent intent = new Intent(CatagActivity.this,WaiterActivity.class);

                        startActivity(intent);

                        finish();


                    }else{
                        showErrorLoading(response.body().getMessage());
                    }
                }

            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<CreateOrderResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("CreateOrderResponse flr", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private CreateOrderRequest createOrderRequest(List<CreateOrderRequest.ItemDetailBean> data) {

        /**
         * rest_id : 608f6fc9bb5e115d275c28b4
         * table_no : 1
         * taken_id : 608f701fbb5e115d275c28b8
         * order_date_book : 23-10-2021 11:00 AM
         * item_detail : [{"_id":"60920638ccb9375cfa41b8a2","rest_id":"608f6fc9bb5e115d275c28b4","category_id":"608f83080ce4f06a62055b59","item_id":"608fd7942392940d525dcaaa","waiter_id":"608f701fbb5e115d275c28b8","table_no":"1","item_name":"Coffee","item_price":100,"item_status":"New Booking","item_count":1,"date_of_create":"Wed May 05 2021 02:43:04 GMT+0000 (Coordinated Universal Time)","updatedAt":"2021-05-05T02:46:19.613Z","createdAt":"2021-05-05T02:43:04.362Z","__v":0},{"_id":"60920638ccb9375cfa41b8a2","rest_id":"608f6fc9bb5e115d275c28b4","category_id":"608f83080ce4f06a62055b59","item_id":"608fd7942392940d525dcaaa","waiter_id":"608f701fbb5e115d275c28b8","table_no":"1","item_name":"Coffee","item_price":100,"item_status":"New Booking","item_count":2,"date_of_create":"Wed May 05 2021 02:43:04 GMT+0000 (Coordinated Universal Time)","updatedAt":"2021-05-05T02:46:19.613Z","createdAt":"2021-05-05T02:43:04.362Z","__v":0},{"_id":"60920638ccb9375cfa41b8a2","rest_id":"608f6fc9bb5e115d275c28b4","category_id":"608f83080ce4f06a62055b59","item_id":"608fd7942392940d525dcaaa","waiter_id":"608f701fbb5e115d275c28b8","table_no":"1","item_name":"Coffee","item_price":300,"item_status":"New Booking","item_count":2,"date_of_create":"Wed May 05 2021 02:43:04 GMT+0000 (Coordinated Universal Time)","updatedAt":"2021-05-05T02:46:19.613Z","createdAt":"2021-05-05T02:43:04.362Z","__v":0}]
         */

        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
        String currentDateandTime = simpleDateFormat.format(new Date());


        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setRest_id(restid);
        createOrderRequest.setTable_no(tableno);
        createOrderRequest.setTaken_id(wait_id);
        createOrderRequest.setOrder_date_book(currentDateandTime);
        createOrderRequest.setItem_detail(data);

        Log.w(TAG,"CreateOrderRequest"+ new Gson().toJson(createOrderRequest));
        return createOrderRequest;
    }



    private void backButton() {
        startActivity(new Intent(getApplicationContext(), WaiterActivity.class));
        finish();
    }

    private void confirmOrder(List<CreateOrderRequest.ItemDetailBean> itemDetailBeanList) {

        try {

            dialog = new Dialog(CatagActivity.this);
            dialog.setContentView(R.layout.order_view_popup_layout);
            RecyclerView rv_itemlist = (RecyclerView)dialog.findViewById(R.id.rv_itemlist);
            Button btn_confirm = (Button)dialog.findViewById(R.id.btn_confirm);
            Button btn_cancel = (Button)dialog.findViewById(R.id.btn_cancel);
            TextView txt_no_records = (TextView)dialog.findViewById(R.id.txt_no_records);
            ImageView img_close = (ImageView)dialog.findViewById(R.id.img_close);
            img_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            if(itemDetailBeanList != null && itemDetailBeanList.size()>0){
                rv_itemlist.setVisibility(View.VISIBLE);
                txt_no_records.setVisibility(View.GONE);
                rv_itemlist.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                rv_itemlist.setItemAnimator(new DefaultItemAnimator());
                rv_itemlist.setNestedScrollingEnabled(true);
                OverViewItemListAdapter overViewItemListAdapter = new OverViewItemListAdapter(getApplicationContext(), itemDetailBeanList);
                rv_itemlist.setAdapter(overViewItemListAdapter);
            }else{
                rv_itemlist.setVisibility(View.GONE);
                txt_no_records.setVisibility(View.VISIBLE);

            }

            btn_confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {

                        createOrder_listResponseCall(itemDetailBeanList);

                        dialog.dismiss();

                    }

                }
            });


            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();


        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), WaiterActivity.class));
        finish();
    }

    private void categroies_items_listResponseCall(String cat_id) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<CategoryItemListResponse> call = apiInterface.categroies_items_listResponseCall(RestUtils.getContentType(), categoryItemListRequest(cat_id));
        Log.w(TAG,"categoryItemlistResponseCall url  :%s"+" "+ call.request().url().toString());
        call.enqueue(new Callback<CategoryItemListResponse>() {
            @Override
            public void onResponse(@NonNull Call<CategoryItemListResponse> call, @NonNull Response<CategoryItemListResponse> response) {
                avi_indicator.smoothToHide();

                if (response.body().getData() != null) {
                    if ( 200 == response.body().getCode()) {
                        if(response.body().getData().size()>0){
                            Log.w(TAG,"categoryItemlistResponseCall : "+new Gson().toJson(response.body().getData()));
                            recyclerView.setVisibility(View.VISIBLE);
                            txt_no_itemlist.setVisibility(View.GONE);
                            setViewCategoryItemList(response.body().getData());
                        }else {
                            recyclerView.setVisibility(View.GONE);
                            txt_no_itemlist.setVisibility(View.VISIBLE);
                        }


                    }else{
                        showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<CategoryItemListResponse> call, Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("TableListResponse flr", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void categroies_listResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<CategoryListResponse> call = apiInterface.categroies_listResponseCall(RestUtils.getContentType(), tableListRequest());
        Log.w(TAG,"table_listResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<CategoryListResponse>() {
            @Override
            public void onResponse(@NonNull Call<CategoryListResponse> call, @NonNull Response<CategoryListResponse> response) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"TableListResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if ( 200 == response.body().getCode()) {

                        if(response.body().getData() != null && response.body().getData().size()>0){
                            rv_categorylist.setVisibility(View.VISIBLE);
                            txt_no_categorylist.setVisibility(View.GONE);
                            setViewCategoryList(response.body().getData());
                        }else {
                            rv_categorylist.setVisibility(View.GONE);
                            txt_no_categorylist.setVisibility(View.VISIBLE);
                        }


                    }else{
                        showErrorLoading(response.body().getMessage());
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<CategoryListResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("TableListResponse flr", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void setViewCategoryList(List<CategoryListResponse.DataBean> data) {
        CategoryListAdapter categoryListAdapter = new CategoryListAdapter(this,data,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_categorylist.setLayoutManager(linearLayoutManager);
        rv_categorylist.setAdapter(categoryListAdapter);

    }
    private void setViewCategoryItemList(List<CategoryItemListResponse.DataBean> data) {
        MyAdapter myAdapter = new MyAdapter(this,data,this,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);

    }
    private TableListRequest tableListRequest() {
        TableListRequest tableListRequest = new TableListRequest();
        tableListRequest.setRest_id(restid);
        Log.w(TAG,"tableListRequest"+ new Gson().toJson(tableListRequest));
        return tableListRequest;
    }

    private CategoryItemListRequest categoryItemListRequest(String cat_id) {

        /**
         * rest_id : 12345
         * cat_id : 123455
         * table_no : 1
         * waiter_id : 123123
         */


        CategoryItemListRequest categoryItemListRequest = new CategoryItemListRequest();
        categoryItemListRequest.setRest_id(restid);
        categoryItemListRequest.setCat_id(cat_id);
        categoryItemListRequest.setTable_no(tableno);
        categoryItemListRequest.setWaiter_id(wait_id);
        Log.w(TAG,"categoryItemListRequest"+ new Gson().toJson(categoryItemListRequest));
        return categoryItemListRequest;

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

    @Override
    public void CatagListListener(String cat_id) {
        if(cat_id != null){
            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                categroies_items_listResponseCall(cat_id);
            }

        }

    }

    @Override
    public void additemdetails(String category_id, String item_id, String item_name, int item_price) {

        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {

            add_items_listResponseCall(category_id,item_id,item_name,item_price);

        }

    }

    private void add_items_listResponseCall(String category_id, String item_id, String item_name, int item_price) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<ItemAddOrRemoveResponse> call = apiInterface.add_item_ResponseCall(RestUtils.getContentType(), itemAddOrRemoveRequest(category_id,item_id,item_name,item_price));
        Log.w(TAG,"ItemAddResponse url  :%s"+" "+ call.request().url().toString());
        call.enqueue(new Callback<ItemAddOrRemoveResponse>() {
            @Override
            public void onResponse(@NonNull Call<ItemAddOrRemoveResponse> call, @NonNull Response<ItemAddOrRemoveResponse> response) {
                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if ( 200 == response.body().getCode()) {

                        Toasty.success(getApplicationContext(),"Added",Toasty.LENGTH_SHORT).show();


                    }else{
                        showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<ItemAddOrRemoveResponse> call, Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("ItemAddResponse flr", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private ItemAddOrRemoveRequest itemAddOrRemoveRequest(String category_id, String item_id, String item_name, int item_price) {

        /**
         * rest_id : 608f6fc9bb5e115d275c28b4
         * category_id : 608f83080ce4f06a62055b59
         * item_id : 608fd7942392940d525dcaaa
         * waiter_id : 3
         * table_no : 2
         * item_name : Coffee
         * item_price : 100
         */

        ItemAddOrRemoveRequest itemAddOrRemoveRequest = new ItemAddOrRemoveRequest();
        itemAddOrRemoveRequest.setRest_id(restid);
        itemAddOrRemoveRequest.setCategory_id(category_id);
        itemAddOrRemoveRequest.setItem_id(item_id);
        itemAddOrRemoveRequest.setWaiter_id(wait_id);
        itemAddOrRemoveRequest.setTable_no(tableno);
        itemAddOrRemoveRequest.setItem_name(item_name);
        itemAddOrRemoveRequest.setItem_price(item_price);


        Log.w(TAG,"itemAddOrRemoveRequest"+ new Gson().toJson(itemAddOrRemoveRequest));
        return itemAddOrRemoveRequest;

    }

    @Override
    public void removeitemdetails(String category_id, String item_id, String item_name, int item_price) {

        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {

            remove_items_listResponseCall(category_id,item_id,item_name,item_price);

        }


    }

    private void remove_items_listResponseCall(String category_id, String item_id, String item_name, int item_price) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<ItemAddOrRemoveResponse> call = apiInterface.remove_item_ResponseCall(RestUtils.getContentType(), itemAddOrRemoveRequest(category_id,item_id,item_name,item_price));
        Log.w(TAG,"ItemRemoveResponse url  :%s"+" "+ call.request().url().toString());
        call.enqueue(new Callback<ItemAddOrRemoveResponse>() {
            @Override
            public void onResponse(@NonNull Call<ItemAddOrRemoveResponse> call, @NonNull Response<ItemAddOrRemoveResponse> response) {
                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if ( 200 == response.body().getCode()) {

                        Toasty.success(getApplicationContext(),"Removed",Toasty.LENGTH_SHORT).show();


                    }else{
                        showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<ItemAddOrRemoveResponse> call, Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("ItemARemoveResponse flr", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
