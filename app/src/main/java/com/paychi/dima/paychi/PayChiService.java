package com.paychi.dima.paychi;

import com.paychi.dima.paychi.models.TaskItem;
import com.paychi.dima.paychi.models.TaskList;
import com.paychi.dima.paychi.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PayChiService {
    @POST("user/registerVk/")
    Call<UserResponse> getUser(
        @Header(value = "token") String token,
        @Header(value = "user_id") long userId,
        @Query(value = "type") int type
    );

    @POST("taskitem/create")
    Call<TaskItem> createTask(
        @Header(value = "token") String token,
        @Header(value = "user_id") long userId,
        @Body TaskItem taskItem
    );

    @GET("tasklist/get") // список списков тасок глазами родителя
    Call<TaskListResponse> getTaskListChildren(
        @Header(value = "token") String token,
        @Header(value = "user_id") long userId
    );

    @GET("tasklist/getParents") // список списков тасок глазами ребенка
    Call<TaskListResponse> getTaskListParents(
        @Header(value = "token") String token,
        @Header(value = "user_id") long userId
    );
}