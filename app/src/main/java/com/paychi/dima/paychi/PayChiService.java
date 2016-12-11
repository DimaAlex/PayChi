package com.paychi.dima.paychi;

import com.paychi.dima.paychi.models.TaskItem;
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
        @Header(value = "userId") long userId,
        @Query(value = "type") int type
    );

    @POST("/paychi/taskitem/create")
    Call<TaskItem> getTask(
        @Header(value = "token") String token,
        @Header(value = "userId") long userId,
        @Body TaskItem taskItem
    );
}