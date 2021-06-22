package com.waiter.ordertaking.api;

import com.waiter.ordertaking.request.AdminRequestListRequest;
import com.waiter.ordertaking.request.AdminTableListRequest;
import com.waiter.ordertaking.request.BlockUnBlockChefRequest;
import com.waiter.ordertaking.request.BlockUnBlockWaiterRequest;
import com.waiter.ordertaking.request.CategoryItemListRequest;
import com.waiter.ordertaking.request.CreateOrderRequest;
import com.waiter.ordertaking.request.DashboardRequest;
import com.waiter.ordertaking.request.DeleteWaiterRequest;
import com.waiter.ordertaking.request.FetchChefOrderHistoryRequest;
import com.waiter.ordertaking.request.FetchChiefListRequest;
import com.waiter.ordertaking.request.FetchOrderByIdRequest;
import com.waiter.ordertaking.request.FetchWaiterListRequest;
import com.waiter.ordertaking.request.FetchWaiterOrderHistoryRequest;
import com.waiter.ordertaking.request.ItemAddOrRemoveRequest;
import com.waiter.ordertaking.request.KitchenAdminCreateRequest;
import com.waiter.ordertaking.request.KitchenAdminRequestListRequest;
import com.waiter.ordertaking.request.NotificationListRequest;
import com.waiter.ordertaking.request.NotificationMarkReadRequest;
import com.waiter.ordertaking.request.WaiterAdminRequestListRequest;
import com.waiter.ordertaking.request.KitchenDashoboardListRequest;
import com.waiter.ordertaking.request.OverViewItemRequest;
import com.waiter.ordertaking.request.SoSRequest;
import com.waiter.ordertaking.request.TableAvaStatusRequest;
import com.waiter.ordertaking.request.TableListRequest;
import com.waiter.ordertaking.request.WaiterAdminCreateRequest;
import com.waiter.ordertaking.request.WaiterUpdateAcceptRequest;
import com.waiter.ordertaking.request.WaiterUpdateOrderConfirmtRequest;
import com.waiter.ordertaking.response.AdminRequestListResponse;
import com.waiter.ordertaking.response.AdminTableListResponse;
import com.waiter.ordertaking.response.BlockUnBlockChefResponse;
import com.waiter.ordertaking.response.BlockUnBlockWaiterResponse;
import com.waiter.ordertaking.response.CategoryItemListResponse;
import com.waiter.ordertaking.response.CategoryListResponse;
import com.waiter.ordertaking.response.CreateOrderResponse;
import com.waiter.ordertaking.response.DashboardResponse;
import com.waiter.ordertaking.request.LoginRequest;
import com.waiter.ordertaking.response.DeleteWaiterResponse;
import com.waiter.ordertaking.response.DropDownCatListResponse;
import com.waiter.ordertaking.response.FetchChiefListResponse;
import com.waiter.ordertaking.response.FetchWaiterListResponse;
import com.waiter.ordertaking.response.ItemAddOrRemoveResponse;
import com.waiter.ordertaking.response.KitchenAdminRequestListResponse;
import com.waiter.ordertaking.response.NotificationListResponse;
import com.waiter.ordertaking.response.WaiterAdminRequestListResponse;
import com.waiter.ordertaking.response.KitchenDashoboardListResponse;
import com.waiter.ordertaking.response.LoginResponse;
import com.waiter.ordertaking.response.OrderDetailsResponse;
import com.waiter.ordertaking.response.OverViewItemResponse;
import com.waiter.ordertaking.response.SoSResponse;
import com.waiter.ordertaking.response.SuccessResponse;
import com.waiter.ordertaking.response.TableAvaStatusResponse;
import com.waiter.ordertaking.response.TableListResponse;
import com.waiter.ordertaking.response.WaiterUpdateOrderConfirmtResponse;

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


    /*Get notifications list*/
    @POST("waiter_notification/user/getlist_id")
    Call<NotificationListResponse> notificationListResponseCall(@Header("Content-Type") String type, @Body NotificationListRequest notificationListRequest);

     /*mark read notifications*/
    @POST("waiter_notification/markread")
    Call<SuccessResponse> markreadResponseCall(@Header("Content-Type") String type, @Body NotificationMarkReadRequest notificationMarkReadRequest);

    /*Get Tablelist in admin*/
    @POST("waiter_table/getlist_id")
    Call<AdminTableListResponse> admintablelistResponseCall(@Header("Content-Type") String type, @Body AdminTableListRequest adminTableListRequest);

}
