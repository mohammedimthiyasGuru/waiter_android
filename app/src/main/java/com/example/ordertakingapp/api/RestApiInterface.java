package com.example.ordertakingapp.api;

import androidx.arch.core.util.Function;

import com.example.ordertakingapp.request.BlockUnBlockWaiterRequest;
import com.example.ordertakingapp.request.CategoryItemListRequest;
import com.example.ordertakingapp.request.CreateOrderRequest;
import com.example.ordertakingapp.request.DashboardRequest;
import com.example.ordertakingapp.request.DeleteWaiterRequest;
import com.example.ordertakingapp.request.FetchChiefListRequest;
import com.example.ordertakingapp.request.FetchWaiterListRequest;
import com.example.ordertakingapp.request.ItemAddOrRemoveRequest;
import com.example.ordertakingapp.request.KitchenDashoboardListRequest;
import com.example.ordertakingapp.request.OverViewItemRequest;
import com.example.ordertakingapp.request.TableAvaStatusRequest;
import com.example.ordertakingapp.request.TableListRequest;
import com.example.ordertakingapp.response.BlockUnBlockWaiterResponse;
import com.example.ordertakingapp.response.CategoryItemListResponse;
import com.example.ordertakingapp.response.CategoryListResponse;
import com.example.ordertakingapp.response.CreateOrderResponse;
import com.example.ordertakingapp.response.DashboardResponse;
import com.example.ordertakingapp.request.LoginRequest;
import com.example.ordertakingapp.response.DeleteWaiterResponse;
import com.example.ordertakingapp.response.FetchChiefListResponse;
import com.example.ordertakingapp.response.FetchWaiterListResponse;
import com.example.ordertakingapp.response.ItemAddOrRemoveResponse;
import com.example.ordertakingapp.response.KitchenDashoboardListResponse;
import com.example.ordertakingapp.response.LoginResponse;
import com.example.ordertakingapp.response.OverViewItemResponse;
import com.example.ordertakingapp.response.TableAvaStatusResponse;
import com.example.ordertakingapp.response.TableListResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RestApiInterface {

    /*Login*/
    @POST("kitchen_user_detail/login")
    Call<LoginResponse> loginResponseCall(@Header("Content-Type") String type, @Body LoginRequest loginRequest);

    /*Dashboard*/
    @POST("kitchen_user_detail/dashboard")
    Call<DashboardResponse> dashboardResponseCall(@Header("Content-Type") String type, @Body DashboardRequest dashboardRequest);

    /*table list*/
    @POST("kitchen_user_detail/table_list")
    Call<TableListResponse> table_listResponseCall(@Header("Content-Type") String type, @Body TableListRequest tableListRequest);

    /*category list*/
    @POST("kitchen_user_detail/categroies_list")
    Call<CategoryListResponse> categroies_listResponseCall(@Header("Content-Type") String type, @Body TableListRequest tableListRequest);

     /*category based items list*/
        @POST("kitchen_user_detail/items_list")
    Call<CategoryItemListResponse> categroies_items_listResponseCall(@Header("Content-Type") String type, @Body CategoryItemListRequest categoryItemListRequest);


    /*kitchen dashboard list*/
    @POST("kitchen_user_detail/kitchen_dashboard")
    Call<KitchenDashoboardListResponse> kitchen_dashboard_ResponseCall(@Header("Content-Type") String type, @Body KitchenDashoboardListRequest kitchenDashoboardListRequest);

   /*Check Table Available Status*/
    @POST("kitchen_user_detail/check_table_status")
    Call<TableAvaStatusResponse> check_table_status_ResponseCall(@Header("Content-Type") String type, @Body TableAvaStatusRequest tableAvaStatusRequest);

    /*Add Item*/
    @POST("kitchen_user_detail/add_item")
    Call<ItemAddOrRemoveResponse> add_item_ResponseCall(@Header("Content-Type") String type, @Body ItemAddOrRemoveRequest itemAddOrRemoveRequest);

    /*Remove Item*/
    @POST("kitchen_user_detail/remove_item")
    Call<ItemAddOrRemoveResponse> remove_item_ResponseCall(@Header("Content-Type") String type, @Body ItemAddOrRemoveRequest itemAddOrRemoveRequest);

    /*OverView Item*/
    @POST("kitchen_user_detail/over_view_item")
    Call<OverViewItemResponse> confirm_orderResponseCall(@Header("Content-Type") String type, @Body OverViewItemRequest overViewItemRequest);

    /*Create Order Item*/
    @POST("kitchen_user_detail/order/create")
    Call<CreateOrderResponse> create_orderResponseCall(@Header("Content-Type") String type, @Body CreateOrderRequest createOrderRequest);

    /*Get Waiter Details*/
    @POST("waiter_waiter/getlist_id")
    Call<FetchWaiterListResponse> getwaiterlistResponseCall(@Header("Content-Type") String type, @Body FetchWaiterListRequest fetchWaiterListRequest);

    /*Get Chef Details*/
    @POST("waiter_chef/getlist_id")
    Call<FetchChiefListResponse> getcheflistResponseCall(@Header("Content-Type") String type, @Body FetchChiefListRequest fetchChiefListRequest);

    /*Get Chef Details*/
    @POST("waiter_waiter/edit")
    Call<BlockUnBlockWaiterResponse> blockorunblockwaiterResponseCall(@Header("Content-Type") String type, @Body BlockUnBlockWaiterRequest blockUnBlockWaiterRequest);

    /*Get Chef Details*/
    @POST("waiter_waiter/delete")
    Call<DeleteWaiterResponse> deletewaiterResponseCall(@Header("Content-Type") String type, @Body DeleteWaiterRequest deleteWaiterRequest);


}
