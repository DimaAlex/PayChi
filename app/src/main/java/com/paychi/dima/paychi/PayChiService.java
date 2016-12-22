package com.paychi.dima.paychi;

import com.paychi.dima.paychi.KuSu.models.Message;
import com.paychi.dima.paychi.KuSu.reaponse.DialogsResponse;
import com.paychi.dima.paychi.KuSu.reaponse.MessagesResponse;
import com.paychi.dima.paychi.KuSu.reaponse.EmptyResponse;
import com.paychi.dima.paychi.KuSu.reaponse.UsersResponse;
import com.paychi.dima.paychi.models.TaskItem;
import com.paychi.dima.paychi.models.TaskList;
import com.paychi.dima.paychi.models.WishItem;
import com.paychi.dima.paychi.models.WishList;
import com.paychi.dima.paychi.responses.CreateTaskItemResponse;
import com.paychi.dima.paychi.responses.CreateWishItemResponse;
import com.paychi.dima.paychi.responses.CreateWishListResponse;
import com.paychi.dima.paychi.responses.GetTaskItemsResponse;
import com.paychi.dima.paychi.responses.GetWishItemsResponse;
import com.paychi.dima.paychi.responses.TaskListResponse;
import com.paychi.dima.paychi.responses.CreateTasksListResponse;
import com.paychi.dima.paychi.responses.UserResponse;
import com.paychi.dima.paychi.responses.WishListResponse;
import com.paychi.dima.paychi.responses.getUsersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PayChiService {
    @POST("user/registerVk/")
    Call<UserResponse> getUser(
        @Header(value = "token") String token,
        @Header(value = "userId") long userId,
        @Query(value = "type") int type
    );

    @POST("user/loginVk/")
    Call<UserResponse> getUser(
        @Header(value = "token") String token,
        @Header(value = "userId") long userId
    );

    @GET("taskitem/get/{tasklist_id}")
    Call<GetTaskItemsResponse> getTaskItems(
        @Header(value = "token") String token,
        @Header(value = "user_id") long userId,
        @Path("tasklist_id") long taskListId
    );

    @POST("taskitem/create")
    Call<CreateTaskItemResponse> createTaskItem(
        @Header(value = "token") String token,
        @Header(value = "user_id") long userId,
        @Body TaskItem taskItem
    );

    @POST("tasklist/create")
    Call<CreateTasksListResponse> createTaskList(
        @Header(value = "token") String token,
        @Header(value = "user_id") long userId,
        @Body TaskList taskList
    );

    @GET("tasklist/get") // список списков тасок
    Call<TaskListResponse> getTaskList(
        @Header(value = "token") String token,
        @Header(value = "user_id") long userId
    );

//    @POST("tasklist/permission/{tasklist_id}")
//    Call<> changeTasklistPermission(
//        @Header(value = "token") String token,
//        @Path("tasklist_id") long taskListId,
//        @Header(value = "user_id") long userId,
//        @Body List<Long> gIds
//        );

    @GET("wishitem/get/{wishlist_id}")
    Call<GetWishItemsResponse> getWishItems(
        @Header(value = "token") String token,
        @Header(value = "user_id") long userId,
        @Path("wishlist_id") long wishListId
    );

    @POST("wishitem/create/{wishlist_id}")
    Call<CreateWishItemResponse> createWishItem(
        @Header(value = "token") String token,
        @Header(value = "user_id") long userId,
        @Body WishItem wishItem,
        @Path("wishlist_id") long wishListId
    );

    @GET("wishlist/get") // список списков подарков
    Call<WishListResponse> getWishList(
        @Header(value = "token") String token,
        @Header(value = "user_id") long userId
    );

    @POST("wishlist/create")
    Call<CreateWishListResponse> createWishList(
        @Header(value = "token") String token,
        @Header(value = "user_id") long userId,
        @Body WishList wishList
    );

    @GET("dialog/getAll/{dialog_id}")
    Call<UsersResponse> getDialogsUsers(
            @Header(value = "token") String token,
            @Header(value = "user_id") long userId,
            @Path("dialog_id") long dialogId
    );

    @GET("dialog/getAll")
    Call<DialogsResponse> getDialogs(
            @Header(value = "token") String token,
            @Header(value = "user_id") long userId
    );

    @GET("message/getFirst/{dialog_id}")
    Call<MessagesResponse> getFirst(
            @Header(value = "token") String token,
            @Header(value = "user_id") long userId,
            @Path("dialog_id") long dialogId
    );

    @DELETE("dialog/out/{dialog_id}")
    Call<EmptyResponse> outDialog(
            @Header(value = "token") String token,
            @Header(value = "user_id") long userId,
            @Path("dialog_id") long dialogId
    );

    @GET("message/getNext/{dialog_id}")
    Call<MessagesResponse> getNext(
            @Header(value = "token") String token,
            @Header(value = "user_id") long userId,
            @Path("dialog_id") long dialogId,
            @Query(value = "message_id") long messageId
    );

    @POST("message/create")
    Call<EmptyResponse> createMessage(
            @Header(value = "token") String token,
            @Header(value = "user_id") long userId,
            @Body Message message
            );

    @GET("request/accepted")
    Call<getUsersResponse> getUsers(
        @Header(value = "token") String token,
        @Header(value = "user_id") long userId
    );
}