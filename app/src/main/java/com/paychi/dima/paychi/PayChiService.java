package com.paychi.dima.paychi;

import com.paychi.dima.paychi.KuSu.models.Message;
import com.paychi.dima.paychi.KuSu.reaponse.DialogsResponse;
import com.paychi.dima.paychi.KuSu.reaponse.MessagesResponse;
import com.paychi.dima.paychi.KuSu.reaponse.EmptyResponse;
import com.paychi.dima.paychi.KuSu.reaponse.UsersResponse;
import com.paychi.dima.paychi.models.TaskItem;
import com.paychi.dima.paychi.models.TaskList;
import com.paychi.dima.paychi.responses.TaskListResponse;
import com.paychi.dima.paychi.responses.TasksListItemResponse;
import com.paychi.dima.paychi.responses.UserResponse;
import com.paychi.dima.paychi.responses.WishListResponse;

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

    @POST("taskitem/create")
    Call<TaskItem> createTask(
        @Header(value = "token") String token,
        @Header(value = "user_id") long userId,
        @Body TaskItem taskItem
    );

    @POST("tasklist/create")
    Call<TasksListItemResponse> createTaskList(
        @Header(value = "token") String token,
        @Header(value = "user_id") long userId,
        @Body TaskList taskList
    );

    @GET("tasklist/get") // список списков тасок
    Call<TaskListResponse> getTaskList(
        @Header(value = "token") String token,
        @Header(value = "user_id") long userId
    );

    @GET("wishlist/get") // список списков подарков
    Call<WishListResponse> getWishList(
        @Header(value = "token") String token,
        @Header(value = "user_id") long userId
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
}