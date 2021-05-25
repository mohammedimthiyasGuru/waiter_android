package com.example.ordertakingapp.api;

import com.example.ordertakingapp.request.AdminRequestListRequest;
import com.example.ordertakingapp.request.BlockUnBlockChefRequest;
import com.example.ordertakingapp.request.BlockUnBlockWaiterRequest;
import com.example.ordertakingapp.request.CategoryItemListRequest;
import com.example.ordertakingapp.request.CreateOrderRequest;
import com.example.ordertakingapp.request.DashboardRequest;
import com.example.ordertakingapp.request.DeleteWaiterRequest;
import com.example.ordertakingapp.request.FetchChefOrderHistoryRequest;
import com.example.ordertakingapp.request.FetchChiefListRequest;
import com.example.ordertakingapp.request.FetchOrderByIdRequest;
import com.example.ordertakingapp.request.FetchWaiterListRequest;
import com.example.ordertakingapp.request.FetchWaiterOrderHistoryRequest;
import com.example.ordertakingapp.request.ItemAddOrRemoveRequest;
import com.example.ordertakingapp.request.KitchenAdminCreateRequest;
import com.example.ordertakingapp.request.KitchenAdminRequestListRequest;
import com.example.ordertakingapp.request.WaiterAdminRequestListRequest;
import com.example.ordertakingapp.request.KitchenDashoboardListRequest;
import com.example.ordertakingapp.request.OverViewItemRequest;
import com.example.ordertakingapp.request.SoSRequest;
import com.example.ordertakingapp.request.TableAvaStatusRequest;
import com.example.ordertakingapp.request.TableListRequest;
import com.example.ordertakingapp.request.WaiterAdminCreateRequest;
import com.example.ordertakingapp.request.WaiterUpdateAcceptRequest;
import com.example.ordertakingapp.request.WaiterUpdateOrderConfirmtRequest;
import com.example.ordertakingapp.response.AdminRequestListResponse;
import com.example.ordertakingapp.response.BlockUnBlockChefResponse;
import com.example.ordertakingapp.response.BlockUnBlockWaiterResponse;
import com.example.ordertakingapp.response.CategoryItemListResponse;
import com.example.ordertakingapp.response.CategoryListResponse;
import com.example.ordertakingapp.response.CreateOrderResponse;
import com.example.ordertakingapp.response.DashboardResponse;
import com.example.ordertakingapp.request.LoginRequest;
import com.example.ordertakingapp.response.DeleteWaiterResponse;
import com.example.ordertakingapp.response.DropDownCatListResponse;
import com.example.ordertakingapp.response.FetchChiefListResponse;
import com.example.ordertakingapp.response.FetchWaiterListResponse;
import com.example.ordertakingapp.response.ItemAddOrRemoveResponse;
import com.example.ordertakingapp.response.KitchenAdminRequestListResponse;
import com.example.ordertakingapp.response.WaiterAdminRequestListResponse;
import com.example.ordertakingapp.response.KitchenDashoboardListResponse;
import com.example.ordertakingapp.response.LoginResponse;
import com.example.ordertakingapp.response.OrderDetailsResponse;
import com.example.ordertakingapp.response.OverViewItemResponse;
import com.example.ordertakingapp.response.SoSResponse;
import com.example.ordertakingapp.response.SuccessResponse;
import com.example.ordertakingapp.response.TableAvaStatusResponse;
import com.example.ordertakingapp.response.TableListResponse;
import com.example.ordertakingapp.response.WaiterUpdateOrderConfirmtResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
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

    /*Edit waiter Details*/
    @POST("waiter_waiter/edit")
    Call<BlockUnBlockWaiterResponse> blockorunblockwaiterResponseCall(@Header("Content-Type") String type, @Body BlockUnBlockWaiterRequest blockUnBlockWaiterRequest);

    /*Delete waiter Details*/
    @POST("waiter_waiter/delete")
    Call<DeleteWaiterResponse> deletewaiterResponseCall(@Header("Content-Type") String type, @Body DeleteWaiterRequest deleteWaiterRequest);

    /*Edit chef Details*/
    @POST("waiter_chef/edit")
    Call<BlockUnBlockChefResponse> blockorunblockchiefResponseCall(@Header("Content-Type") String type, @Body BlockUnBlockChefRequest blockUnBlockChefRequest);

    /*Delete chef Details*/
    @POST("waiter_chef/delete")
    Call<DeleteWaiterResponse> deletechefResponseCall(@Header("Content-Type") String type, @Body DeleteWaiterRequest deleteWaiterRequest);


    /*update the status*/
    @POST("waiter_order/waiter/update/accept")
    Call<SuccessResponse> waiterUpdateAcceptResponseCall(@Header("Content-Type") String type, @Body WaiterUpdateAcceptRequest waiterUpdateAcceptRequest);


    /*fetch order by id response call*/
    @POST("kitchen_user_detail/fetch_order_by_id")
    Call<OrderDetailsResponse> fetch_order_by_id_ResponseCall(@Header("Content-Type") String type, @Body FetchOrderByIdRequest fetchOrderByIdRequest);


    /*update the status*/
    @POST("waiter_order/chef/update/complete")
    Call<SuccessResponse> waiterCompleteAcceptResponseCall(@Header("Content-Type") String type, @Body WaiterUpdateAcceptRequest waiterUpdateAcceptRequest);

    /*Get Chef Orders*/
    @POST("waiter_order/chef/order_history")
    Call<KitchenDashoboardListResponse> chefMyordersResponseCall(@Header("Content-Type") String type, @Body FetchChefOrderHistoryRequest fetchChefOrderHistoryRequest);

    /*Get Waiter Orders*/
    @POST("waiter_order/waiter/order_history")
    Call<KitchenDashoboardListResponse> waiterMyordersResponseCall(@Header("Content-Type") String type, @Body FetchWaiterOrderHistoryRequest fetchWaiterOrderHistoryRequest);


    /*waiter complete the status*/
    @POST("waiter_order/waiter/update_status")
    Call<WaiterUpdateOrderConfirmtResponse> waiterCompleteOrderResponseCall(@Header("Content-Type") String type, @Body WaiterUpdateOrderConfirmtRequest waiterUpdateOrderConfirmtRequest);


    /*waiter admin request list*/
    @POST("waiter_adminrequest/waiter/getlist")
    Call<WaiterAdminRequestListResponse> kitchenAdminRequestListResponse(@Header("Content-Type") String type, @Body WaiterAdminRequestListRequest waiterAdminRequestListRequest);

    /*dropdown list*/
    @GET("waiter_adminrequest/dropdown/getlist")
    Call<DropDownCatListResponse> dropDownListResponseCall(@Header("Content-Type") String type);

    /*Create admin new request by waiter*/
    @POST("waiter_adminrequest/create")
    Call<SuccessResponse> waiterAdminCreateResponseCall(@Header("Content-Type") String type, @Body WaiterAdminCreateRequest waiterAdminCreateRequest);

    /*Create admin new request by chef*/
    @POST("waiter_adminrequest/create")
    Call<SuccessResponse> chefAdminCreateResponseCall(@Header("Content-Type") String type, @Body KitchenAdminCreateRequest kitchenAdminCreateRequest);

    /*SoS List*/
    @POST("kitchen_user_detail/sos")
    Call<SoSResponse> soSResponseCall(@Header("Content-Type") String type, @Body SoSRequest soSRequest);

    /*kitchen admin request list*/
    @POST("waiter_adminrequest/chef/getlist")
    Call<KitchenAdminRequestListResponse> chefAdminRequestListResponse(@Header("Content-Type") String type, @Body KitchenAdminRequestListRequest kitchenAdminRequestListRequest);

    /*ADMIN request list*/
    @POST("waiter_adminrequest/admin/getlist")
    Call<AdminRequestListResponse> adminRequestListResponse(@Header("Content-Type") String type, @Body AdminRequestListRequest adminRequestListRequest);

}
